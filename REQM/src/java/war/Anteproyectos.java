package war;

import conexion.Conexion;
import dao.DAOAnteproyectos;
import dao.DAOPFisicas;
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

public class Anteproyectos extends HttpServlet {

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
                    SQLXML anteproyectos = DAOAnteproyectos.getXMLRecords((Long) session.getAttribute("PersonaId"));
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(anteproyectos.getString(), user.getPermisos()), path + "../web/xsl/anteproyectos2.xsl"));
                } else {
                    user = (UserManager) session.getAttribute("user");
                    if (request.getParameter("step") != null) {
                        long step = new Long("" + request.getParameter("step"));
                        if (step == 2) {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOAnteproyectos.getXMLRecords((Long) session.getAttribute("PersonaId")).getString(), user.getPermisos()), path + "../web/xsl/anteproyectos2.xsl"));
                        } else {
                            if (user.isbClient()) {
                                out.println(XMLModder.XSLTransform(
                                        XMLModder.JoinDocs(DAOAnteproyectos.getXMLRecords(user.getPersonaId()).getString(), user.getPermisos()), path + "../web/xsl/anteproyectos2.xsl"));
                            } else {
                                out.println(XMLModder.XSLTransform(
                                        XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), user.getPermisos()), path + "../web/xsl/anteproyectos.xsl"));
                            }
                        }
                    } else {
                        if (user.isbClient()) {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOAnteproyectos.getXMLRecords(user.getPersonaId()).getString(), user.getPermisos()), path + "../web/xsl/anteproyectos2.xsl"));
                        } else {
                            out.println(XMLModder.XSLTransform(
                                    XMLModder.JoinDocs(DAOPFisicas.getXMLRecords().getString(), user.getPermisos()), path + "../web/xsl/anteproyectos.xsl"));
                        }
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
                String ins = request.getParameter("ins");
                System.out.println(ins + " == " + request.getParameter("ins"));
                if (request.getParameter("mod") == null) {
                    if (ins == null) {
                        processRequest(request, response);
                    } else {
                        Conexion.autoConnect();
                        if (!ins.equals("true")) {
                            session.setAttribute("PersonaId", new Long(ins));
                        }
                        UserManager user = (UserManager) session.getAttribute("user");
                        SQLXML personas = DAOPFisicas.getXMLRecords("" + session.getAttribute("PersonaId"));
                        out.println(XMLModder.XSLTransform(
                                XMLModder.JoinDocs(personas.getString(),
                                new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.TIPOS).getString(),
                                    DAOParams.getXMLRecords(DAOParams.CATEGORIAS).getString()}), path + "../web/xsl/anteproyectos_form.xsl"));
                    }
                } else {
                    UserManager user = (UserManager) session.getAttribute("user");
                    request.setCharacterEncoding("UTF-8");
                    Conexion.autoConnect();
                    SQLXML anteproyectos = DAOAnteproyectos.getXMLRecords(new Long("0"), new Long(request.getParameter("mod")));
                    out.println(XMLModder.XSLTransform(
                            XMLModder.JoinDocs(anteproyectos.getString(),
                            new String[]{user.getPermisos(), DAOParams.getXMLRecords(DAOParams.TIPOS).getString(),
                                DAOParams.getXMLRecords(DAOParams.CATEGORIAS).getString()}), path + "../web/xsl/anteproyectos_form.xsl"));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("login.html");
            //throw new ServletException(ex.getMessage(), ex.getCause());
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
                DAOAnteproyectos daoap = new DAOAnteproyectos(new Long(request.getParameter("inCode")),
                        new Integer(request.getParameter("inType")), new Integer(request.getParameter("inCats")), new Long(request.getParameter("inFKey")), request.getParameter("inName"), request.getParameter("inDesc"));
                if (daoap.getlAnteproyectoId() == 0) {
                    daoap.insert();
                } else {
                    daoap.update();
                }
                response.sendRedirect("ok.html");
            } else if (request.getParameter("del.x") != null) {
                Conexion.autoConnect();
                DAOAnteproyectos daoap = new DAOAnteproyectos();
                daoap.setlAnteproyectoId(new Long(request.getParameter("keycode")));
                daoap.delete();
                user = (UserManager) session.getAttribute("user");
                out.println(XMLModder.XSLTransform(
                        XMLModder.JoinDocs(DAOAnteproyectos.getXMLRecords((Long) session.getAttribute("PersonaId")).getString(), user.getPermisos()), path + "../web/xsl/anteproyectos2.xsl"));
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
        return "REQM System - Anteproyectos";
    }// </editor-fold>
}
