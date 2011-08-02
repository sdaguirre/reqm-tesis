package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;
import org.apache.commons.fileupload.FileItem;

public class DAOAPDocumentos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblAPDocumentos";
    public static final int F_ANTEPROYECTO=1,F_DOCUMENTO=2,F_NEW=3;
    private long lAPDocumentoId, lAnteproyectoId;
    private Date dtAPDocumentoDt;
    private String sAPDocumentoNm,sAPDocumentoExt;
    private FileItem bnAPDocumentoData;
    //#End
//#Region Builders
    public DAOAPDocumentos(){}

    public DAOAPDocumentos(long lAPDocumentoId, long lAnteproyectoId, Date dtAPDocumentoDt, String sAPDocumentoNm, String sAPDocumentoExt, FileItem bnAPDocumentoData) {
        this.lAPDocumentoId = lAPDocumentoId;
        this.lAnteproyectoId = lAnteproyectoId;
        this.dtAPDocumentoDt = dtAPDocumentoDt;
        this.sAPDocumentoNm = sAPDocumentoNm;
        this.sAPDocumentoExt = sAPDocumentoExt;
        this.bnAPDocumentoData = bnAPDocumentoData;
    }
    
    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lAnteproyectoId,int filter) throws SQLException{
        return getProcessXML("gxml_", tabla,new Object[]{lAnteproyectoId,filter});
    }
    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    //#End
//#Region IUD
    @Override
    public boolean insert() throws SQLException {
        System.out.println(bnAPDocumentoData.getFieldName()+" --  "+bnAPDocumentoData.getName());
        this.lAPDocumentoId=insertRecord(tabla, new Object[]{lAnteproyectoId,dtAPDocumentoDt,sAPDocumentoNm,sAPDocumentoExt,bnAPDocumentoData});
        return true;
    }
    @Override
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lAPDocumentoId,lAnteproyectoId,dtAPDocumentoDt,bnAPDocumentoData});
    }
    @Override
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lAPDocumentoId,0);
    }
    //#End
//#Region Accessors
    public FileItem getBnAPDocumentoData() {
        return bnAPDocumentoData;
    }

    public void setBnAPDocumentoData(FileItem bnAPDocumentoData) {
        this.bnAPDocumentoData = bnAPDocumentoData;
    }

    public Date getDtAPDocumentoDt() {
        return dtAPDocumentoDt;
    }

    public void setDtAPDocumentoDt(Date dtAPDocumentoDt) {
        this.dtAPDocumentoDt = dtAPDocumentoDt;
    }

    public long getlAPDocumentoId() {
        return lAPDocumentoId;
    }

    public void setlAPDocumentoId(long lAPDocumentoId) {
        this.lAPDocumentoId = lAPDocumentoId;
    }

    public long getlAnteproyectoId() {
        return lAnteproyectoId;
    }

    public void setlAnteproyectoId(long lAnteproyectoId) {
        this.lAnteproyectoId = lAnteproyectoId;
    }

    public String getsAPDocumentoExt() {
        return sAPDocumentoExt;
    }

    public void setsAPDocumentoExt(String sAPDocumentoExt) {
        this.sAPDocumentoExt = sAPDocumentoExt;
    }

    public String getsAPDocumentoNm() {
        return sAPDocumentoNm;
    }

    public void setsAPDocumentoNm(String sAPDocumentoNm) {
        this.sAPDocumentoNm = sAPDocumentoNm;
    }

    //#End
}