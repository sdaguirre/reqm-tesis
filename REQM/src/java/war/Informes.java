package war;

import conexion.Conexion;
import dao.DAOPFisicas;
import dao.DAOProyectos;
import dao.DAOObservaciones;
import dao.DAOPermisos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class Informes extends HttpServlet {

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
                        XMLModder.JoinDocs("", new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/informes.xsl"));
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
        PrintWriter out = null;
        try {
            if (session.isNew() || session == null) {
                Conexion.getConnection().disconnect();
                session.invalidate();
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                UserManager user;
                String str = request.getParameter("rep");
                int rep = new Integer((str != null ? str : "0"));
                str = request.getParameter("akey");
                long persona = new Long(str != null ? str : "0");
                str = request.getParameter("bkey");
                long project = new Long(str != null ? str : "0");
                if (rep != 0) {
                    out = response.getWriter();
                    session.setAttribute("Report", rep);
                    Conexion.autoConnect();
                    SQLXML personas = DAOPFisicas.getXMLRecords();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(personas.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/informes2.xsl"));
                } else if (persona != 0) {
                    out = response.getWriter();
                    session.setAttribute("RepKey1", persona);
                    Conexion.autoConnect();
                    SQLXML personas = DAOPFisicas.getXMLRecords(persona, DAOPFisicas.F_REPORT);
                    SQLXML proyectos = DAOProyectos.getXMLRecords(persona, DAOProyectos.F_CLIENTE);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), new String[]{personas.getString(), user.getPermisos()}), path + "../web/xsl/informes3.xsl"));
                } else if (project != 0) {
                    Map parametros = new HashMap();
                    ServletOutputStream stream;
                    JasperReport jasper = null;
                    switch ((Integer) session.getAttribute("Report")) {
                        case 1:
                            parametros = DAOProyectos.getReportHeader(project);
                            jasper = (JasperReport) JRLoader.loadObject(new FileInputStream(getServletContext().getRealPath("WEB-INF/jasper/EstadoReqs.jasper")));
                            break;
                        case 2:
                            jasper = (JasperReport) JRLoader.loadObject(new FileInputStream(getServletContext().getRealPath("WEB-INF/jasper/AvancePr.jasper")));
                            break;
                        default:
                    }
                    parametros.put("ProyectoKey", project);
                    byte[] report = JasperRunManager.runReportToPdf(jasper, parametros, Conexion.getConnection().getCon());
                    response.setContentType("application/pdf");
                    response.setHeader("Content-disposition", "inline; filename=informe.pdf");
                    response.setHeader("Cache-Control", "max-age=10");
                    response.setHeader("Pragma", "No-cache");
                    response.setDateHeader("Expires", 0);
                    response.setContentLength(report.length);
                    stream = response.getOutputStream();
                    stream.write(report, 0, report.length);
                    stream.flush();
                    stream.close();
                } else {
                    processRequest(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex.getMessage(), ex.getCause());
        } finally {
            if (out != null) {
                out.close();
            }
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
                        XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/pfisicas.xsl"));
            } else if (request.getParameter("srch") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPFisicas.searchXML(request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "../web/xsl/pfisicas.xsl"));
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
        return "REQM System - Informes";
    }// </editor-fold>
}
