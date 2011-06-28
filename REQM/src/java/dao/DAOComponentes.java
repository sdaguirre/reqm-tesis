package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOComponentes extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblComponentes";
    private long lComponenteId;
    private String sComponenteNm;
    //#End
//#Region Builders
    public DAOComponentes(){}

    public DAOComponentes(long lComponenteId, String sComponenteNm) {
        this.lComponenteId = lComponenteId;
        this.sComponenteNm = sComponenteNm;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lComponenteId=insertRecord(tabla, new Object[]{sComponenteNm});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lComponenteId,sComponenteNm});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lComponenteId,0);
    }
    //#End
//#Region Accessors
    public long getlComponenteId() {
        return lComponenteId;
    }

    public void setlComponenteId(long lComponenteId) {
        this.lComponenteId = lComponenteId;
    }

    public String getsComponenteNm() {
        return sComponenteNm;
    }

    public void setsComponenteNm(String sComponenteNm) {
        this.sComponenteNm = sComponenteNm;
    }
    //#End
}