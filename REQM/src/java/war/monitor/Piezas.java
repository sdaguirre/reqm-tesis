package war.monitor;

import conexion.Conexion;
import dao.DAOObservaciones;
import dao.DAOParams;
import dao.monitor.DAOPiezas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;

public class Piezas extends HttpServlet {

    private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";
    //private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(false);
            UserManager user;
            if (!session.isNew()) {
                Conexion.autoConnect();
                String keycode = request.getParameter("fkey");
                if (keycode != null) {
                    user = (UserManager) session.getAttribute("user");
                    session.setAttribute("DispositivoId", new Long(keycode));
                    SQLXML piezas = DAOPiezas.getXMLRecords((Long) session.getAttribute("DispositivoId"), DAOPiezas.F_LISTA);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(piezas.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/piezas.xsl"));
                }
            } else {
                Conexion.getConnection().disconnect();
                request.getSession().invalidate();
                response.sendRedirect("login.html");
            }
        } catch (Exception ex) {
            //Logger.getLogger(ADM.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession(true).invalidate();
            throw new ServletException("", ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        try {
            if (session.isNew() || session == null) {
                Conexion.getConnection().disconnect();
                session.invalidate();
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                String ins = request.getParameter("ins");
                System.out.println(ins + " == " + request.getParameter("ins"));
                if (request.getParameter("mod") != null) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML piezas = DAOPiezas.getXMLRecords(new Long(request.getParameter("mod")), DAOPiezas.F_REGISTRO);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(piezas.getString(),
                            new String[]{user.getPermisos(),DAOParams.getXMLRecords(DAOParams.PM_PPIEZAS).getString(),DAOParams.getXMLRecords(DAOParams.PM_MARCAS).getString()}), path + "xsl/monitor/piezas_form.xsl"));
                } else if (ins != null) {
                    Conexion.autoConnect();
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML piezas = DAOPiezas.getXMLRecords((Long) session.getAttribute("DispositivoId"), DAOPiezas.F_NEW);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(piezas.getString(),
                            new String[]{user.getPermisos(),DAOParams.getXMLRecords(DAOParams.PM_PPIEZAS).getString(),DAOParams.getXMLRecords(DAOParams.PM_MARCAS).getString()}), path + "xsl/monitor/piezas_form.xsl"));
                } else {
                    processRequest(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("login.html");
            //throw new ServletException(ex.getMessage(), ex.getCause());
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session.isNew()) {
            session.invalidate();
            response.sendRedirect("login.html");
            return;
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                DAOPiezas daopieza = new DAOPiezas(new Long(request.getParameter("inCode")), new Long(request.getParameter("inFKey")),
                        new java.sql.Date(dateFormatter.parse(request.getParameter("inDate")).getTime()),new java.sql.Date(dateFormatter.parse(request.getParameter("inGarantia")).getTime()),new Integer(request.getParameter("inType")), new Integer(request.getParameter("inMarca")),
                        request.getParameter("inSerial"), request.getParameter("inModelo"), request.getParameter("inDesc"));
                if (daopieza.getlPiezaId() == 0) {
                    daopieza.insert();
                } else {
                    daopieza.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOPiezas daopieza = new DAOPiezas();
                daopieza.setlPiezaId(new Long(request.getParameter("keycode")));
                daopieza.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPiezas.getXMLRecords((Long) session.getAttribute("DispositivoId"), DAOPiezas.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/piezas.xsl"));
            } else if (request.getParameter("srch") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPiezas.searchXML((Long)session.getAttribute("Dispositivoid"),request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/piezas.xsl"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex.getMessage(), ex.getCause());
        } finally {
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "REQM System - Equipos Empresa";
    }// </editor-fold>
}
