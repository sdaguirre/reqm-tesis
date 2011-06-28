package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOPContactos extends DAO implements IDAO {
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">

    public static final String tabla = "tblPersonasContactos";
    private long lContactoId, lPersonaId;
    private int iPContactoId;
    private String sContactoNm;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">

    public DAOPContactos() {
    }

    public DAOPContactos(long lContactoId, long lPersonaId, int iPContactoId, String sContactoNm) {
        this.lContactoId = lContactoId;
        this.lPersonaId = lPersonaId;
        this.iPContactoId = iPContactoId;
        this.sContactoNm = sContactoNm;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">
    public static SQLXML getXMLRecords(long lPersonaId) throws SQLException {
        return getXMLRecords(tabla, lPersonaId);
    }

    public static SQLXML getXMLRecords(long lPersonaId, long lContactoId) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lContactoId,lPersonaId});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla, "%" + value + "%");
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">

    public boolean insert() throws SQLException {
        this.lContactoId = insertRecord(tabla, new Object[]{lPersonaId, iPContactoId, sContactoNm});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lContactoId, lPersonaId, iPContactoId, sContactoNm});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lContactoId);
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">

    public int getiPContactoId() {
        return iPContactoId;
    }

    public void setiPContactoId(int iPContactoId) {
        this.iPContactoId = iPContactoId;
    }

    public long getlContactoId() {
        return lContactoId;
    }

    public void setlContactoId(long lContactoId) {
        this.lContactoId = lContactoId;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public String getsContactoNm() {
        return sContactoNm;
    }

    public void setsContactoNm(String sContactoNm) {
        this.sContactoNm = sContactoNm;
    }
// </editor-fold>
}
