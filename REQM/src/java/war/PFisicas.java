package war;

import conexion.Conexion;
import dao.DAOPFisicas;
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

public class PFisicas extends HttpServlet {

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
                SQLXML personas = DAOPFisicas.getXMLRecords();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(personas.getString(), user.getPermisos()), path + "../web/xsl/pfisicas.xsl"));
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
            if (session.isNew() || session == null) {
                Conexion.getConnection().disconnect();
                session.invalidate();
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                String mod = request.getParameter("mod"), nuevo = request.getParameter("ins");
                if (mod != null) {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML daopfisicas = DAOPFisicas.getXMLRecords(mod);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daopfisicas.getString(), user.getPermisos()), path + "../web/xsl/form.xsl"));
                } else if (nuevo != null) {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs("", user.getPermisos()), path + "../web/xsl/form.xsl"));
                } else {
                    processRequest(request, response);
                }
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
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                DAOPFisicas pfisica = new DAOPFisicas(new Long(request.getParameter("inCode")), request.getParameter("inName"));
                if (pfisica.getlPersonaId() == 0) {
                    pfisica.insert();
                } else {
                    pfisica.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOPFisicas pfisica = new DAOPFisicas();
                pfisica.setlPersonaId(new Long(request.getParameter("keycode")));
                pfisica.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), user.getPermisos()), path + "../web/xsl/pfisicas.xsl"));
            } else if (request.getParameter("srch") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPFisicas.searchXML(request.getParameter("inSearch")).getString(), user.getPermisos()), path + "../web/xsl/pfisicas.xsl"));
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
        return "REQM System - PFisicas";
    }// </editor-fold>
}
