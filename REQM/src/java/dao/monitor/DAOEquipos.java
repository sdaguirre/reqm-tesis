package dao.monitor;

import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOEquipos extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblEquipos";
    public static final int F_LISTA = 1, F_REGISTRO = 2, F_NEW = 3, F_REPORT = 4;
    private long lEquipoId, lPersonaId;
    private Date dtEquipoDt;
    //#End
//#Region Builders

    public DAOEquipos() {
    }

    public DAOEquipos(long lEquipoId, long lPersonaId, Date dtEquipoDt) {
        this.lEquipoId = lEquipoId;
        this.lPersonaId = lPersonaId;
        this.dtEquipoDt = dtEquipoDt;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException {
        return getXMLTable(tabla);
    }

    public static SQLXML getXMLRecords(String key) throws SQLException {
        return getXMLRecords(tabla, new Long(key));
    }

    public static SQLXML getXMLRecords(long lPersonaId, int filter) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lPersonaId, filter});
    }

    public static SQLXML searchXML(String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{"%" + value + "%"});
    }
    //#End
//#Region IUD

    public boolean insert() throws SQLException {
        this.lEquipoId = insertRecord(tabla, new Object[]{lPersonaId});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lEquipoId, lPersonaId});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lEquipoId);
    }
    //#End
//#Region Accessors

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public Date getDtEquipoDt() {
        return dtEquipoDt;
    }

    public void setDtEquipoDt(Date dtEquipoDt) {
        this.dtEquipoDt = dtEquipoDt;
    }

    public long getlEquipoId() {
        return lEquipoId;
    }

    public void setlEquipoId(long lEquipoId) {
        this.lEquipoId = lEquipoId;
    }
    //#End
}
