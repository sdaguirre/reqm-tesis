package war;

import conexion.Conexion;
import dao.DAOPFisicas;
import dao.DAOParams;
import dao.DAOProyectos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;

public class Proyectos extends HttpServlet {

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
                    SQLXML proyectos = DAOProyectos.getXMLRecords(new Long(keycode), DAOProyectos.f_cliente);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), user.getPermisos()), path + "../web/xsl/proyectos2.xsl"));
                } else if (request.getParameter("apkey") != null) {
                    keycode = request.getParameter("apkey");
                    session.setAttribute("AnteproyectoId", new Long(keycode));
                    SQLXML proyectos = DAOProyectos.getXMLRecords(new Long(keycode), DAOProyectos.f_anteprotecto);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), user.getPermisos()), path + "../web/xsl/proyectos2.xsl"));
                } else {
                    user = (UserManager) session.getAttribute("user");
                    if (user.isbClient()) {
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(DAOProyectos.getXMLRecords(user.getPersonaId(), DAOProyectos.f_cliente).getString(), user.getPermisos()), path + "../web/xsl/proyectos2.xsl"));
                    } else {
                        SQLXML personas = DAOPFisicas.getXMLRecords();
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(personas.getString(), user.getPermisos()), path + "../web/xsl/proyectos.xsl"));
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
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        try {
            if (session == null || session.isNew()) {
                Conexion.getConnection().disconnect();
                session.invalidate();
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                String ins = request.getParameter("ins");
                if (request.getParameter("mod") != null) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML proyectos = DAOProyectos.getXMLRecords(new Long(request.getParameter("mod")), DAOProyectos.f_proyecto);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(),
                            new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.TIPOS).getString(),
                                DAOParams.getXMLRecords(DAOParams.CATEGORIAS).getString()}), path + "../web/xsl/proyectos_form.xsl"));
                } else if (ins != null) {
// -------------------------     REGISTRO DE PROYECTOS      ---------------------
                    Conexion.autoConnect();
                    Long key = (Long) session.getAttribute("PersonaId");
                    if (key != null) {
                        UserManager user = (UserManager) session.getAttribute("user");
                        SQLXML proyecto = DAOProyectos.getXMLRecords(key, DAOProyectos.fo_client);
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(proyecto.getString(),
                                new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.TIPOS).getString(),
                                    DAOParams.getXMLRecords(DAOParams.CATEGORIAS).getString()}), path + "../web/xsl/proyectos_form.xsl"));
                    } else {
                        UserManager user = (UserManager) session.getAttribute("user");
                        SQLXML proyecto = DAOProyectos.getXMLRecords((Long) session.getAttribute("AnteproyectoId"), DAOProyectos.fo_ap);
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(proyecto.getString(),
                                new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.TIPOS).getString(),
                                    DAOParams.getXMLRecords(DAOParams.CATEGORIAS).getString()}), path + "../web/xsl/proyectos_form.xsl"));
                    }

                } else {
                    processRequest(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Conexion.getConnection().disconnect();
            } catch (SQLException ex1) {
                Logger.getLogger(Proyectos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            session.invalidate();
            response.sendRedirect("login.html");
            throw new ServletException(ex.getMessage(), ex.getCause());
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
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                DAOProyectos daoproyecto = new DAOProyectos(new Long(request.getParameter("inCode")), new Long(request.getParameter("inAP")),
                        new Integer(request.getParameter("inType")), new Integer(request.getParameter("inCats")), 1,
                        new java.sql.Date(dateFormatter.parse(request.getParameter("inDtIn")).getTime()), null, request.getParameter("inName"), request.getParameter("inDesc"), 0);
                if (daoproyecto.getlProyectoId() == 0) {
                    daoproyecto.insert();
                } else {
                    daoproyecto.setdCostoAmt(new Double(request.getParameter("inCosto")));
                    String date = request.getParameter("inDtFin");
                    if (date != null && date.length() == 10) {
                        daoproyecto.setDtFechaFinDt(new java.sql.Date(dateFormatter.parse(date).getTime()));
                    }
                    daoproyecto.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOProyectos daoproyecto = new DAOProyectos();
                daoproyecto.setlProyectoId(new Long(request.getParameter("keycode")));
                daoproyecto.delete();
                user = (UserManager) session.getAttribute("user");
                if (session.getAttribute("AnteproyectoId") != null) {
                    SQLXML proyectos = DAOProyectos.getXMLRecords((Long) session.getAttribute("AnteproyectoId"), DAOProyectos.f_anteprotecto);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), user.getPermisos()), path + "../web/xsl/proyectos2.xsl"));
                } else {
                    SQLXML proyectos = DAOProyectos.getXMLRecords((Long) session.getAttribute("PersonaId"), DAOProyectos.f_cliente);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), user.getPermisos()), path + "../web/xsl/proyectos2.xsl"));
                }
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
        return "REQM System - Proyectos";
    }// </editor-fold>
}
