package war.monitor;

import conexion.Conexion;
import dao.DAOObservaciones;
import dao.DAOParams;
import dao.monitor.DAOTrabajos;
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

public class Taller extends HttpServlet {

    private String path = "C:/Users/BlueFox/Documents/NetBeansProjects/REQM/web/";
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
                user = (UserManager) session.getAttribute("user");
                if (keycode != null) {
                    session.setAttribute("DispositivoId", new Long(keycode));
                    SQLXML taller = DAOTrabajos.getXMLRecords((Long) session.getAttribute("DispositivoId"), DAOTrabajos.F_LISTA);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(taller.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/taller.xsl"));
                }else{
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOTrabajos.getXMLRecords().getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/taller.xsl"));
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
                    SQLXML taller = DAOTrabajos.getXMLRecords(new Long(request.getParameter("mod")), DAOTrabajos.F_REGISTRO);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(taller.getString(),
                            new String[]{user.getPermisos(),DAOParams.getXMLRecords(DAOParams.PM_MOTIVOS).getString()}), path + "xsl/monitor/taller_form.xsl"));
                } else if (ins != null) {
                    Conexion.autoConnect();
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML taller = DAOTrabajos.getXMLRecords(new Long(ins), DAOTrabajos.F_NEW);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(taller.getString(),
                            new String[]{user.getPermisos(),DAOParams.getXMLRecords(DAOParams.PM_MOTIVOS).getString()}), path + "xsl/monitor/taller_form.xsl"));
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
                user = (UserManager) session.getAttribute("user");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                DAOTrabajos taller = new DAOTrabajos(new Long(request.getParameter("inCode")), new Long(request.getParameter("inFKey")),
                        new Integer(request.getParameter("inMotive")), new Integer(request.getParameter("inState")),
                        Integer.parseInt(request.getParameter("inLevel")),Integer.parseInt(""+user.getUsuarioId()),request.getParameter("inName"), request.getParameter("inDesc"));
                if (taller.getlTrabajoId() == 0) {
                    taller.insert();
                } else {
                    taller.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOTrabajos taller = new DAOTrabajos();
                taller.setlTrabajoId(new Long(request.getParameter("keycode")));
                taller.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOTrabajos.getXMLRecords().getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/taller.xsl"));
            } else if (request.getParameter("srch") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOTrabajos.searchXML(request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/taller.xsl"));
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
