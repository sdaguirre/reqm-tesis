package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOPermisos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblPermisos";
    public static final int F_ROL=1,F_NEW=2;
    private long lPermisoId;
    private int iRolId,iSitioId;
    private boolean bConsultarFl,bInsertarFl,bModificarFl,bEliminarFl;
    //#End
//#Region Builders
    public DAOPermisos(){}

    public DAOPermisos(long lPermisoId, int iRolId, int iSitioId, boolean bConsultarFl, boolean bInsertarFl, boolean bModificarFl, boolean bEliminarFl) {
        this.lPermisoId = lPermisoId;
        this.iRolId = iRolId;
        this.iSitioId = iSitioId;
        this.bConsultarFl = bConsultarFl;
        this.bInsertarFl = bInsertarFl;
        this.bModificarFl = bModificarFl;
        this.bEliminarFl = bEliminarFl;
    }
    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lKeyId,int filter) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lKeyId,filter});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }

    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lPermisoId=insertRecord(tabla, new Object[]{iRolId,iSitioId,bConsultarFl,bInsertarFl,bModificarFl,bEliminarFl});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lPermisoId,iRolId,iSitioId,bConsultarFl,bInsertarFl,bModificarFl,bEliminarFl});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lPermisoId);
    }
    public static boolean changeAccess(String value,String checker) throws SQLException {
        return processRecord("chk_", tabla, new Object[]{new Long(value),new Integer(checker)})>0;
    }
    //#End
//#Region Accessors
    public boolean isbConsultarFl() {
        return bConsultarFl;
    }

    public void setbConsultarFl(boolean bConsultarFl) {
        this.bConsultarFl = bConsultarFl;
    }

    public boolean isbEliminarFl() {
        return bEliminarFl;
    }

    public void setbEliminarFl(boolean bEliminarFl) {
        this.bEliminarFl = bEliminarFl;
    }

    public boolean isbInsertarFl() {
        return bInsertarFl;
    }

    public void setbInsertarFl(boolean bInsertarFl) {
        this.bInsertarFl = bInsertarFl;
    }

    public boolean isbModificarFl() {
        return bModificarFl;
    }

    public void setbModificarFl(boolean bModificarFl) {
        this.bModificarFl = bModificarFl;
    }

    public int getiRolId() {
        return iRolId;
    }

    public void setiRolId(int iRolId) {
        this.iRolId = iRolId;
    }

    public int getiSitioId() {
        return iSitioId;
    }

    public void setiSitioId(int iSitioId) {
        this.iSitioId = iSitioId;
    }

    public long getlPermisoId() {
        return lPermisoId;
    }

    public void setlPermisoId(long lPermisoId) {
        this.lPermisoId = lPermisoId;
    }

    //#End
}