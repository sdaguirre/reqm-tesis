/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import conexion.Conexion;
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
public class PRDocumentos extends HttpServlet {

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
                    SQLXML daoprdocumentos = DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.PROYECTOS,keycode, DAOReqmDocumentos.F_LISTA);
                    session.setAttribute("PRId", keycode);
                    UserManager user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(daoprdocumentos.getString(), user.getPermisos()), path + "../web/xsl/prdocumentos.xsl"));
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
                    SQLXML prdocumentos = DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.PROYECTOS,new Long(mod), DAOReqmDocumentos.F_DOCUMENTO);
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(prdocumentos.getString(), new String[]{user.getPermisos(),}), path + "../web/xsl/prdocumentos_form.xsl"));
                } else if (nuevo != null) {
                    PrintWriter out = response.getWriter();
                    UserManager user;
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    user = (UserManager) session.getAttribute("user");
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.PROYECTOS,(Long) session.getAttribute("PRId"), DAOReqmDocumentos.F_NEW).getString(),
                            new String[]{user.getPermisos()}), path + "../web/xsl/prdocumentos_form.xsl"));
                } else if (down != null) {
                    DAOReqmDocumentos prdoc = new DAOReqmDocumentos(DAOReqmDocumentos.PROYECTOS);
                    byte[] doc = prdoc.getDocument(new Long(down));
                    if (doc != null) {
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + prdoc.createDocName() + "\"");
                        response.setHeader("cache-control", "no-cache");
                        response.setContentType(prdoc.getsReqmDocumentoMIME());
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
                            XMLModder.JoinDocs("<root><key>"+upload+"</key></root>",new String[]{user.getPermisos()}), path + "../web/xsl/prdocumentos_form.xsl"));
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
                DAOReqmDocumentos prdocumento = new DAOReqmDocumentos(DAOReqmDocumentos.PROYECTOS);
                prdocumento.setlReqmDocumentoId(new Long(request.getParameter("keycode")));
                prdocumento.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOReqmDocumentos.getXMLRecords(DAOReqmDocumentos.PROYECTOS,(Long) session.getAttribute("PRId"), DAOReqmDocumentos.F_LISTA).getString(), user.getPermisos()), path + "../web/xsl/prdocumentos.xsl"));

            } else {
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
                    DAOReqmDocumentos prdocumento = new DAOReqmDocumentos(DAOReqmDocumentos.PROYECTOS,new Long((String) hash.get("inCode")), (Long) session.getAttribute("PRId"), null, (String) hash.get("inName"), (FileItem) hash.get("inFile"));
                    if (prdocumento.getlReqmDocumentoId() == 0) {
                        prdocumento.insert();
                    } else if (prdocumento.getlFReqmId()!=0 && prdocumento.getsReqmDocumentoNm()!=null){
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        String date = ""+hash.get("inDate");
                        if (date != null && date.length() == 10) {
                            prdocumento.setDtReqmDocumentoDt(new java.sql.Date(dateFormatter.parse(date).getTime()));
                        }
                        prdocumento.update();
                    }else{
                        prdocumento.upload();
                    }

                    response.sendRedirect("ok.html");
                }
            }
        } catch(FileUploadException fex){
            response.sendRedirect("big.html");
//            throw new ServletException(fex.getMessage(), fex.getCause());
        }
        catch (Exception ex) {
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
        return "REQM - Documentos Proyecto";
    }// </editor-fold>
}
