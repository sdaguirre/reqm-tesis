package war;

import conexion.Conexion;
import dao.DAOIntegrantes;
import dao.DAOParams;
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
                Long keycode = new Long(request.getParameter("fkey"));
                if (keycode != null) {
                    SQLXML daointegrantes = DAOIntegrantes.getXMLRecords(keycode,DAOIntegrantes.F_EQUIPO);
                    session.setAttribute("EquipoId", keycode);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daointegrantes.getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/integrantes.xsl"));
                } else {
                    response.sendRedirect("login.html");
                }
            } else {
                Conexion.getConnection().disconnect();
                request.getSession().invalidate();
                response.sendRedirect("login.html");
            }
        } catch (Exception ex) {
            //Logger.getLogger(ADM.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
                                XMLModder.JoinDocs(DAOIntegrantes.getXMLRecords((Long)session.getAttribute("EquipoId"),DAOIntegrantes.F_NEW).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/integrantes_form.xsl"));
                    }
                } else {
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOParams.getXMLRecord(DAOParams.EQUIPOS, new Integer(mod)).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/integrantes_form.xsl"));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                DAOIntegrantes daointegrante = new DAOIntegrantes(new Long(request.getParameter("inCode")),Integer.parseInt(""+session.getAttribute("EquipoId")),new Long(request.getParameter("inEmp")));
                if (daointegrante.getlIntegranteId() == 0) {
                    daointegrante.insert();
                } else {
                    daointegrante.update();
                }
                response.sendRedirect("ok.html");
            } else {
                if (request.getParameter("del.x") != null) {
                    Conexion.autoConnect();
                    DAOIntegrantes daointegrante = new DAOIntegrantes();
                    daointegrante.setlIntegranteId(new Long(request.getParameter("keycode")));
                    daointegrante.delete();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOIntegrantes.getXMLRecords((Long)session.getAttribute("EquipoId"), DAOIntegrantes.F_EQUIPO).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/equipos.xsl"));
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
