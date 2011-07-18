/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOParams;
import dao.DAOPermisos;
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
public class Permisos extends HttpServlet {

    private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";
    //private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        try {
            if (!session.isNew()) {
                Conexion.autoConnect();
                Long keycode = new Long(request.getParameter("fkey"));
                if (keycode != null) {
                    SQLXML daopermisos = DAOPermisos.getXMLRecords(keycode,DAOPermisos.F_ROL);
                    session.setAttribute("RolId", keycode);
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daopermisos.getString(), user.getPermisos()), path + "../web/xsl/permisos.xsl"));
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
                String mod = request.getParameter("mod"), nuevo = request.getParameter("ins"),checker = request.getParameter("checker");
                if(checker != null){
                    DAOPermisos.changeAccess(request.getParameter("key"), checker);
                    SQLXML daopermisos = DAOPermisos.getXMLRecords((Long)session.getAttribute("RolId"),DAOPermisos.F_ROL);
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daopermisos.getString(), user.getPermisos()), path + "../web/xsl/permisos.xsl"));
                }else if (mod == null) {
                    if (nuevo == null) {
                        processRequest(request, response);
                    } else {
                        UserManager user;
                        request.setCharacterEncoding("UTF-8");
                        Conexion.autoConnect();
                        user = (UserManager) session.getAttribute("user");
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(null, new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.SITIOS).getString()}), path + "../web/xsl/permisos_form.xsl"));
                    }
                } else {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML daopermisos = DAOPermisos.getXMLRecords(new Long(mod),DAOPermisos.F_PERMISO);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daopermisos.getString(), new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.SITIOS).getString()}), path + "../web/xsl/permisos_form.xsl"));
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
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
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                DAOPermisos daopermiso = new DAOPermisos(new Long(request.getParameter("inCode")),
                        new Integer(request.getParameter("inFKey")),new Integer(request.getParameter("inSite")), Boolean.valueOf(request.getParameter("inSelect")),
                        Boolean.valueOf(request.getParameter("inInsert")),Boolean.valueOf(request.getParameter("inMod")),Boolean.valueOf(request.getParameter("inDel")));
                if (daopermiso.getlPermisoId() == 0) {
                    daopermiso.insert();
                } else {
                    daopermiso.update();
                }
                response.sendRedirect("ok.html");
            } else {
                if (request.getParameter("del.x") != null) {
                    Conexion.autoConnect();
                    DAOPermisos daopermiso = new DAOPermisos();
                    daopermiso.setlPermisoId(new Long(request.getParameter("keycode")));
                    daopermiso.delete();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOPermisos.getXMLRecords((Long)session.getAttribute("RolId"),DAOPermisos.F_ROL).getString(), user.getPermisos()), path + "../web/xsl/permisos.xsl"));
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
        return "REQM - Permisos de Sitios";
    }// </editor-fold>
}
