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
    private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";
    //private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(false);
            UserManager user = (UserManager) session.getAttribute("user");
            if (!session.isNew() && user.isLogged()) {
                out.println(XMLModder.XSLTransform(user.getBrief(), path + "xsl/briefing.xsl"));
            } else {
                session.invalidate();
                response.sendRedirect("login.html");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
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
                if(Conexion.getConnection()!=null)
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
                Object[] permisos;
                SQLXML xmldata;
                SQLXML xmlbrief;
                try {
                    permisos = DAOUsuarios.LoginUser(request.getParameter("usr"), request.getParameter("pwd"));
                    xmldata=(SQLXML)permisos[0];
                    xmlbrief=(SQLXML)permisos[3];
                } catch (SQLException sql) {
                    Conexion.Destroy();
                    throw new ServletException("", sql);
                }
                if (xmldata.getString().length() > 0) {
                    user = new UserManager();
                    user.setUsername(request.getParameter("usr"));
                    user.setPermisos(xmldata.getString());
                    user.setBrief(XMLModder.JoinDocs(xmlbrief.getString(),((SQLXML)permisos[4]).getString()));
                    user.setPersonaId((Long)permisos[1]);
                    user.setbClient((Boolean)permisos[2]);
                    user.setLogged(true);
                    session.setAttribute("user", user);
                    out.println(XMLModder.XSLTransform(XMLModder.JoinDocs(user.getBrief(),user.getPermisos()), path + "xsl/briefing.xsl"));
                } else {
                    Conexion.Destroy();
                    request.getSession().invalidate();
                    response.sendRedirect("login.html");
                }
            } else {
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(user.getBrief(), path + "xsl/briefing.xsl"));
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
