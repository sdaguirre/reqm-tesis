/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOUsuarios;
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

/**
 *
 * @author Moncho
 */
public class Reqm extends HttpServlet {
    //private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";
    private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Reqm</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reqm at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String exit = request.getParameter("drop");
        if (exit != null && exit.equals("true")) {
            try {
                Conexion.Destroy();
            } catch (SQLException ex) {
                Logger.getLogger(Reqm.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().invalidate();
            response.sendRedirect("login.html");
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);
            UserManager user;
            if (session.isNew()) {
                Conexion.autoConnect();
                SQLXML permisos;
                try {
                    permisos = DAOUsuarios.LoginUser(request.getParameter("usr"), request.getParameter("pwd"));
                } catch (SQLException sql) {
                    Conexion.Destroy();
                    throw new ServletException("", sql);
                }
                if (permisos.getString().length() > 0) {
                    user = new UserManager();
                    user.setUsername(request.getParameter("usr"));
                    user.setPermisos(permisos.getString());
                    session.setAttribute("user", user);
                    out.println(XMLModder.XSLTransform(permisos.getString(), path + "xsl/template.xsl"));
                } else {
                    Conexion.Destroy();
                    request.getSession().invalidate();
                    response.sendRedirect("login.html");
                }
            } else {
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(user.getPermisos(), path + "xsl/template.xsl"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Reqm.class.getName()).log(Level.SEVERE, null, ex);
            request.getSession().invalidate();
            response.sendRedirect("login.html");
            
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
        return "REQM System - Reqm";
    }// </editor-fold>
}
