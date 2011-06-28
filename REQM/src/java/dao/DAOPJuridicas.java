package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;

public class DAOPJuridicas extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblPJuridicas";
    private long lPersonaId;
    private int iPRubro_id;
    private String sPersonaRs;
    //#End
//#Region Builders
    public DAOPJuridicas(){}

    public DAOPJuridicas(long lPersonaId, int iPRubro_id, String sPersonaRs) {
        this.lPersonaId = lPersonaId;
        this.iPRubro_id = iPRubro_id;
        this.sPersonaRs = sPersonaRs;
    }

    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }
    public static ArrayList<DAOPJuridicas> getRecords(int limit) throws SQLException {
        if (limit==0)
            return filldao(getTableDAO(tabla,"",limit));
        else
            return filldao(getTableDAO(tabla,"lPersona_id desc ",limit));
    }
    public static ArrayList<DAOPJuridicas> search(String value) throws SQLException {
        return filldao(searchRecords(tabla,"%"+value+"%"));
    }
	//#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lPersonaId=insertRecord(tabla, new Object[]{iPRubro_id,sPersonaRs});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lPersonaId,iPRubro_id,sPersonaRs});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lPersonaId,0);
    }
    //#End
//#Region Accessors
    public int getiPRubro_id() {
        return iPRubro_id;
    }

    public void setiPRubro_id(int iPRubro_id) {
        this.iPRubro_id = iPRubro_id;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public String getsPersonaRs() {
        return sPersonaRs;
    }

    public void setsPersonaRs(String sPersonaRs) {
        this.sPersonaRs = sPersonaRs;
    }
    //#End
//#Region Filler
    /**Llena un Vector de tipo DAO con los datos contenidos en un ResultSet
     * @param rs ResultSet obtenido de la consulta a la base de datos
     * @return Retorna un vector con los datos especializados en DAOs de tipo Persona Fisica
     * @throws SQLException
     */
    private static ArrayList<DAOPJuridicas> filldao(ResultSet rs) throws SQLException {
        ArrayList<DAOPJuridicas> vec=new ArrayList<DAOPJuridicas>();
        while(rs.next()){
            vec.add(new DAOPJuridicas(rs.getLong(1),rs.getInt(2),rs.getString(3)));
        }
        rs.close();
        System.gc();
        return vec;
    }
    //#End
}