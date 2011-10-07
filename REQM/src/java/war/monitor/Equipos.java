package war.monitor;

import conexion.Conexion;
import dao.DAOAnteproyectos;
import dao.DAOObservaciones;
import dao.DAOPFisicas;
import dao.DAOParams;
import dao.monitor.DAOEquipos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;

public class Equipos extends HttpServlet {

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
                    if (user.isbClient()) {
                        session.setAttribute("PersonaId", user.getPersonaId());
                    } else {
                        session.setAttribute("PersonaId", new Long(keycode));
                    }
                    SQLXML equipos = DAOAnteproyectos.getXMLRecords((Long) session.getAttribute("PersonaId"));
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(equipos.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos2.xsl"));
                } else {
                    user = (UserManager) session.getAttribute("user");
                    if (request.getParameter("step") != null) {
                        long step = new Long("" + request.getParameter("step"));
                        if (step == 2) {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOEquipos.getXMLRecords((Long) session.getAttribute("PersonaId"), DAOEquipos.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos2.xsl"));
                        } else {
                            if (user.isbClient()) {
                                out.println(XMLModder.XSLTransform(
                                        XMLModder.JoinDocs(DAOEquipos.getXMLRecords(user.getPersonaId(), DAOEquipos.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos2.xsl"));
                            } else {
                                out.println(XMLModder.XSLTransform(
                                        XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos.xsl"));
                            }
                        }
                    } else {
                        if (user.isbClient()) {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOEquipos.getXMLRecords(user.getPersonaId(),DAOEquipos.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos2.xsl"));
                        } else {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/monitor/equipos.xsl"));
                        }
                    }
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
                    SQLXML equipos = DAOEquipos.getXMLRecords(new Long(request.getParameter("mod")),DAOEquipos.F_REGISTRO);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(equipos.getString(),
                            new String[]{user.getPermisos()}), path + "../web/xsl/equipos_form.xsl"));

                } else if (ins != null) {
                    Conexion.autoConnect();
                    if (!ins.equals("true")) {
                        session.setAttribute("PersonaId", new Long(ins));
                    }
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML personas = DAOPFisicas.getXMLRecords("" + session.getAttribute("PersonaId"));
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(personas.getString(),
                            new String[]{user.getPermisos()}), path + "../web/xsl/equipos_form.xsl"));
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
                DAOEquipos daoequipo = new DAOEquipos(new Long(request.getParameter("inCode")),new Long(request.getParameter("inFKey")),
                        new Integer(request.getParameter("inType")), new Integer(request.getParameter("inNet")), request.getParameter("inPerson"), request.getParameter("inUser"), request.getParameter("inPass"),request.getParameter("inIP"));
                if (daoequipo.getlEquipoId() == 0) {
                    daoequipo.insert();
                } else {
                    daoequipo.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOEquipos daoequipo = new DAOEquipos();
                daoequipo.setlEquipoId(new Long(request.getParameter("keycode")));
                daoequipo.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOEquipos.getXMLRecords((Long) session.getAttribute("PersonaId"),DAOEquipos.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/equipos2.xsl"));
            } else if (request.getParameter("srch1") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPFisicas.searchXML(request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/equipos.xsl"));
            } else if (request.getParameter("srch2") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOEquipos.searchXML((Long) session.getAttribute("PersonaId"), request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/equipos2.xsl"));
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
