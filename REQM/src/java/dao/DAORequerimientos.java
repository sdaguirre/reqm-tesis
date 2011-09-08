package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAORequerimientos extends DAO implements IDAO {
//#Region VarDeclarations

    public static final String tabla = "tblRequerimientos";
    public static final int F_LISTA = 1, F_REQUERIMIENTO = 2, FO_PROYECTO = 3, FO_REQPADRE = 4, F_LISTA_CLIENTE = 5,
            A_EMPRESA = 1, A_CLIENTE = 2;
    private long lRequerimientoId, lProyectoId, lReqPadreId;
    private int iTipoFl, iEstadoFl, iAvanceFl;
    private String sRequerimientoNm, sRequerimientoDesc;
    //#End
//#Region Builders

    public DAORequerimientos() {
    }

    public DAORequerimientos(long lRequerimientoId, long lProyectoId, long lReqPadreId, int iTipoFl, int iEstadoFl, int iAvanceFl, String sRequerimientoNm, String sRequerimientoDesc) {
        this.lRequerimientoId = lRequerimientoId;
        this.lProyectoId = lProyectoId;
        this.lReqPadreId = lReqPadreId;
        this.iTipoFl = iTipoFl;
        this.iEstadoFl = iEstadoFl;
        this.iAvanceFl = iAvanceFl;
        this.sRequerimientoNm = sRequerimientoNm;
        this.sRequerimientoDesc = sRequerimientoDesc;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lKeyId, int filtro) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lKeyId, filtro});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla, "%" + value + "%");
    }

    public static SQLXML searchXML(Long lProyectoId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lProyectoId,"%"+value+"%"});
    }
    //#End
//#Region IUD

    public boolean insert() throws SQLException {
        this.lProyectoId = insertRecord(tabla, new Object[]{lProyectoId, lReqPadreId, iTipoFl, iEstadoFl, iAvanceFl, sRequerimientoNm, sRequerimientoDesc});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lRequerimientoId, lProyectoId, lReqPadreId, iTipoFl, iEstadoFl, iAvanceFl, sRequerimientoNm, sRequerimientoDesc});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lRequerimientoId);
    }

    public static boolean approve(long lRequerimientoId, int type) throws SQLException {
        return processRecord("apr_", tabla, new Object[]{lRequerimientoId,type})>0;
    }
    //#End
//#Region Accessors

    public int getiAvanceFl() {
        return iAvanceFl;
    }

    public void setiAvanceFl(int iAvanceFl) {
        this.iAvanceFl = iAvanceFl;
    }

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public int getiTipoFl() {
        return iTipoFl;
    }

    public void setiTipoFl(int iTipoFl) {
        this.iTipoFl = iTipoFl;
    }

    public long getlProyectoId() {
        return lProyectoId;
    }

    public void setlProyectoId(long lProyectoId) {
        this.lProyectoId = lProyectoId;
    }

    public long getlReqPadreId() {
        return lReqPadreId;
    }

    public void setlReqPadreId(long lReqPadreId) {
        this.lReqPadreId = lReqPadreId;
    }

    public long getlRequerimientoId() {
        return lRequerimientoId;
    }

    public void setlRequerimientoId(long lRequerimientoId) {
        this.lRequerimientoId = lRequerimientoId;
    }

    public String getsRequerimientoDesc() {
        return sRequerimientoDesc;
    }

    public void setsRequerimientoDesc(String sRequerimientoDesc) {
        this.sRequerimientoDesc = sRequerimientoDesc;
    }

    public String getsRequerimientoNm() {
        return sRequerimientoNm;
    }

    public void setsRequerimientoNm(String sRequerimientoNm) {
        this.sRequerimientoNm = sRequerimientoNm;
    }
    //#End
}
