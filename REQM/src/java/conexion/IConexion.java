package conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConexion {
     public void connect() throws SQLException;
     public void disconnect() throws SQLException;
     public boolean isConnected() throws SQLException;
     public void beginTransact() throws SQLException;
     public void endTransact() throws SQLException;   
     public int executeSimple(String query) throws SQLException;
     public long executeSQL(PreparedStatement sql) throws SQLException;
     public ResultSet getRecordsSQL(PreparedStatement sql) throws SQLException;
     public ResultSet executeSQL(String query) throws SQLException;
 }
