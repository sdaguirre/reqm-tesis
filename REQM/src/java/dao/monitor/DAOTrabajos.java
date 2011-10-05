package dao.monitor;

import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOTrabajos extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblTrabajos";
    public static final int F_LISTA = 1, F_REGISTRO = 2, F_NEW = 3, F_REPORT = 4;
    private long lTrabajoId,lEquipoId;
    private Date dtTrabajoDt;
    private int iPMotivoId,iEstadoFl,iNivelFl,iRecepcionId;
    //#End
//#Region Builders

    public DAOTrabajos() {
    }

    public DAOTrabajos(long lTrabajoId, long lEquipoId, Date dtTrabajoDt, int iPMotivoId, int iEstadoFl, int iNivelFl, int sRecepcionId) {
        this.lTrabajoId = lTrabajoId;
        this.lEquipoId = lEquipoId;
        this.dtTrabajoDt = dtTrabajoDt;
        this.iPMotivoId = iPMotivoId;
        this.iEstadoFl = iEstadoFl;
        this.iNivelFl = iNivelFl;
        this.iRecepcionId = sRecepcionId;
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
        this.lTrabajoId = insertRecord(tabla, new Object[]{lEquipoId,iPMotivoId,iEstadoFl,iNivelFl,iRecepcionId});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lTrabajoId,lEquipoId,iPMotivoId,iEstadoFl,iNivelFl,iRecepcionId});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lTrabajoId);
    }
    //#End
//#Region Accessors

    public long getlEquipoId() {
        return lEquipoId;
    }

    public void setlEquipoId(long lEquipoId) {
        this.lEquipoId = lEquipoId;
    }

    public Date getDtTrabajoDt() {
        return dtTrabajoDt;
    }

    public void setDtTrabajoDt(Date dtTrabajoDt) {
        this.dtTrabajoDt = dtTrabajoDt;
    }

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public int getiNivelFl() {
        return iNivelFl;
    }

    public void setiNivelFl(int iNivelFl) {
        this.iNivelFl = iNivelFl;
    }

    public int getiPMotivoId() {
        return iPMotivoId;
    }

    public void setiPMotivoId(int iPMotivoId) {
        this.iPMotivoId = iPMotivoId;
    }

    public int getiRecepcionId() {
        return iRecepcionId;
    }

    public void setiRecepcionId(int iRecepcionId) {
        this.iRecepcionId = iRecepcionId;
    }

    public long getlTrabajoId() {
        return lTrabajoId;
    }

    public void setlTrabajoId(long lTrabajoId) {
        this.lTrabajoId = lTrabajoId;
    }
    
    //#End
}
