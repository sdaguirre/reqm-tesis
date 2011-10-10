/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOEmpleados;
import dao.DAOObservaciones;
import dao.DAOParams;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
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
public class Empleados extends HttpServlet {

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
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOEmpleados.getXMLRecords().getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/empleados.xsl"));
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
                if (mod == null) {
                    if (nuevo == null) {
                        processRequest(request, response);
                    } else {
                        UserManager user;
                        request.setCharacterEncoding("UTF-8");
                        Conexion.autoConnect();
                        user = (UserManager) session.getAttribute("user");
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs("",new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.getXMLRecords(DAOParams.CARGOS).getString()}), path + "xsl/empleados_form.xsl"));
                    }
                } else {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML daoempleados = DAOEmpleados.getXMLRecords(new Long(mod));
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoempleados.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.getXMLRecords(DAOParams.CARGOS).getString()}), path + "xsl/empleados_form.xsl"));
                }

            }
        } catch (Exception ex) {
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
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            response.setContentType("text/html;charset=UTF-8");
            UserManager user;
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("ok.x") != null) {
                Conexion.autoConnect();
                DAOEmpleados empleado = new DAOEmpleados(new Long(request.getParameter("inCode")), request.getParameter("inName"), new Integer(request.getParameter("inType")),
                        new Double(request.getParameter("inPay")), new java.sql.Date(dateFormatter.parse(request.getParameter("inDtIn")).getTime()), null, new Integer(request.getParameter("inActive")));
                if (empleado.getlEmpleadoId() == 0) {
                    empleado.insert();
                } else {
                    empleado.setlPersonaId(new Long(request.getParameter("inFKey")));
                    String date = request.getParameter("inDtOut");
                    if (date != null && date.length() == 10) {
                        empleado.setDtEgresoDt(new java.sql.Date(dateFormatter.parse(date).getTime()));
                    }
                    empleado.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOEmpleados empleado = new DAOEmpleados();
                empleado.setlEmpleadoId(new Long(request.getParameter("keycode")));
                empleado.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOEmpleados.getXMLRecords().getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString(),DAOParams.getXMLRecords(DAOParams.CARGOS).getString()}), path + "xsl/empleados.xsl"));
            } else if (request.getParameter("srch") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOEmpleados.searchXML(request.getParameter("inSearch")).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/pfisicas.xsl"));
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
        return "REQM System - Empleados";
    }// </editor-fold>
}
