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

/**
 *
 * @author Moncho
 */
public class Observaciones extends HttpServlet {

    private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Observaciones</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Observaciones at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
                Long keycode = new Long(request.getParameter("fkey"));
                if (keycode != null) {
                    UserManager user = (UserManager) session.getAttribute("user");
                    SQLXML daoobservacion;
                    session.setAttribute("ReqId", keycode);
                    if (user.isbClient()) {
                        daoobservacion = DAOObservaciones.getXMLRecords(keycode, DAOObservaciones.F_NEW_CLIENT);
                    } else {
                        daoobservacion = DAOObservaciones.getXMLRecords(keycode, DAOObservaciones.F_NEW_DEVELOPER);
                    }
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoobservacion.getString(), user.getPermisos()), path + "../web/xsl/observaciones_form.xsl"));
                } else {
                    response.sendRedirect("login.html");
                }
            } else {
                session.invalidate();
                response.sendRedirect("login.html");
            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
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
                        XMLModder.JoinDocs(DAOObservaciones.getXMLRecords((Long) session.getAttribute("ReqId"), DAOObservaciones.F_LISTA).getString(), user.getPermisos()), path + "../web/xsl/observaciones.xsl"));

            } else if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                user=(UserManager) session.getAttribute("user");
                DAOObservaciones observacion = new DAOObservaciones(new Long(request.getParameter("inCode")), (Long) session.getAttribute("ReqId"), user.getUsuarioId(), new Long(request.getParameter("inUser")), (String) request.getParameter("inName"), request.getParameter("inDesc"));
                if (observacion.getlObservacionId() == 0) {
                    observacion.insert();
                } else {
                    observacion.update();
                }
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
