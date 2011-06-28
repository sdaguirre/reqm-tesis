package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOUsuarios extends DAO implements IDAO{
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">
    public static final String tabla="tblUsuarios";
    private int lUsuarioId,iRolId,iEstadoFl;
    private long lPersonaId;
    private String sUsuarioNm,sUsuarioPwd;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">
    public DAOUsuarios(){}

    public DAOUsuarios(int lUsuarioId, int iRolId, int iEstadoFl, long lPersonaId, String sUsuarioNm, String sUsuarioPwd) {
        this.lUsuarioId = lUsuarioId;
        this.iRolId = iRolId;
        this.iEstadoFl = iEstadoFl;
        this.lPersonaId = lPersonaId;
        this.sUsuarioNm = sUsuarioNm;
        this.sUsuarioPwd = sUsuarioPwd;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    public static SQLXML LoginUser(String user,String passwd) throws SQLException{
        return getProcessXML("vfy_", tabla, new Object[]{user,passwd});
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">
    public boolean insert() throws SQLException {
        this.lUsuarioId=(int)insertRecord(tabla, new Object[]{iRolId,iEstadoFl,lPersonaId,sUsuarioNm,sUsuarioPwd});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lUsuarioId,iRolId,iEstadoFl,lPersonaId,sUsuarioNm,sUsuarioPwd});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lUsuarioId,0);
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">
    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public int getiRolId() {
        return iRolId;
    }

    public void setiRolId(int iRolId) {
        this.iRolId = iRolId;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public int getlUsuarioId() {
        return lUsuarioId;
    }

    public void setlUsuarioId(int lUsuarioId) {
        this.lUsuarioId = lUsuarioId;
    }

    public String getsUsuarioNm() {
        return sUsuarioNm;
    }

    public void setsUsuarioNm(String sUsuarioNm) {
        this.sUsuarioNm = sUsuarioNm;
    }

    public String getsUsuarioPwd() {
        return sUsuarioPwd;
    }

    public void setsUsuarioPwd(String sUsuarioPwd) {
        this.sUsuarioPwd = sUsuarioPwd;
    }
// </editor-fold>
}