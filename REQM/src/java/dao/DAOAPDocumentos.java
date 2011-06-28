package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOAPDocumentos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblAPDocumentos";
    private long lAPDocumentoId, lAnteproyectoId;
    private Date dtAPDocumentoDt;
    private Object bnAPDocumentoData;
    //#End
//#Region Builders
    public DAOAPDocumentos(){}

    public DAOAPDocumentos(long lAPDocumentoId, long lAnteproyectoId, Date dtAPDocumentoDt, Object bnAPDocumentoData) {
        this.lAPDocumentoId = lAPDocumentoId;
        this.lAnteproyectoId = lAnteproyectoId;
        this.dtAPDocumentoDt = dtAPDocumentoDt;
        this.bnAPDocumentoData = bnAPDocumentoData;
    }
    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lAnteproyectoId) throws SQLException{
        return getXMLRecords(tabla, lAnteproyectoId);
    }
    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lAPDocumentoId=insertRecord(tabla, new Object[]{lAnteproyectoId,dtAPDocumentoDt,bnAPDocumentoData});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lAPDocumentoId,lAnteproyectoId,dtAPDocumentoDt,bnAPDocumentoData});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lAPDocumentoId,0);
    }
    //#End
//#Region Accessors
    public Object getBnAPDocumentoData() {
        return bnAPDocumentoData;
    }

    public void setBnAPDocumentoData(Object bnAPDocumentoData) {
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
    //#End
}