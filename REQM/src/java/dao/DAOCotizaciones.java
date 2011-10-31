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
    private long lCotizacionId, lFReqmId;
    private int iEstadoFl;
    private Date dtCotizacionDt;
    private String sCotizacionNm, sCotizacionDesc,sCotizacionExt,sCotizacionMIME;
    private FileItem bnCotizacionData;
    //#End
//#Region Builders

    public DAOCotizaciones() {
    }

    public DAOCotizaciones(long lCotizacionId, FileItem bnCotizacionData) {
        this.lCotizacionId = lCotizacionId;
        this.bnCotizacionData = bnCotizacionData;
        if(bnCotizacionData!=null){
            String[] pathf = bnCotizacionData.getName().split("\\.");
            this.sCotizacionExt = pathf[pathf.length - 1];
            this.sCotizacionMIME=bnCotizacionData.getContentType();
        }
    }

    public DAOCotizaciones(long lCotizacionId, long lPersonaId,int iEstadoFl,
            Date dtCotizacionDt,String sCotizacionNm,String sCotizacionDesc, FileItem bnCotizacionData) {
        this.lCotizacionId = lCotizacionId;
        this.lFReqmId = lPersonaId;
        this.iEstadoFl=iEstadoFl;
        this.dtCotizacionDt = dtCotizacionDt;
        this.sCotizacionNm = sCotizacionNm;
        this.sCotizacionDesc = sCotizacionDesc;
        if(bnCotizacionData!=null){
            String[] pathf = bnCotizacionData.getName().split("\\.");
            this.sCotizacionExt = pathf[pathf.length - 1];
            this.sCotizacionMIME=bnCotizacionData.getContentType();
        }
        this.bnCotizacionData = bnCotizacionData;
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
            dtCotizacionDt = rs.getDate(1);
            sCotizacionNm = rs.getString(2);
            sCotizacionExt = rs.getString(3);
            sCotizacionMIME = rs.getString(4);
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
        this.lCotizacionId = insertRecord(tabla, new Object[]{lFReqmId, iEstadoFl,sCotizacionNm,sCotizacionDesc, sCotizacionExt,sCotizacionMIME, bnCotizacionData});
        return true;
    }

    @Override
    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lCotizacionId,lFReqmId,iEstadoFl, sCotizacionNm,sCotizacionDesc});
    }
    
    public boolean upload() throws SQLException {
        return updateRecord(tabla, new Object[]{lCotizacionId,sCotizacionExt,sCotizacionMIME,bnCotizacionData});
    }

    @Override
    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lCotizacionId);
    }

    //#End
//#Region Accessors

   public FileItem getBnCotizacionData() {
        return bnCotizacionData;
    }

    public void setBnCotizacionData(FileItem bnCotizacionData) {
        this.bnCotizacionData = bnCotizacionData;
    }

    public Date getDtCotizacionDt() {
        return dtCotizacionDt;
    }

    public void setDtCotizacionDt(Date dtCotizacionDt) {
        this.dtCotizacionDt = dtCotizacionDt;
    }

    public long getlFReqmId() {
        return lFReqmId;
    }

    public void setlFReqmId(long lFReqmId) {
        this.lFReqmId = lFReqmId;
    }

    public long getlCotizacionId() {
        return lCotizacionId;
    }

    public void setlCotizacionId(long lCotizacionId) {
        this.lCotizacionId = lCotizacionId;
    }

    public String getsCotizacionExt() {
        return sCotizacionExt;
    }

    public void setsCotizacionExt(String sCotizacionExt) {
        this.sCotizacionExt = sCotizacionExt;
    }

    public String getsCotizacionMIME() {
        return sCotizacionMIME;
    }

    public void setsCotizacionMIME(String sCotizacionMIME) {
        this.sCotizacionMIME = sCotizacionMIME;
    }

    public String getsCotizacionNm() {
        return sCotizacionNm;
    }

    public void setsCotizacionNm(String sCotizacionNm) {
        this.sCotizacionNm = sCotizacionNm;
    }

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public String getsCotizacionDesc() {
        return sCotizacionDesc;
    }

    public void setsCotizacionDesc(String sCotizacionDesc) {
        this.sCotizacionDesc = sCotizacionDesc;
    }
    
    public String createDocName(){
        String doc="";
        if(dtCotizacionDt!=null){
            doc+=dtCotizacionDt.toString()+"-";
        }
        String[] word = sCotizacionNm.toUpperCase().split(" ");
        for (String string : word) {
            doc+=string.charAt(0);
        }
        doc+="."+sCotizacionExt;
        return doc;
    }
    //#End
}
