package dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Timestamp;
import java.util.Vector;
import conexion.Conexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;

public abstract class DAO {
    //#Region Records

    /**Prepara la consulta SQL de Insert o Update
     * @param tabla Nombre de la Tabla donde se va a ingresar el registro
     * @param datos Array de todos los datos que se ingresan a la tabla
     * @param update Booleano que indica si la sentencia es de inserci&oacute;n o actualizaci&oacute;n
     * @return Devuelve el estado true o false indicando si el registro se ingres&oacute; correctamente
     * @throws SQLException
     */
    private static long makeRecord(String tabla, Object[] datos, boolean update) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "";
        if (!update) {
            statement = "SELECT INS_" + tabla + " (";
        } else {
            statement = "SELECT UPD_" + tabla + " (";
        }
        stmt = con.getCon().prepareStatement(prepareSQL(datos.length, statement));
        return con.executeSQL(fillSQL(stmt, datos));
    }

    public static long processRecord(String operator, String tabla, Object[] datos) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "";
        statement = "SELECT " + operator + tabla + " (";
        stmt = con.getCon().prepareStatement(prepareSQL(datos.length, statement));
        return con.executeSQL(fillSQL(stmt, datos));
    }

    /**Inserci&oacute;n de Registros
     * @param tabla Nombre de la Tabla donde se va a ingresar el registro
     * @param datos Array de todos los datos que se ingresan a la tabla
     * @return Devuelve el estado true o false indicando si el registro se ingres&oacute; correctamente
     * @throws SQLException
     */
    protected static long insertRecord(String tabla, Object[] datos) throws SQLException {
        return makeRecord(tabla, datos, false);
    }

    /**Actualizaci&oacute;n de Registros
     * @param tabla Nombre de la Tabla donde se va a ingresar el registro
     * @param datos Array de todos los datos que cuenta la tabla
     * @return Devuelve el estado true o false indicando si el registro se actualiz&oacute; correctamente
     * @throws SQLException
     */
    protected static boolean updateRecord(String tabla, Object[] datos) throws SQLException {
        return makeRecord(tabla, datos, true) == 1;
    }

    /**Elimina un registro de una tabla
     * @param tabla Nombre de la Tabla de donde se va a eliminar el registro
     * @param where Valor del ID del registro a eliminar
     * @return Devuelve el estado true o false indicando si la eliminaci&oacute;n ha sido exitosa
     * @throws SQLException
     */
    protected static boolean deleteRecord(String tabla, Object where, Object concurr) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "SELECT DEL_" + tabla + " (";
        if (where.getClass().equals(String.class)) {
            statement += "'" + where + "'";
        } else {
            if (where.getClass().equals(Date.class) || where.getClass().equals(Timestamp.class)) {
                String str = "'" + where + "'";
                statement += str.replace("-", "");
            } else {
                statement += where + "," + concurr + ")";
            }
        }
        stmt = con.getCon().prepareStatement(statement);
        long a = con.executeSQL(stmt);
        stmt.close();
        return a == 1l;
    }

    protected static boolean deleteRecord(String tabla, Object where) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "SELECT DEL_" + tabla + " (";
        if (where.getClass().equals(String.class)) {
            statement += "'" + where + "'";
        } else {
            if (where.getClass().equals(Date.class) || where.getClass().equals(Timestamp.class)) {
                String str = "'" + where + "'";
                statement += str.replace("-", "");
            } else {
                statement += where + ")";
            }
        }
        stmt = con.getCon().prepareStatement(statement);
        long a = con.executeSQL(stmt);
        stmt.close();
        return a == 1l;
    }
    //#End
    //#Region Queries

    protected static boolean consulta(String query) throws SQLException {
        Conexion con = Conexion.getConnection();
        con.beginTransact();
        int a = con.executeSimple(query);
        con.endTransact();
        return a >= 0;
    }

    protected static Object function(String query) throws SQLException {
        Conexion con = Conexion.getConnection();
        con.beginTransact();
        ResultSet a = con.executeSQL(query);
        con.endTransact();
        a.next();
        return a.getObject(1);
    }

    protected static ResultSet process(String query) throws SQLException {
        Conexion con = Conexion.getConnection();
        ResultSet a = con.executeSQL(query);
        return a;
    }
    //#End
    //#Region SearchAndGetters

    protected static Vector<String> getTablas() throws SQLException {
        Conexion con = Conexion.getConnection();
        con.beginTransact();
        ResultSet rs = con.executeSQL("SELECT tablename from pg_tables where schemaname='public'");
        con.endTransact();
        Vector<String> aux = new Vector<String>();
        while (rs.next()) {
            aux.add(rs.getString(1));
        }
        return aux;
    }

    protected static Vector<Object[]> getTable(String tabla, Object[] where) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "SELECT * FROM " + tabla + " WHERE ";
        for (int i = 0; i < where.length; i++) {
            if (where[i + 1].getClass() == String.class) {
                statement += where[i] + "='" + where[i + 1] + "'";
            } else {
                statement += where[i] + "=" + where[i + 1];
            }
            i++;
            if (i + 1 < where.length) {
                statement += " AND ";
            }
        }
        con.beginTransact();
        ResultSet rs = con.executeSQL(statement);
        con.endTransact();
        Vector<Object[]> aux = new Vector<Object[]>();
        while (rs.next()) {
            Object[] objarray = new Object[rs.getMetaData().getColumnCount()];
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                objarray[i] = rs.getObject(i + 1);
            }
            aux.add(objarray);
        }
        return aux;
    }

    protected static Vector<Object[]> getTable(String tabla, String orden) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "SELECT * FROM " + tabla;
        if (!orden.equals("")) {
            statement += " order by " + orden;
        }
        con.beginTransact();
        ResultSet rs = con.executeSQL(statement);
        con.endTransact();
        Vector<Object[]> aux = new Vector<Object[]>();
        while (rs.next()) {
            Object[] objarray = new Object[rs.getMetaData().getColumnCount()];
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                objarray[i - 1] = rs.getObject(i);
            }
            aux.add(objarray);
        }
        return aux;
    }

    protected static Vector<Vector<Object>> getTable(String tabla) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "SELECT L" + tabla;
        ResultSet rs = con.executeSQL(statement);
        Vector<Vector<Object>> aux = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> objvec = new Vector<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                objvec.add(rs.getObject(i));
            }
            aux.add(objvec);
        }
        rs.close();
        System.gc();
        return aux;
    }

    protected static SQLXML getXMLTable(String tabla) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "SELECT GXML_" + tabla + "()";
        ResultSet rs = con.executeSQL(statement);
        if (rs.next()) {
            return rs.getSQLXML(1);
        } else {
            return null;
        }
    }

    protected static SQLXML getXMLQuery(String tabla, String where) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "SELECT QUERY_TO_XML ('SELECT * FROM " + tabla + " WHERE " + where + "',FALSE,FALSE,'')";
        ResultSet rs = con.executeSQL(statement);
        if (rs.next()) {
            return rs.getSQLXML(1);
        } else {
            return null;
        }
    }

    protected static ResultSet getTableDAO(String tabla, String orden, int cantidad) throws SQLException {
        Conexion con = Conexion.getConnection();
        String statement = "";
        if (cantidad == 0) {
            statement = "SELECT * FROM " + tabla;
        } else {
            statement = "SELECT * FROM " + tabla;
        }
        if (!orden.equals("")) {
            statement += " ORDER BY " + orden + " LIMIT " + cantidad;
        }
        ResultSet rs = con.executeSQL(statement);
        return rs;
    }

    protected static Object[] getReg(String tabla, Object keyval) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "SELECT * FROM REC_" + tabla + " (?)";
        stmt = con.getCon().prepareStatement(statement);
        stmt.setObject(1, (keyval));
        ResultSet rs = con.getRecordsSQL(stmt);
        rs.next();
        int colcount = rs.getMetaData().getColumnCount();
        Object[] objarray = new Object[colcount];
        for (int i = 0; i < colcount; i++) {
            objarray[i] = rs.getObject((i + 1));
        }
        rs.close();
        stmt.close();
        return objarray;
    }

    protected static SQLXML searchXMLRecords(String tabla, Object keyval) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "SELECT * FROM SRCH_" + tabla + " (?)";
        stmt = con.getCon().prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        stmt.setObject(1, (keyval));
        ResultSet rs = con.getRecordsSQL(stmt);
        if (rs.next()) {
            return rs.getSQLXML(1);
        } else {
            return null;
        }
    }

    protected static ResultSet searchRecords(String tabla, Object keyval) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "SELECT * FROM SRCH_" + tabla + " (?)";
        stmt = con.getCon().prepareStatement(statement, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        stmt.setObject(1, (keyval));
        ResultSet rs = con.getRecordsSQL(stmt);
        return rs;
    }

    protected static SQLXML getXMLRecords(String tabla, Object keyval) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement;
        if (keyval == null) {
            statement = "SELECT * FROM GXML_" + tabla + "()";
        } else {
            statement = "SELECT * FROM GXML_" + tabla + " (?)";
        }
        stmt = con.getCon().prepareStatement(statement);
        if (keyval != null) {
            stmt.setObject(1, (keyval));
        }
        ResultSet rs = con.getRecordsSQL(stmt);
        if (rs.next()) {
            return rs.getSQLXML(1);
        } else {
            return null;
        }
    }

    protected static ResultSet getRecords(String tabla, Object keyval) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement;
        if (keyval == null) {
            statement = "SELECT * FROM GET_" + tabla + "()";
        } else {
            statement = "SELECT * FROM GET_" + tabla + " (?)";
        }
        stmt = con.getCon().prepareStatement(statement);
        if (keyval != null) {
            stmt.setObject(1, (keyval));
        }
        ResultSet rs = con.getRecordsSQL(stmt);
        return rs;
    }

    public static ResultSet getProcessRecords(String operator, String tabla, Object[] datos) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "";
        statement = "SELECT * FROM " + operator + tabla + " (";
        stmt = con.getCon().prepareStatement(prepareSQL(datos.length, statement));
        return con.getRecordsSQL(fillSQL(stmt, datos));
    }

    public static SQLXML getProcessXML(String operator, String tabla, Object[] datos) throws SQLException {
        Conexion con = Conexion.getConnection();
        PreparedStatement stmt = null;
        String statement = "";
        statement = "SELECT * FROM " + operator + tabla + " (";
        stmt = con.getCon().prepareStatement(prepareSQL(datos.length, statement));
        ResultSet rs = con.getRecordsSQL(fillSQL(stmt, datos));
        if (rs.next()) {
            return rs.getSQLXML(1);
        } else {
            return null;
        }
    }
    //#End
    //#Region PreparerAndFiller

    /**Prepara una Sentencia SQL
     * @param largo Indica la cantidad de campos que requiere la consulta SQL
     * @param statement String donde se almacena el SQL p
     * @return
     */
    private static String prepareSQL(int largo, String statement) {
        for (int i = 0; i < largo; i++) {
            statement += "?,";
        }
        if (largo != 0) {
            statement = statement.substring(0, statement.length() - 1) + ")";
        } else {
            statement += ")";
        }
        return statement;
    }

    /**Llenar Sentencia SQL
     * @param stmt PreparedStatement que se desea llenar con los datos
     * @param datos Array de datos que llenar&aacute;n el PreparedStatement
     * @return Retorna el PreparedStatement con los datos cargados
     * @throws SQLException
     */
    private static PreparedStatement fillSQL(PreparedStatement stmt, Object[] datos) throws SQLException {
        for (int i = 1; i <= datos.length; i++) {
            if (datos[i - 1] == null) {
                stmt.setNull(i, java.sql.Types.DATE);
            } else if (datos[i - 1].getClass().equals(Date.class)) {
                stmt.setString(i, ((Date) datos[i - 1]).toString().replace("-", ""));
            } else if (datos[i - 1].getClass().equals(Timestamp.class)) {
                stmt.setObject(i, datos[i - 1], java.sql.Types.TIMESTAMP);
            } else if (datos[i - 1].getClass().equals(DiskFileItem.class)) {
                FileItem file=(FileItem)datos[i-1];
                try {
                    stmt.setBinaryStream(i, file.getInputStream(),(int)file.getSize());
                } catch (IOException ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                stmt.setObject(i, datos[i - 1]);
            }
        }
        return stmt;
    }
    //#End
}
