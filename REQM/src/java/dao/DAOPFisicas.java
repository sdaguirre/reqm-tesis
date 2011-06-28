package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;

public class DAOPFisicas extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblPFisicas";
    private long lPersonaId;
    private String sPersonaNm;
	//#End
//#Region Builders
    public DAOPFisicas(){}

    public DAOPFisicas(long lPersonaId, String sPersonaNm) {
        this.lPersonaId = lPersonaId;
        this.sPersonaNm = sPersonaNm;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }
    public static SQLXML getXMLRecords(String key) throws SQLException{
        return getXMLRecords(tabla, new Long(key));
    }
    public static ArrayList<DAOPFisicas> getRecords(int limit) throws SQLException {
        if (limit==0)
            return filldao(getTableDAO(tabla,"",limit));
        else
            return filldao(getTableDAO(tabla,"lPersona_id desc ",limit));
    }
    public static ArrayList<DAOPFisicas> search(String value) throws SQLException {
        return filldao(searchRecords(tabla,"%"+value+"%"));
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lPersonaId=insertRecord(tabla, new Object[]{sPersonaNm});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lPersonaId,sPersonaNm});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lPersonaId);
    }
    //#End
//#Region Accessors
    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public String getsPersonaNm() {
        return sPersonaNm;
    }

    public void setsPersonaNm(String sPersonaNm) {
        this.sPersonaNm = sPersonaNm;
    }
    //#End
//#Region Filler
    /**Llena un Vector de tipo DAO con los datos contenidos en un ResultSet
     * @param rs ResultSet obtenido de la consulta a la base de datos
     * @return Retorna un vector con los datos especializados en DAOs de tipo Persona Fisica
     * @throws SQLException
     */
    private static ArrayList<DAOPFisicas> filldao(ResultSet rs) throws SQLException {
        ArrayList<DAOPFisicas> vec=new ArrayList<DAOPFisicas>();
        while(rs.next()){
        	vec.add(new DAOPFisicas(rs.getLong(1),rs.getString(2)));
            }
        rs.close();
        System.gc();
        return vec;
    }
    //#End
}