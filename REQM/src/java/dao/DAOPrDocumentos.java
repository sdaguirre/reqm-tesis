package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOPrDocumentos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblPrDocumentos";
    private long lDocumentoId, lProyectoId;
    private Date dtDocumentoDt;
    private Object bnDocumentoData;
    //#End
//#Region Builders
    public DAOPrDocumentos(){}

    public DAOPrDocumentos(long lDocumentoId, long lProyectoId, Date dtDocumentoDt, Object bnDocumentoData) {
        this.lDocumentoId = lDocumentoId;
        this.lProyectoId = lProyectoId;
        this.dtDocumentoDt = dtDocumentoDt;
        this.bnDocumentoData = bnDocumentoData;
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
        this.lDocumentoId=insertRecord(tabla, new Object[]{lProyectoId,dtDocumentoDt,bnDocumentoData});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lDocumentoId,lProyectoId,dtDocumentoDt,bnDocumentoData});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lDocumentoId,0);
    }
    //#End
//#Region Accessors
    public Object getBnDocumentoData() {
        return bnDocumentoData;
    }

    public void setBnDocumentoData(Object bnDocumentoData) {
        this.bnDocumentoData = bnDocumentoData;
    }

    public Date getDtDocumentoDt() {
        return dtDocumentoDt;
    }

    public void setDtDocumentoDt(Date dtDocumentoDt) {
        this.dtDocumentoDt = dtDocumentoDt;
    }

    public long getlDocumentoId() {
        return lDocumentoId;
    }

    public void setlDocumentoId(long lDocumentoId) {
        this.lDocumentoId = lDocumentoId;
    }

    public long getlProyectoId() {
        return lProyectoId;
    }

    public void setlProyectoId(long lProyectoId) {
        this.lProyectoId = lProyectoId;
    }
    //#End
}