package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;

public class DAOEmpleados extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblEmpleados";
    private long lEmpleadoId,lPersonaId;
    private int iPCargoId,iEstadoFl;
    private double dSalarioAmt;
    private Date dtIngresoDt,dtEgresoDt;
    private String sEmpleadoNm;
    //#End
//#Region Builders
    public DAOEmpleados(){}

    public DAOEmpleados(long lEmpleadoId, String sEmpleadoNm, int iPCargoId, double dSalarioAmt,
            Date dtIngresoDt, Date dtEgresoDt,int iEstadoFl) {
        this.lEmpleadoId = lEmpleadoId;
        this.sEmpleadoNm=sEmpleadoNm;
        this.iPCargoId = iPCargoId;
        this.iEstadoFl = iEstadoFl;
        this.dSalarioAmt = dSalarioAmt;
        this.dtIngresoDt = dtIngresoDt;
        this.dtEgresoDt = dtEgresoDt;
    }
    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords() throws SQLException{
        return getXMLTable(tabla);
    }
    public static SQLXML getXMLRecords(long lEmpleadoId) throws SQLException{
        return getXMLRecords(tabla,lEmpleadoId);
    }
    public static ArrayList<DAOEmpleados> getRecords(int limit) throws SQLException {
        if (limit==0)
            return filldao(getTableDAO(tabla,"",limit));
        else
            return filldao(getTableDAO(tabla,"lEmpleado_id desc ",limit));
    }
    public static ArrayList<DAOEmpleados> search(String value) throws SQLException {
        return filldao(searchRecords(tabla,"%"+value+"%"));
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lEmpleadoId=insertRecord(tabla, new Object[]{sEmpleadoNm,iPCargoId,dSalarioAmt,dtIngresoDt,iEstadoFl});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lEmpleadoId,lPersonaId,sEmpleadoNm,iPCargoId,dSalarioAmt,dtIngresoDt,dtEgresoDt,iEstadoFl});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lEmpleadoId);
    }

    public double getdSalarioAmt() {
        return dSalarioAmt;
    }

    public void setdSalarioAmt(double dSalarioAmt) {
        this.dSalarioAmt = dSalarioAmt;
    }

    public Date getDtEgresoDt() {
        return dtEgresoDt;
    }

    public void setDtEgresoDt(Date dtEgresoDt) {
        this.dtEgresoDt = dtEgresoDt;
    }

    public Date getDtIngresoDt() {
        return dtIngresoDt;
    }

    public void setDtIngresoDt(Date dtIngresoDt) {
        this.dtIngresoDt = dtIngresoDt;
    }

    public int getiEstadoFl() {
        return iEstadoFl;
    }

    public void setiEstadoFl(int iEstadoFl) {
        this.iEstadoFl = iEstadoFl;
    }

    public int getiPCargoId() {
        return iPCargoId;
    }

    public void setiPCargoId(int iPCargoId) {
        this.iPCargoId = iPCargoId;
    }

    public long getlEmpleadoId() {
        return lEmpleadoId;
    }

    public void setlEmpleadoId(long lEmpleadoId) {
        this.lEmpleadoId = lEmpleadoId;
    }

    public long getlPersonaId() {
        return lPersonaId;
    }

    public void setlPersonaId(long lPersonaId) {
        this.lPersonaId = lPersonaId;
    }
    //#End
//#Region Accessors

    //#End
//#Region Filler
    /**Llena un Vector de tipo DAO con los datos contenidos en un ResultSet
     * @param rs ResultSet obtenido de la consulta a la base de datos
     * @return Retorna un vector con los datos especializados en DAOs de tipo Persona Fisica
     * @throws SQLException
     */
    private static ArrayList<DAOEmpleados> filldao(ResultSet rs) throws SQLException {
        ArrayList<DAOEmpleados> vec=new ArrayList<DAOEmpleados>();
        while(rs.next()){
            vec.add(new DAOEmpleados(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getDate(5),rs.getDate(6),rs.getInt(7)));
        }
        rs.close();
        System.gc();
        return vec;
    }
    //#End
}