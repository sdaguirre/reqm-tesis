package dao.monitor;

import dao.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOPiezas extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblPiezas";
    public static final int F_LISTA = 1, F_REGISTRO = 2, F_NEW = 3, F_REPORT = 4;
    private long lPiezaId, lEquipoId;
    private Date dtPiezaDt, dtGarantiaDt;
    private int iPPiezaId, iPMarcaId;
    private String sCodigoNm, sModeloNm, sPiezaDesc;
    //#End
//#Region Builders

    public DAOPiezas() {
    }

    public DAOPiezas(long lPiezaId, long lEquipoId, Date dtPiezaDt, Date dtGarantiaDt, int iPPiezaId, int iPMarcaId, String sCodigoNm, String sModeloNm, String sPiezaDesc) {
        this.lPiezaId = lPiezaId;
        this.lEquipoId = lEquipoId;
        this.dtPiezaDt = dtPiezaDt;
        this.dtGarantiaDt = dtGarantiaDt;
        this.iPPiezaId = iPPiezaId;
        this.iPMarcaId = iPMarcaId;
        this.sCodigoNm = sCodigoNm;
        this.sModeloNm = sModeloNm;
        this.sPiezaDesc = sPiezaDesc;
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

    public static SQLXML searchXML(long lDispositivoId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lDispositivoId,"%" + value + "%"});
    }
    //#End
//#Region IUD

    public boolean insert() throws SQLException {
        this.lPiezaId = insertRecord(tabla, new Object[]{lEquipoId, dtPiezaDt,iPPiezaId, sCodigoNm, iPMarcaId, sModeloNm, sPiezaDesc, dtGarantiaDt});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lPiezaId, lEquipoId, dtPiezaDt, iPPiezaId, sCodigoNm, iPMarcaId, sModeloNm, sPiezaDesc, dtGarantiaDt});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lPiezaId);
    }
    //#End
//#Region Accessors

    public long getlEquipoId() {
        return lEquipoId;
    }

    public void setlEquipoId(long lEquipoId) {
        this.lEquipoId = lEquipoId;
    }

    public Date getDtGarantiaDt() {
        return dtGarantiaDt;
    }

    public void setDtGarantiaDt(Date dtGarantiaDt) {
        this.dtGarantiaDt = dtGarantiaDt;
    }

    public Date getDtPiezaDt() {
        return dtPiezaDt;
    }

    public void setDtPiezaDt(Date dtPiezaDt) {
        this.dtPiezaDt = dtPiezaDt;
    }

    public int getiPMarcaId() {
        return iPMarcaId;
    }

    public void setiPMarcaId(int iPMarcaId) {
        this.iPMarcaId = iPMarcaId;
    }

    public int getiPPiezaId() {
        return iPPiezaId;
    }

    public void setiPPiezaId(int iPPiezaId) {
        this.iPPiezaId = iPPiezaId;
    }

    public long getlPiezaId() {
        return lPiezaId;
    }

    public void setlPiezaId(long lPiezaId) {
        this.lPiezaId = lPiezaId;
    }

    public String getsCodigoNm() {
        return sCodigoNm;
    }

    public void setsCodigoNm(String sCodigoNm) {
        this.sCodigoNm = sCodigoNm;
    }

    public String getsModeloNm() {
        return sModeloNm;
    }

    public void setsModeloNm(String sModeloNm) {
        this.sModeloNm = sModeloNm;
    }

    public String getsPiezaDesc() {
        return sPiezaDesc;
    }

    public void setsPiezaDesc(String sPiezaDesc) {
        this.sPiezaDesc = sPiezaDesc;
    }
    //#End
}
