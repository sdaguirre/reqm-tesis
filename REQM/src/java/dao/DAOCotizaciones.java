package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.apache.commons.fileupload.FileItem;

public class DAOCotizaciones extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblCotizaciones";
    public static final int F_LISTA = 1, F_DOCUMENTO = 2, F_NEW = 3;
    private long lReqmDocumentoId, lFReqmId;
    private int iEstadoFl;
    private Date dtReqmDocumentoDt;
    private String sReqmDocumentoNm, sReqmDocumentoExt,sReqmDocumentoMIME;
    private FileItem bnReqmDocumentoData;
    //#End
//#Region Builders

    public DAOCotizaciones() {
    }

    public DAOCotizaciones(long lReqmDocumentoId, FileItem bnReqmDocumentoData) {
        this.lReqmDocumentoId = lReqmDocumentoId;
        this.bnReqmDocumentoData = bnReqmDocumentoData;
        if(bnReqmDocumentoData!=null){
            String[] pathf = bnReqmDocumentoData.getName().split("\\.");
            this.sReqmDocumentoExt = pathf[pathf.length - 1];
            this.sReqmDocumentoMIME=bnReqmDocumentoData.getContentType();
        }
    }

    public DAOCotizaciones(long lCotizacionId, long lPersonaId,int iEstadoFl,
            Date dtCotizacionDt,String sCotizacionNm, FileItem bnCotizacionData) {
        this.lReqmDocumentoId = lCotizacionId;
        this.lFReqmId = lPersonaId;
        this.iEstadoFl=iEstadoFl;
        this.dtReqmDocumentoDt = dtCotizacionDt;
        this.sReqmDocumentoNm = sCotizacionNm;
        if(bnCotizacionData!=null){
            String[] pathf = bnCotizacionData.getName().split("\\.");
            this.sReqmDocumentoExt = pathf[pathf.length - 1];
            this.sReqmDocumentoMIME=bnCotizacionData.getContentType();
        }
        this.bnReqmDocumentoData = bnCotizacionData;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lKeyId, int filter) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lKeyId, filter});
    }

    public byte[] getDocument(long lCotizacionId) throws SQLException {
        byte[] doc=null;
        ResultSet rs = getProcessRecords("file_", tabla, new Object[]{lCotizacionId});
        if (rs.next()) {
            dtReqmDocumentoDt = rs.getDate(1);
            sReqmDocumentoNm = rs.getString(2);
            sReqmDocumentoExt = rs.getString(3);
            sReqmDocumentoMIME = rs.getString(4);
            doc=rs.getBytes(5);
            return doc;
        } else {
            return null;
        }
    }

    public static SQLXML searchXML(long lPersonaId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lPersonaId,"%" + value + "%"});
    }
    //#End
//#Region IUD

    @Override
    public boolean insert() throws SQLException {
        this.lReqmDocumentoId = insertRecord(tabla, new Object[]{lFReqmId, iEstadoFl,sReqmDocumentoNm, sReqmDocumentoExt,sReqmDocumentoMIME, bnReqmDocumentoData});
        return true;
    }

    @Override
    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lReqmDocumentoId,lFReqmId,iEstadoFl, sReqmDocumentoNm});
    }
    
    public boolean upload() throws SQLException {
        return updateRecord(tabla, new Object[]{lReqmDocumentoId,sReqmDocumentoExt,sReqmDocumentoMIME,bnReqmDocumentoData});
    }

    @Override
    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lReqmDocumentoId);
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

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
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
