package dao.monitor;

import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAODispositivos extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblDispositivos";
    public static final int F_LISTA = 1, F_REGISTRO = 2, F_NEW = 3, F_REPORT = 4;
    private long lEquipoId, lPersonaId;
    private int iTipoFl,iRedFl;
    private String sResponsableNm,sUsuarioNm,sUsuarioPass,sIPNm,sRedNm;
    private Date dtEquipoDt;
    //#End
//#Region Builders

    public DAODispositivos() {
    }

    public DAODispositivos(long lEquipoId, long lPersonaId, int iTipoFl, int iRedFl, String sResponsableNm, String sUsuarioNm, String sUsuarioPass, String sIPNm,String sRedNm) {
        this.lEquipoId = lEquipoId;
        this.lPersonaId = lPersonaId;
        this.iTipoFl = iTipoFl;
        this.iRedFl = iRedFl;
        this.sResponsableNm = sResponsableNm;
        this.sUsuarioNm = sUsuarioNm;
        this.sUsuarioPass = sUsuarioPass;
        this.sIPNm = sIPNm;
        this.sRedNm=sRedNm;
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

    public static SQLXML searchXML(long lPersonaId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lPersonaId,"%" + value + "%"});
    }
    //#End
//#Region IUD

    public boolean insert() throws SQLException {
        this.lEquipoId = insertRecord(tabla, new Object[]{lPersonaId,iTipoFl,sResponsableNm,sUsuarioNm,sUsuarioPass,sIPNm,iRedFl,sRedNm});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lEquipoId, lPersonaId,iTipoFl,sResponsableNm,sUsuarioNm,sUsuarioPass,sIPNm,iRedFl,sRedNm});
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

    public int getiRedFl() {
        return iRedFl;
    }

    public void setiRedFl(int iRedFl) {
        this.iRedFl = iRedFl;
    }

    public int getiTipoFl() {
        return iTipoFl;
    }

    public void setiTipoFl(int iTipoFl) {
        this.iTipoFl = iTipoFl;
    }

    public String getsIPNm() {
        return sIPNm;
    }

    public void setsIPNm(String sIPNm) {
        this.sIPNm = sIPNm;
    }

    public String getsResponsableNm() {
        return sResponsableNm;
    }

    public void setsResponsableNm(String sResponsableNm) {
        this.sResponsableNm = sResponsableNm;
    }

    public String getsUsuarioNm() {
        return sUsuarioNm;
    }

    public void setsUsuarioNm(String sUsuarioNm) {
        this.sUsuarioNm = sUsuarioNm;
    }

    public String getsUsuarioPass() {
        return sUsuarioPass;
    }

    public void setsUsuarioPass(String sUsuarioPass) {
        this.sUsuarioPass = sUsuarioPass;
    }

    public String getsRedNm() {
        return sRedNm;
    }

    public void setsRedNm(String sRedNm) {
        this.sRedNm = sRedNm;
    }
    
    //#End
}
