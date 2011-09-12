package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOReporter extends DAO{
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">
    public static final String tabla="vwReports";
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">

    public static SQLXML getXMLReport(long lKeyId, int report) throws SQLException {
        return getProcessXML("gxml_", tabla, new Object[]{lKeyId, report});
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">
    
// </editor-fold>
}