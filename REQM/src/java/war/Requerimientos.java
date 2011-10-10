package war;

import conexion.Conexion;
import dao.DAORequerimientos;
import dao.DAOObservaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;

public class Requerimientos extends HttpServlet {

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
                String keycode = request.getParameter("prkey");
                if (keycode != null) {
                    session.setAttribute("ProyectoId", new Long(keycode));
                    SQLXML requerimientos = null;
                    user = (UserManager) session.getAttribute("user");
                    if (user.isbClient()) {
                        requerimientos = DAORequerimientos.getXMLRecords(new Long(keycode), DAORequerimientos.F_LISTA_CLIENTE);
                    } else {
                        requerimientos = DAORequerimientos.getXMLRecords(new Long(keycode), DAORequerimientos.F_LISTA);
                    }
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(requerimientos.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/requerimientos.xsl"));
                } else if (session.getAttribute("ProyectoId") != null) {
                    user = (UserManager) session.getAttribute("user");
                    if (user.isbClient()) {
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(DAORequerimientos.getXMLRecords((Long) session.getAttribute("ProyectoId"), DAORequerimientos.F_LISTA_CLIENTE).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/requerimientos.xsl"));
                    } else {
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(DAORequerimientos.getXMLRecords((Long) session.getAttribute("ProyectoId"), DAORequerimientos.F_LISTA).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/requerimientos.xsl"));
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
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                String ins = request.getParameter("ins"), approve = request.getParameter("approve");
                if (request.getParameter("mod") != null) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML requerimientos = DAORequerimientos.getXMLRecords(new Long(request.getParameter("mod")), DAORequerimientos.F_REQUERIMIENTO);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(requerimientos.getString(),
                            new String[]{user.getPermisos()}), path + "xsl/requerimientos_form.xsl"));
                } else if (ins != null) {
                    Conexion.autoConnect();
                    Long key = (Long) session.getAttribute("ProyectoId");
                    Long req = new Long(ins);
                    SQLXML requerimientos = null;
                    UserManager user = (UserManager) session.getAttribute("user");
                    if (req > 0) {
                        requerimientos = DAORequerimientos.getXMLRecords(req, DAORequerimientos.FO_REQPADRE);
                    } else {
                        if (key != null) {
                            requerimientos = DAORequerimientos.getXMLRecords(key, DAORequerimientos.FO_PROYECTO);
                        }
                    }
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(requerimientos.getString(),
                            new String[]{user.getPermisos()}), path + "xsl/requerimientos_form.xsl"));
                } else if (approve != null) {
                    session.setAttribute("ApproveKey", new Long(approve));
                    response.sendRedirect("reqsure.html");
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
            //SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                DAORequerimientos daorequerimiento = new DAORequerimientos(new Long(request.getParameter("inCode")), new Long(request.getParameter("inFKey")),
                        new Long(request.getParameter("inRPadre")), new Integer(request.getParameter("inType")), new Integer(request.getParameter("inEstado")),
                        new Integer(request.getParameter("inProgress")), request.getParameter("inName"), request.getParameter("inDesc"));
                if (daorequerimiento.getlRequerimientoId() == 0) {
                    daorequerimiento.insert();
                } else {
                    daorequerimiento.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAORequerimientos daorequerimiento = new DAORequerimientos();
                System.out.println(request.getParameter("keycode"));
                daorequerimiento.setlRequerimientoId(new Long(request.getParameter("keycode")));
                daorequerimiento.delete();
                user = (UserManager) session.getAttribute("user");
                SQLXML proyectos = null;
                if (user.isbClient()) {
                    proyectos = DAORequerimientos.getXMLRecords((Long) session.getAttribute("ProyectoId"), DAORequerimientos.F_LISTA_CLIENTE);
                } else {
                    proyectos = DAORequerimientos.getXMLRecords((Long) session.getAttribute("ProyectoId"), DAORequerimientos.F_LISTA);
                }
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(proyectos.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/requerimientos.xsl"));
            } else if (request.getParameter("accept.x") != null) {
                user = (UserManager) session.getAttribute("user");
                if (user.isbClient()) {
                    DAORequerimientos.approve((Long) session.getAttribute("ApproveKey"), DAORequerimientos.A_CLIENTE);
                } else {
                    DAORequerimientos.approve((Long) session.getAttribute("ApproveKey"), DAORequerimientos.A_EMPRESA);
                }
                response.sendRedirect("ok.html");
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
        return "REQM System - Requerimientos";
    }// </editor-fold>
}
