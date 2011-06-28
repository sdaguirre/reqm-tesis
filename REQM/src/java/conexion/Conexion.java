package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Conexion implements IConexion {
    protected String host,connectionString,database,instance, port, username, password;
    protected Connection con;
    protected static Conexion singleton;
    protected Statement stmnt;

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public String getInstance() {
        return instance;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }
    /**Obtiene la instancia para comunicarse con la base de datos, si esta no existe se crea,
     * @param host Direcci&oacute;n o nombre del Servidor donde se encuentra el DBMS.
     * @param username Nombre de usuario a acceder
     * @param password Contrase&ntilde; del usuario
     * @param database Base de Datos que acceder&aacute; el usuario
     * @return Retorna la conexi&oacute;
     * @throws SQLException
     */
    public static Conexion connect(String host, String username, String password, String database,String port) throws SQLException {
        singleton = ConexionPSQL.getOrCreate(host, username, password,database,port);
        return singleton;
    }
     public static Conexion autoConnect() throws SQLException {
        singleton = ConexionPSQL.getOrCreate("192.168.1.115", "soldig", "soldig","REQM","5432");
        return singleton;
    }

    public Connection getCon() {
        return con;
    }
    public static Conexion getConnection(){
        return singleton;
    }
    public static void setConnection(ConexionPSQL conector){
        singleton=conector;
    }
    public static void Destroy() throws SQLException{
        singleton.disconnect();
        singleton=null;
        System.gc();
    }
}
