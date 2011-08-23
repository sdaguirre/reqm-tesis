package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;

public class DAOAnteproyectos extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblAnteproyectos";
    private long lAnteproyectoId,lPersonaId;
    private int iPTipoId,iPCategoriaId;
    private String sAnteproyectoNm,sAnteproyectoDesc;
	//#End
//#Region Builders
    public DAOAnteproyectos(){}
    public DAOAnteproyectos(long lAnteproyectoId,int iPTipoId,int iPCategoriaId,long lPersonaId,
            String sAnteproyectoNm,String sAnteproyectoDesc){
        this.lAnteproyectoId=lAnteproyectoId;
        this.iPTipoId=iPTipoId;
        this.iPCategoriaId=iPCategoriaId;
        this.lPersonaId=lPersonaId;
        this.sAnteproyectoNm=sAnteproyectoNm;
        this.sAnteproyectoDesc=sAnteproyectoDesc;
    }
	//#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(Long lPersonaId) throws SQLException{
        return getXMLRecords(tabla,lPersonaId);
    }
    public static SQLXML getXMLRecords(Long lPersonaId,Long lAnteproyectoId) throws SQLException{
        return getProcessXML("gxml_",tabla,new Object[]{lPersonaId,lAnteproyectoId});
    }
    public static ArrayList<DAO> getRecords(int limit) throws SQLException {
        if (limit==0)
            return filldao(getTableDAO(tabla,"",limit));
        else
            return filldao(getTableDAO(tabla,"lAnteproyecto_id desc ",limit));
    }
    public static ArrayList<DAO> search(String value) throws SQLException {
        return filldao(searchRecords(tabla,"%"+value+"%"));
    }
    public static SQLXML searchXML(Long lPersonaId,String value) throws SQLException {
        return getProcessXML("srch_", tabla, new Object[]{lPersonaId,"%"+value+"%"});
    }
	//#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.setlAnteproyectoId(insertRecord(tabla, new Object[]{iPTipoId,iPCategoriaId,lPersonaId,sAnteproyectoNm,sAnteproyectoDesc}));
        return true;
    }
    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lAnteproyectoId,iPTipoId,iPCategoriaId,lPersonaId,sAnteproyectoNm,sAnteproyectoDesc});
    }
    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lAnteproyectoId);
    }
//#End
//#Region Accessors
    public long getlAnteproyectoId() {
        return lAnteproyectoId;
    }

    public void setlAnteproyectoId(long lAnteproyectoId) {
        this.lAnteproyectoId = lAnteproyectoId;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }

    public int getiPTipoId() {
        return iPTipoId;
    }

    public void setiPTipoId(int iPTipoId) {
        this.iPTipoId = iPTipoId;
    }

    public int getiPCategoriaId() {
        return iPCategoriaId;
    }

    public void setiPCategoriaId(int iPCategoriaId) {
        this.iPCategoriaId = iPCategoriaId;
    }

    public String getsAnteproyectoNm() {
        return sAnteproyectoNm;
    }

    public void setsAnteproyectoNm(String sAnteproyectoNm) {
        this.sAnteproyectoNm = sAnteproyectoNm;
    }

    public String getsAnteproyectoDesc() {
        return sAnteproyectoDesc;
    }

    public void setsAnteproyectoDesc(String sAnteproyectoDesc) {
        this.sAnteproyectoDesc = sAnteproyectoDesc;
    }
        
	//#End
//#Region Filler
    /**Llena un Vector de tipo DAO con los datos contenidos en un ResultSet
     * @param rs ResultSet obtenido de la consulta a la base de datos
     * @return Retorna un vector con los datos especializados en DAOs de tipo Persona Fisica
     * @throws SQLException
     */
    private static ArrayList<DAO> filldao(ResultSet rs) throws SQLException {
        ArrayList<DAO> vec=new ArrayList<DAO>();
        while(rs.next()){
        	vec.add(new DAOAnteproyectos(rs.getLong(1),rs.getInt(2),rs.getInt(3),rs.getLong(4),rs.getString(5),rs.getString(6)));
            }
        rs.close();
        System.gc();
        return vec;
    }
	//#End
}