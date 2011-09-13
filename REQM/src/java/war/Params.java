/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.*;
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
public class Params extends HttpServlet {

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
                Conexion.autoConnect();
                SQLXML parametros = DAOParams.getXMLRecords(DAOParams.CARGOS);
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(parametros.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.getXMLParamNm(DAOParams.CARGOS)}), path + "../web/xsl/params.xsl"));
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
                String mod = request.getParameter("mod"), nuevo = request.getParameter("ins"), prm = request.getParameter("prm");
                if (mod == null) {
                    if (nuevo == null) {
                        if (prm == null) {
                            processRequest(request, response);
                        } else {
                            request.setCharacterEncoding("UTF-8");
                            Conexion.autoConnect();
                            SQLXML parametros = DAOParams.getXMLRecords("tbl" + prm);
                            UserManager user = (UserManager) session.getAttribute("user");
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(parametros.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.formatXMLParamNm(prm)}), path + "../web/xsl/params.xsl"));
                        }
                    } else {
                        request.setCharacterEncoding("UTF-8");
                        Conexion.autoConnect();
                        UserManager user = (UserManager) session.getAttribute("user");
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs("",new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.formatXMLParamNm(prm)}), path + "../web/xsl/params_form.xsl"));
                    }
                } else {
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML parametro = DAOParams.getXMLRecord("tbl" + prm, new Integer(mod));
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(parametro.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.formatXMLParamNm(prm)}), path + "../web/xsl/params_form.xsl"));
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
                DAOParams parametro = new DAOParams("tbl" + request.getParameter("prm"), new Long(request.getParameter("inCode")), request.getParameter("inName"));
                if (parametro.getlParamId() == 0) {
                    parametro.insert();
                } else {
                    parametro.update();
                }
                response.sendRedirect("ok.html");
            } else {
                if (request.getParameter("del.x") != null) {
                    Conexion.autoConnect();
                    DAOParams parametro = new DAOParams("tbl" + request.getParameter("prm"));
                    parametro.setlParamId(new Long(request.getParameter("keycode")));
                    parametro.delete();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOParams.getXMLRecords("tbl" + request.getParameter("prm")).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.formatXMLParamNm(request.getParameter("prm"))}), path + "../web/xsl/params.xsl"));
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
        return "REQM System - Params";
    }// </editor-fold>
}
