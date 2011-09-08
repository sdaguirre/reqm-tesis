package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOProyectos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblProyectos";
    public static final int F_CLIENTE=1,F_ANTEPROYECTO=2,F_PROYECTO=3,FO_AP=4,FO_CLIENT=5;
    private long lProyectoId,lAnteproyectoId;
    private int iPTipoId,iPCategoriaId,iEstadoFl;
    private Date dtFechaIniDt, dtFechaFinDt;
    private String sProyectoNm,sProyectoDesc;
    private double dCostoAmt;
    //#End
//#Region Builders
    public DAOProyectos(){}

    public DAOProyectos(long lProyectoId, long lAnteproyectoId, int iPTipoId, int iPCategoriaId, int iEstadoFl, Date dtFechaIniDt, Date dtFechaFinDt, String sProyectoNm, String sProyectoDesc, double dCostoAmt) {
        this.lProyectoId = lProyectoId;
        this.lAnteproyectoId = lAnteproyectoId;
        this.iPTipoId = iPTipoId;
        this.iPCategoriaId = iPCategoriaId;
        this.iEstadoFl = iEstadoFl;
        this.dtFechaIniDt = dtFechaIniDt;
        this.dtFechaFinDt = dtFechaFinDt;
        this.sProyectoNm = sProyectoNm;
        this.sProyectoDesc = sProyectoDesc;
        this.dCostoAmt = dCostoAmt;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lKeyId,int filtro) throws SQLException{
        return getProcessXML("gxml_",tabla,new Object[]{lKeyId,filtro});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    
    public static SQLXML searchXML(int origen,Long lPersonaId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lPersonaId,"%"+value+"%",origen});
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lProyectoId=insertRecord(tabla, new Object[]{lAnteproyectoId,iPTipoId,iPCategoriaId,iEstadoFl,dtFechaIniDt,
        dtFechaFinDt,sProyectoNm,sProyectoDesc,dCostoAmt});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lProyectoId,lAnteproyectoId,iPTipoId,iPCategoriaId,iEstadoFl,dtFechaIniDt,
        dtFechaFinDt,sProyectoNm,sProyectoDesc,dCostoAmt});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lProyectoId);
    }
    //#End
//#Region Accessors
    public double getdCostoAmt() {
        return dCostoAmt;
    }

    public void setdCostoAmt(double dCostoAmt) {
        this.dCostoAmt = dCostoAmt;
    }

    public Date getDtFechaFinDt() {
        return dtFechaFinDt;
    }

    public void setDtFechaFinDt(Date dtFechaFinDt) {
        this.dtFechaFinDt = dtFechaFinDt;
    }

    public Date getDtFechaIniDt() {
        return dtFechaIniDt;
    }

    public void setDtFechaIniDt(Date dtFechaIniDt) {
        this.dtFechaIniDt = dtFechaIniDt;
    }

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public int getiPCategoriaId() {
        return iPCategoriaId;
    }

    public void setiPCategoriaId(int iPCategoriaId) {
        this.iPCategoriaId = iPCategoriaId;
    }

    public int getiPTipoId() {
        return iPTipoId;
    }

    public void setiPTipoId(int iPTipoId) {
        this.iPTipoId = iPTipoId;
    }

    public long getlAnteproyectoId() {
        return lAnteproyectoId;
    }

    public void setlAnteproyectoId(long lAnteproyectoId) {
        this.lAnteproyectoId = lAnteproyectoId;
    }

    public long getlProyectoId() {
        return lProyectoId;
    }

    public void setlProyectoId(long lProyectoId) {
        this.lProyectoId = lProyectoId;
    }

    public String getsProyectoDesc() {
        return sProyectoDesc;
    }

    public void setsProyectoDesc(String sProyectoDesc) {
        this.sProyectoDesc = sProyectoDesc;
    }

    public String getsProyectoNm() {
        return sProyectoNm;
    }

    public void setsProyectoNm(String sProyectoNm) {
        this.sProyectoNm = sProyectoNm;
    }
    //#End
}