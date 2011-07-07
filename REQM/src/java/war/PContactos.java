/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOPContactos;
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

/**
 *
 * @author Moncho
 */
public class PContactos extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        try {
            if (!session.isNew()) {
                Conexion.autoConnect();
                Long keycode = new Long(request.getParameter("fkey"));
                if (keycode != null) {
                    SQLXML daocontactos = DAOPContactos.getXMLRecords(keycode);
                    session.setAttribute("PFisicaId", keycode);
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daocontactos.getString(), user.getPermisos()), path + "../web/xsl/pcontactos.xsl"));
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
                if (mod == null) {
                    if (nuevo == null) {
                        processRequest(request, response);
                    } else {
                        UserManager user;
                        request.setCharacterEncoding("UTF-8");
                        Conexion.autoConnect();
                        user = (UserManager) session.getAttribute("user");
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(null, new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.CONTACTOS).getString()}), path + "../web/xsl/pcontactos_form.xsl"));
                    }
                } else {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML daopcontactos = DAOPContactos.getXMLRecords(0, new Long(mod));
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daopcontactos.getString(), new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.CONTACTOS).getString()}), path + "../web/xsl/pcontactos_form.xsl"));
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
                DAOPContactos contacto = new DAOPContactos(new Long(request.getParameter("inCode")),
                        (Long) session.getAttribute("PFisicaId"), new Integer(request.getParameter("inType")), request.getParameter("inName"));
                if (contacto.getlContactoId() == 0) {
                    contacto.insert();
                } else {
                    contacto.update();
                }
                response.sendRedirect("ok.html");
            } else {
                if (request.getParameter("del.x") != null) {
                    Conexion.autoConnect();
                    DAOPContactos contacto = new DAOPContactos();
                    contacto.setlContactoId(new Long(request.getParameter("keycode")));
                    contacto.delete();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOPContactos.getXMLRecords((Long)session.getAttribute("PFisicaId")).getString(), user.getPermisos()), path + "../web/xsl/pcontactos.xsl"));
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
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "REQM - PContactos";
    }// </editor-fold>
}
