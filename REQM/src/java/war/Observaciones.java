/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOObservaciones;
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
import org.w3c.dom.Document;

/**
 *
 * @author Moncho
 */
public class Observaciones extends HttpServlet {

    private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(false);
            UserManager user;
            if (!session.isNew()) {
                user = (UserManager) session.getAttribute("user");
                session.setAttribute("inbox", true);
                Conexion.autoConnect();
                SQLXML observaciones = DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_RECIBIDOS);
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(observaciones.getString(), new String[]{user.getPermisos(), "<inbox>true</inbox>"}), path + "../web/xsl/observaciones.xsl"));
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        try {
            if (!session.isNew()) {
                Conexion.autoConnect();
                Long keycode = new Long((request.getParameter("fkey") != null ? request.getParameter("fkey") : "0"));
                Long mail = new Long((request.getParameter("mail") != null ? request.getParameter("mail") : "0"));
                Long reply = new Long((request.getParameter("reply") != null ? request.getParameter("reply") : "0"));
                Long delete = new Long((request.getParameter("del") != null ? request.getParameter("del") : "0"));
                String received = request.getParameter("rcv");
                String sent = request.getParameter("sent");
                if (keycode > 0) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML daoobservacion;
                    session.setAttribute("ReqId", keycode);
                    if (user.isbClient()) {
                        daoobservacion = DAOObservaciones.getXMLRecords(keycode, DAOObservaciones.F_NEW_CLIENT);
                    } else {
                        daoobservacion = DAOObservaciones.getXMLRecords(keycode, DAOObservaciones.F_NEW_DEVELOPER);
                    }
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoobservacion.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/observaciones_form.xsl"));
                } else if (sent != null) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    session.setAttribute("inbox", false);
                    Conexion.autoConnect();
                    SQLXML observaciones = DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_ENVIADOS);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(observaciones.getString(), new String[]{user.getPermisos(), "<inbox>false</inbox>"}), path + "../web/xsl/observaciones.xsl"));
                } else if (received != null) {
                    session.setAttribute("inbox", true);
                    UserManager user = (UserManager) session.getAttribute("user");
                    Conexion.autoConnect();
                    SQLXML observaciones = DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_RECIBIDOS);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(observaciones.getString(), new String[]{user.getPermisos(), "<inbox>true</inbox>"}), path + "../web/xsl/observaciones.xsl"));
                } else if (mail > 0) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML daoobservacion;
                    Document observer;
                    if ((Boolean) session.getAttribute("inbox")) {
                        observer = XMLModder.JoinDocs(DAOObservaciones.getXMLRecords(mail, DAOObservaciones.F_OBSERVACION).getString(),new String[]{user.getPermisos(),"<inbox>true</inbox>"});
                    } else {
                        observer = XMLModder.JoinDocs(DAOObservaciones.getXMLRecords(mail, DAOObservaciones.F_OBSERVACION_SENT).getString(),new String[]{user.getPermisos(),"<inbox>false</inbox>"});
                    }
                    out.println(XMLModder.XSLTransform(observer, path + "../web/xsl/observaciones2.xsl"));
                } else if (reply > 0) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML daoobservacion = DAOObservaciones.getXMLRecords(reply, DAOObservaciones.F_REPLY);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoobservacion.getString(), new String[]{user.getPermisos()}), path + "../web/xsl/observaciones_form.xsl"));
                } else if (delete > 0) {
                    Conexion.autoConnect();
                    DAOObservaciones observacion = new DAOObservaciones();
                    observacion.setlObservacionId(delete);
                    observacion.delete();
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML observaciones = DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_RECIBIDOS);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(observaciones.getString(), new String[]{user.getPermisos(), "<inbox>true</inbox>"}), path + "../web/xsl/observaciones.xsl"));
                } else {
                    processRequest(request, response);
                }
            } else {
                session.invalidate();
                response.sendRedirect("login.html");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
//            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOObservaciones observacion = new DAOObservaciones();
                observacion.setlObservacionId(new Long(request.getParameter("keycode")));
                observacion.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOObservaciones.getXMLRecords((Long) session.getAttribute("ReqId"), DAOObservaciones.F_RECIBIDOS).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/observaciones.xsl"));

            } else if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                String reqKey = request.getParameter("inReq");
                DAOObservaciones observacion;
                if (reqKey != null) {
                    observacion = new DAOObservaciones(new Long(request.getParameter("inCode")), new Long(reqKey), user.getUsuarioId(), new Long(request.getParameter("inUser")), (String) request.getParameter("inName"), request.getParameter("inDesc"));
                } else {
                    observacion = new DAOObservaciones(new Long(request.getParameter("inCode")), (Long) session.getAttribute("ReqId"), user.getUsuarioId(), new Long(request.getParameter("inUser")), (String) request.getParameter("inName"), request.getParameter("inDesc"));
                }
                observacion.insert();
            }
            response.sendRedirect("ok.html");
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
        return "REQM - Observaciones";

    }// </editor-fold>
}
