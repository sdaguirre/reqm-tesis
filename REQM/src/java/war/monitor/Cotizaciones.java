package war.monitor;

import conexion.Conexion;
import dao.DAOCotizaciones;
import dao.DAOPFisicas;
import dao.DAOObservaciones;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.UserManager;
import libs.XMLModder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Cotizaciones extends HttpServlet {

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
                String keycode = request.getParameter("fkey");
                if (keycode != null) {
                    user = (UserManager) session.getAttribute("user");
                    if (user.isbClient()) {
                        session.setAttribute("PersonaId", user.getPersonaId());
                    } else {
                        session.setAttribute("PersonaId", new Long(keycode));
                    }
                    SQLXML proyectos = DAOCotizaciones.getXMLRecords(new Long(keycode), DAOCotizaciones.F_LISTA);
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(proyectos.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones2.xsl"));
                } else {
                    user = (UserManager) session.getAttribute("user");
                    if (user.isbClient()) {
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(DAOCotizaciones.getXMLRecords(user.getPersonaId(), DAOCotizaciones.F_LISTA).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones2.xsl"));
                    } else {
                        SQLXML personas = DAOPFisicas.getXMLRecords();
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(personas.getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones.xsl"));
                    }
                }
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
        try {
            if (session.isNew() || session == null) {
                Conexion.getConnection().disconnect();
                session.invalidate();
                response.sendRedirect("login.html");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                String mod = request.getParameter("mod"), nuevo = request.getParameter("ins"), down = request.getParameter("down"), upload = request.getParameter("upl");
                if (mod != null) {
                    PrintWriter out = response.getWriter();
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML cotizacion = DAOCotizaciones.getXMLRecords(new Long(mod), DAOCotizaciones.F_DOCUMENTO);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(cotizacion.getString(), new String[]{user.getPermisos(),}), path + "xsl/monitor/cotizaciones_form.xsl"));
                } else if (nuevo != null) {
                    if(!nuevo.equals("true")){
                        session.setAttribute("PersonaId", new Long(nuevo));
                    }
                    PrintWriter out = response.getWriter();
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOCotizaciones.getXMLRecords((Long) session.getAttribute("PersonaId"), DAOCotizaciones.F_NEW).getString(),
                            new String[]{user.getPermisos()}), path + "xsl/monitor/cotizaciones_form.xsl"));
                } else if (down != null) {
                    DAOCotizaciones cotizacion = new DAOCotizaciones();
                    byte[] doc = cotizacion.getDocument(new Long(down));
                    if (doc != null) {
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + cotizacion.createDocName() + "\"");
                        response.setHeader("cache-control", "no-cache");
                        response.setContentType(cotizacion.getsReqmDocumentoMIME());
                        response.getOutputStream().write(doc, 0, doc.length);
                        response.getOutputStream().flush();
                        response.getOutputStream().close();
                    }
                } else if (upload != null) {
                    PrintWriter out = response.getWriter();
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs("<root><key>" + upload + "</key></root>", new String[]{user.getPermisos()}), path + "xsl/monitor/cotizaciones_form.xsl"));
                } else {
                    processRequest(request, response);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex.getMessage(), ex.getCause());
        } finally {

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
//            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOCotizaciones cotizacion = new DAOCotizaciones();
                cotizacion.setlReqmDocumentoId(new Long(request.getParameter("keycode")));
                cotizacion.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOCotizaciones.getXMLRecords((Long) session.getAttribute("PRId"), DAOCotizaciones.F_LISTA).getString(), new String[]{user.getPermisos(),
                            DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones2.xsl"));
            } else if (request.getParameter("srch1") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOPFisicas.searchXML(request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones.xsl"));
            } else if (request.getParameter("srch2") != null) {
                Conexion.autoConnect();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOCotizaciones.searchXML((Long) session.getAttribute("PersonaId"), request.getParameter("inSearch")).getString(), new String[]{user.getPermisos(), DAOObservaciones.getXMLRecords(user.getUsuarioId(), DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/monitor/cotizaciones2.xsl"));
            }else{
                HashMap<String, Object> hash = new HashMap<String, Object>();
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(4096);
                factory.setRepository(new File("C:/"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(5242880);
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        hash.put(item.getFieldName(), item.getString());
                    } else {
                        hash.put(item.getFieldName(), item);
                    }
                }
                if (hash.get("ok.x") != null) {
                    Conexion.autoConnect();
                    DAOCotizaciones cotizacion = null;
                    if (hash.get("inClient") != null) {
                        cotizacion = new DAOCotizaciones(new Long((String) hash.get("inCode")), new Long((String) hash.get("inClient")), new Integer((String) hash.get("inState")), null, (String) hash.get("inName"), (FileItem) hash.get("inFile"));
                    } else {
                        cotizacion = new DAOCotizaciones(new Long((String) hash.get("inCode")),(FileItem) hash.get("inFile"));
                    }
                    if (cotizacion.getlReqmDocumentoId() == 0) {
                        cotizacion.insert();
                    } else if (cotizacion.getlFReqmId() != 0 && cotizacion.getsReqmDocumentoNm() != null) {
                        cotizacion.setiEstadoFl(new Integer((String) hash.get("inState")));
                        cotizacion.update();
                    } else {
                        cotizacion.upload();
                    }

                    response.sendRedirect("ok.html");
                }
            }
        } catch (FileUploadException fex) {
            fex.printStackTrace();
            response.sendRedirect("big.html");
//            throw new ServletException(fex.getMessage(), fex.getCause());
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
        return "REQM System - Cotizaciones";
    }// </editor-fold>
}
