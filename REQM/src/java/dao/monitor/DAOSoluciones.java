package dao.monitor;

import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOSoluciones extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblSoluciones";
    public static final int F_LISTA = 1, F_REGISTRO = 2, F_NEW = 3, F_REPORT = 4;
    private long lSolucionId, lTrabajoId;
    private Date dtSolucionDt;
    private int iPMotivoId, lUsuarioId;
    private String sSolucionNm, sSolucionDesc;
    //#End
//#Region Builders

    public DAOSoluciones() {
    }

    public DAOSoluciones(long lSolucionId, long lTrabajoId, Date dtSolucionDt, int iPMotivoId, int lUsuarioId, String sSolucionNm, String sSolucionDesc) {
        this.lSolucionId = lSolucionId;
        this.lTrabajoId = lTrabajoId;
        this.dtSolucionDt = dtSolucionDt;
        this.iPMotivoId = iPMotivoId;
        this.lUsuarioId = lUsuarioId;
        this.sSolucionNm = sSolucionNm;
        this.sSolucionDesc = sSolucionDesc;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException {
        return getXMLTable(tabla);
    }

    public static SQLXML getXMLRecords(String key) throws SQLException {
        return getXMLRecords(tabla, new Long(key));
    }

    public static SQLXML getXMLRecords(long lKeyId, int filter) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lKeyId, filter});
    }

    public static SQLXML searchXML(String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{"%" + value + "%"});
    }
    //#End
//#Region IUD

    public boolean insert() throws SQLException {
        this.lSolucionId = insertRecord(tabla, new Object[]{lTrabajoId, iPMotivoId, sSolucionNm, sSolucionDesc, lUsuarioId});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lSolucionId, lTrabajoId, iPMotivoId, sSolucionNm, sSolucionDesc, lUsuarioId});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lSolucionId);
    }
//#End
//#Region Accessors

    public Date getDtSolucionDt() {
        return dtSolucionDt;
    }

    public void setDtSolucionDt(Date dtSolucionDt) {
        this.dtSolucionDt = dtSolucionDt;
    }

    public int getiPMotivoId() {
        return iPMotivoId;
    }

    public void setiPMotivoId(int iPMotivoId) {
        this.iPMotivoId = iPMotivoId;
    }

    public long getlSolucionId() {
        return lSolucionId;
    }

    public void setlSolucionId(long lSolucionId) {
        this.lSolucionId = lSolucionId;
    }

    public long getlTrabajoId() {
        return lTrabajoId;
    }

    public void setlTrabajoId(long lTrabajoId) {
        this.lTrabajoId = lTrabajoId;
    }

    public int getlUsuarioId() {
        return lUsuarioId;
    }

    public void setlUsuarioId(int lUsuarioId) {
        this.lUsuarioId = lUsuarioId;
    }

    public String getsSolucionDesc() {
        return sSolucionDesc;
    }

    public void setsSolucionDesc(String sSolucionDesc) {
        this.sSolucionDesc = sSolucionDesc;
    }

    public String getsSolucionNm() {
        return sSolucionNm;
    }

    public void setsSolucionNm(String sSolucionNm) {
        this.sSolucionNm = sSolucionNm;
    }
    //#End
}
