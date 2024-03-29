/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
import dao.DAOObservaciones;
import dao.DAOReqmDocumentos;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
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

/**
 *
 * @author Moncho
 */
public class APDocumentos extends HttpServlet {

    private String path = "C:/Users/Moncho/Documents/NetBeansProjects/REQM/web/";
    //private String path = "/home/bluefox/NetBeansProjects/REQM/web/";

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
                    SQLXML daoapdocumentos = DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.ANTEPROYECTOS, keycode, DAOReqmDocumentos.F_LISTA);
                    session.setAttribute("APId", keycode);
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoapdocumentos.getString(), new String[]{user.getPermisos(),
                            DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/apdocumentos.xsl"));
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
                    SQLXML apdocumentos = DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.ANTEPROYECTOS, new Long(mod), DAOReqmDocumentos.F_DOCUMENTO);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(apdocumentos.getString(), new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/apdocumentos_form.xsl"));
                } else if (nuevo != null) {
                    PrintWriter out = response.getWriter();
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.ANTEPROYECTOS, (Long) session.getAttribute("APId"), DAOReqmDocumentos.F_NEW).getString(),
                            new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/apdocumentos_form.xsl"));
                } else if (down != null) {
                    DAOReqmDocumentos apdoc = new DAOReqmDocumentos(DAOReqmDocumentos.ANTEPROYECTOS);
                    byte[] doc = apdoc.getDocument(new Long(down));
                    if (doc != null) {
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + apdoc.createDocName() + "\"");
                        response.setHeader("cache-control", "no-cache");
                        response.setContentType(apdoc.getsReqmDocumentoMIME());
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
                            XMLModder.JoinDocs("<root><key>" + upload + "</key></root>", new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/apdocumentos_form.xsl"));
                } else {
                    processRequest(request, response);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex.getMessage(), ex.getCause());
        } finally {
            /*if (out != null) {
            out.flush();
            out.close();
            } else {
            out.close();
            }*/
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
                DAOReqmDocumentos apdocumento = new DAOReqmDocumentos(DAOReqmDocumentos.ANTEPROYECTOS);
                apdocumento.setlReqmDocumentoId(new Long(request.getParameter("keycode")));
                apdocumento.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.ANTEPROYECTOS, (Long) session.getAttribute("APId"), DAOReqmDocumentos.F_LISTA).getString(),new String[]{user.getPermisos(),DAOObservaciones.getXMLRecords(user.getUsuarioId(),DAOObservaciones.F_NOTIFY).getString()}), path + "xsl/apdocumentos.xsl"));

            } else {
                HashMap<String, Object> hash = new HashMap<String, Object>();
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(4096);
                factory.setRepository(new File("C:/"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(5242880);
                List<FileItem> items = upload.parseRequest(request);
                //System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                //System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        hash.put(item.getFieldName(), item.getString());
                        /*String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        System.out.println("Campo: " + fieldname + " - Valor: " + fieldvalue);*/
                    } else {
                        // Process form file field (input type="file").
                        hash.put(item.getFieldName(), item);
                        /*String fieldname = item.getFieldName();
                        System.out.println("ENTRADA: " + item.getSize());
                        /* System.out.println("Archivo: " + fieldname);
                        System.out.println("ArchiBO: " + item.isInMemory());
                        System.out.println("ArcMIME: " + item.getContentType());
                        System.out.println("ArcSIZE: " + item.getSize());
                        System.out.println(item.get().length);
                        //String filename = FilenameUtils.getName(item.getName());
                        //InputStream filecontent = item.getInputStream();
                        // ... (do your job here)*/
                    }
                }
                //System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                //System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                if (hash.get("ok.x") != null) {
                    Conexion.autoConnect();
                    DAOReqmDocumentos apdocumento = new DAOReqmDocumentos(DAOReqmDocumentos.ANTEPROYECTOS, new Long((String) hash.get("inCode")), (Long) session.getAttribute("APId"), null, (String) hash.get("inName"), (FileItem) hash.get("inFile"));
                    if (apdocumento.getlReqmDocumentoId() == 0) {
                        apdocumento.insert();
                    } else if (apdocumento.getlFReqmId() != 0 && apdocumento.getsReqmDocumentoNm() != null) {
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        String date = "" + hash.get("inDate");
                        if (date != null && date.length() == 10) {
                            apdocumento.setDtReqmDocumentoDt(new java.sql.Date(dateFormatter.parse(date).getTime()));
                        }
                        apdocumento.update();
                    } else {
                        apdocumento.upload();
                    }

                    response.sendRedirect("ok.html");
                }
            }
        } catch (FileUploadException fex) {
            response.sendRedirect("big.html");
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
        return "REQM - Documentos Anteproyecto";
    }// </editor-fold>
}
