package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.apache.commons.fileupload.FileItem;

public class DAOReqmDocumentos extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String ANTEPROYECTOS = "tblAPDocumentos",PROYECTOS="tblProyectoDocumentos",REQUERIMIENTOS="tblReqDocumentos";
    public static final int F_LISTA = 1, F_DOCUMENTO = 2, F_NEW = 3;
    private long lReqmDocumentoId, lFReqmId;
    private Date dtReqmDocumentoDt;
    private String sReqmDocumentoNm, sReqmDocumentoExt,sReqmDocumentoMIME;
    private FileItem bnReqmDocumentoData;
    private String database="";
    //#End
//#Region Builders

    public DAOReqmDocumentos() {
    }

    public DAOReqmDocumentos(String tabla,long lAPDocumentoId, long lAnteproyectoId, Date dtAPDocumentoDt,String sAPDocumentoNm, FileItem bnAPDocumentoData) {
        this.database=tabla;
        this.lReqmDocumentoId = lAPDocumentoId;
        this.lFReqmId = lAnteproyectoId;
        this.dtReqmDocumentoDt = dtAPDocumentoDt;
        this.sReqmDocumentoNm = sAPDocumentoNm;
        if(bnAPDocumentoData!=null){
            String[] pathf = bnAPDocumentoData.getName().split("\\.");
            this.sReqmDocumentoExt = pathf[pathf.length - 1];
            this.sReqmDocumentoMIME=bnAPDocumentoData.getContentType();
        }
        this.bnReqmDocumentoData = bnAPDocumentoData;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(String tabla,long lAnteproyectoId, int filter) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lAnteproyectoId, filter});
    }

    public byte[] getDocument(long lAPDocumentoId) throws SQLException {
        byte[] doc=null;
        ResultSet rs = getProcessRecords("file_", database, new Object[]{lAPDocumentoId});
        if (rs.next()) {
            //try {
            dtReqmDocumentoDt = rs.getDate(1);
            sReqmDocumentoNm = rs.getString(2);
            sReqmDocumentoExt = rs.getString(3);
            sReqmDocumentoMIME = rs.getString(4);
                /*int largo=rs.getString(5).length();
                doc=new byte[largo];
                InputStream is=rs.getBinaryStream(5);
                is.read(doc, 0, largo);
            } catch (IOException ex) {
                Logger.getLogger(DAOAPDocumentos.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }*/
            doc=rs.getBytes(5);
            return doc;
        } else {
            return null;
        }
    }

    public static SQLXML search(String tabla,String value) throws SQLException {
        return searchXMLRecords(tabla, "%" + value + "%");
    }
    //#End
//#Region IUD

    @Override
    public boolean insert() throws SQLException {
        this.lReqmDocumentoId = insertRecord(database, new Object[]{lFReqmId, sReqmDocumentoNm, sReqmDocumentoExt,sReqmDocumentoMIME, bnReqmDocumentoData});
        return true;
    }

    @Override
    public boolean update() throws SQLException {
        return updateRecord(database, new Object[]{lReqmDocumentoId, lFReqmId, dtReqmDocumentoDt, sReqmDocumentoNm});
    }
    
    public boolean upload() throws SQLException {
        return updateRecord(database, new Object[]{lReqmDocumentoId,sReqmDocumentoExt,sReqmDocumentoMIME,bnReqmDocumentoData});
    }

    @Override
    public boolean delete() throws SQLException {
        return deleteRecord(database, lReqmDocumentoId);
    }

    //#End
//#Region Accessors

   public FileItem getBnReqmDocumentoData() {
        return bnReqmDocumentoData;
    }

    public void setBnReqmDocumentoData(FileItem bnReqmDocumentoData) {
        this.bnReqmDocumentoData = bnReqmDocumentoData;
    }

    public Date getDtReqmDocumentoDt() {
        return dtReqmDocumentoDt;
    }

    public void setDtReqmDocumentoDt(Date dtReqmDocumentoDt) {
        this.dtReqmDocumentoDt = dtReqmDocumentoDt;
    }

    public long getlFReqmId() {
        return lFReqmId;
    }

    public void setlFReqmId(long lFReqmId) {
        this.lFReqmId = lFReqmId;
    }

    public long getlReqmDocumentoId() {
        return lReqmDocumentoId;
    }

    public void setlReqmDocumentoId(long lReqmDocumentoId) {
        this.lReqmDocumentoId = lReqmDocumentoId;
    }

    public String getsReqmDocumentoExt() {
        return sReqmDocumentoExt;
    }

    public void setsReqmDocumentoExt(String sReqmDocumentoExt) {
        this.sReqmDocumentoExt = sReqmDocumentoExt;
    }

        /*public String getMIME() {
        if (sAPDocumentoExt.equals("jpeg")) {
            return "image/jpg";
        } else if (sAPDocumentoExt.equals("jpg")) {
            return "image/jpg";
        } else if (sAPDocumentoExt.equals("bmp")) {
            return "image/bmp";
        } else if (sAPDocumentoExt.equals("gif")) {
            return "image/gif";
        } else if (sAPDocumentoExt.equals("png")) {
            return "image/png";
        } else if (sAPDocumentoExt.equals("doc")) {
            return "application/msword";
        } else if (sAPDocumentoExt.equals("docx")) {
            return "application/msword";
        } else if (sAPDocumentoExt.equals("xls")) {
            return "application/msexcel";
        } else if (sAPDocumentoExt.equals("xlsx")) {
            return "application/msexcel";
        } else if (sAPDocumentoExt.equals("pdf")) {
            return "application/pdf";
        } else {
            return "text/plain";
        }
    }*/

    public String getsReqmDocumentoMIME() {
        return sReqmDocumentoMIME;
    }

    public void setsReqmDocumentoMIME(String sReqmDocumentoMIME) {
        this.sReqmDocumentoMIME = sReqmDocumentoMIME;
    }

    public String getsReqmDocumentoNm() {
        return sReqmDocumentoNm;
    }

    public void setsReqmDocumentoNm(String sReqmDocumentoNm) {
        this.sReqmDocumentoNm = sReqmDocumentoNm;
    }

    public String createDocName(){
        String doc="";
        if(dtReqmDocumentoDt!=null){
            doc+=dtReqmDocumentoDt.toString()+"-";
        }
        String[] word = sReqmDocumentoNm.toUpperCase().split(" ");
        for (String string : word) {
            doc+=string.charAt(0);
        }
        doc+="."+sReqmDocumentoExt;
        return doc;
    }
    //#End
}
