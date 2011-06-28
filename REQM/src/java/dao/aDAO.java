package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public abstract class aDAO extends DAO implements IDAO{
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">
    public static final String tabla="";    
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">
    //public DAO(){}
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">
    public boolean insert() throws SQLException {
        //this.iMesa_id=insertRecord(tabla, new Object[]{});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, null,0);
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">
// </editor-fold>
}