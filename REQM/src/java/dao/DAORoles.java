package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAORoles extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblRoles";
    private int iRolId,iEstadoFl;
    private String sRolNm;
    //#End
//#Region Builders
    public DAORoles(){}

    public DAORoles(int iRolId, String sRolNm,int iEstadoFl) {
        this.iRolId = iRolId;
        this.iEstadoFl = iEstadoFl;
        this.sRolNm = sRolNm;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }
    public static SQLXML getXMLRecords(String key) throws SQLException{
        return getXMLRecords(tabla, new Long(key));
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.iRolId=(int)insertRecord(tabla, new Object[]{sRolNm,iEstadoFl});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{iRolId,sRolNm,iEstadoFl});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, Long.parseLong(""+iRolId));
    }
    //#End
//#Region Accessors
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

    public String getsRolNm() {
        return sRolNm;
    }

    public void setsRolNm(String sRolNm) {
        this.sRolNm = sRolNm;
    }
    //#End
}