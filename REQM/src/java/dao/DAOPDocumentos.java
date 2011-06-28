package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOPDocumentos extends DAO implements IDAO{
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">
    public static final String tabla="tblPersonasDocumentos";
    private long lDocumentoId,lPersonaId;
    private int iPDocumentoId;
    private String sDocumentoNm;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">
    public DAOPDocumentos(){}

    public DAOPDocumentos(long lDocumentoId, long lPersonaId, int iPDocumentoId, String sDocumentoNm) {
        this.lDocumentoId = lDocumentoId;
        this.lPersonaId = lPersonaId;
        this.iPDocumentoId = iPDocumentoId;
        this.sDocumentoNm = sDocumentoNm;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">
    public static SQLXML getXMLRecords(long lPersonaId) throws SQLException{
        return getXMLRecords(tabla,lPersonaId);
    }
    public static SQLXML getXMLRecords(long lPersonaId,long lDocumentoId) throws SQLException{
        return getProcessXML("gxml_", tabla, new Object[]{lDocumentoId,lPersonaId});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">
    public boolean insert() throws SQLException {
        this.lDocumentoId=insertRecord(tabla, new Object[]{lPersonaId,iPDocumentoId,sDocumentoNm});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lDocumentoId,lPersonaId,iPDocumentoId,sDocumentoNm});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lDocumentoId);
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">
    public int getiPDocumentoId() {
        return iPDocumentoId;
    }

    public void setiPDocumentoId(int iPDocumentoId) {
        this.iPDocumentoId = iPDocumentoId;
    }

    public long getlDocumentoId() {
        return lDocumentoId;
    }

    public void setlDocumentoId(long lDocumentoId) {
        this.lDocumentoId = lDocumentoId;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public String getsDocumentoNm() {
        return sDocumentoNm;
    }

    public void setsDocumentoNm(String sDocumentoNm) {
        this.sDocumentoNm = sDocumentoNm;
    }

// </editor-fold>
}