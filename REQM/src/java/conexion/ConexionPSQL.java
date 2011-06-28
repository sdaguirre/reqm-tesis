package conexion;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPSQL extends Conexion {
    public static Conexion getOrCreate(String host, String username,
                                       String password, String database,String port) throws SQLException {
        if (singleton == null) {
            singleton =
                    new ConexionPSQL(host, username, password, database,port);
        }
        return singleton;
    }

    public static Conexion getOrCreate() {
        return singleton;
    }
    //Puerto Default PSQL = 5432
    public ConexionPSQL(String host, String username, String password,
                             String database,String port) throws SQLException {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
        this.instance = "";
        this.port = port;
        this.connectionString = "jdbc:postgresql://"+this.host+":"+this.port+"/"+this.database;
        this.connect();
    }

    public void connect() throws SQLException {
        if (this.isConnected())
            return;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = this.connectionString;
        this.con = DriverManager.getConnection(url,username,password);
        System.out.println("Conexion Establecida - Java+PSQL Connection");        
    }

    public void beginTransact() throws SQLException {
        stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
     //   ejecutarSimple("begin");
    }

    public void endTransact() throws SQLException {
        executeSimple("commit");
       // stmnt.close();
    }

    public void disconnect() {
        try {
            if (this.isConnected()){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDbProperties() throws SQLException {
        java.sql.DatabaseMetaData dm = null;
        java.sql.ResultSet rs = null;
        connect();
        if (con != null) {
            dm = con.getMetaData();
            rs = dm.getCatalogs();
            while (rs.next()) {
                System.out.println(" catalogo: " + rs.getString(1));
            }
            rs.close();
            rs = null;
            disconnect();
        }else
            System.err.println("Error: No hay ninguna conexion activa");
        dm = null;
    }

    public ResultSet executeSQL(String query) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }

    public int executeSimple(String query) throws SQLException {
        Statement stmt = con.createStatement();
        int nb = stmt.executeUpdate(query);
        return nb;
    }

    public boolean isConnected() throws SQLException {
        try {
            return !con.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
    public long executeSQL(PreparedStatement sql) throws SQLException {
        ResultSet rs=sql.executeQuery();
        if(rs.next()){
            long a=rs.getLong(1);
            rs.close();
            sql.close();
            return a;
        }
        sql.close();
        return -1l;
    }
    public ResultSet getRecordsSQL(PreparedStatement sql) throws SQLException {
        return sql.executeQuery();
    }

}
