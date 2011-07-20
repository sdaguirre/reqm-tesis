package war;

import conexion.Conexion;
import dao.DAOParams;
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

public class Integrantes extends HttpServlet {

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
                SQLXML personas = DAOParams.getXMLRecords(DAOParams.EQUIPOS);
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(personas.getString(), user.getPermisos()), path + "../web/xsl/integrantes.xsl"));
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
                String mod = request.getParameter("mod"), nuevo = request.getParameter("ins");
                if (mod == null) {
                    if (nuevo == null) {
                        processRequest(request, response);
                    } else {
                        UserManager user;
                        request.setCharacterEncoding("UTF-8");
                        Conexion.autoConnect();
                        user = (UserManager) session.getAttribute("user");
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(null, user.getPermisos()), path + "../web/xsl/equipos_form.xsl"));
                    }
                } else {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOParams.getXMLRecord(DAOParams.EQUIPOS,new Integer(mod)).getString(), user.getPermisos()), path + "../web/xsl/equipos_form.xsl"));
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
                DAOParams deoequipo = new DAOParams(DAOParams.EQUIPOS,new Long(request.getParameter("inCode")),request.getParameter("inName"));
                if (deoequipo.getlParamId() == 0) {
                    deoequipo.insert();
                } else {
                    deoequipo.update();
                }
                response.sendRedirect("ok.html");
            } else {
                if (request.getParameter("del.x") != null) {
                    Conexion.autoConnect();
                    DAOParams daoequipo= new DAOParams(DAOParams.EQUIPOS);
                    daoequipo.setlParamId(new Long(request.getParameter("keycode")));
                    daoequipo.delete();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOParams.getXMLRecords(DAOParams.EQUIPOS).getString(), user.getPermisos()), path + "../web/xsl/equipos.xsl"));
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
        return "REQM System - Roles";
    }// </editor-fold>
}
