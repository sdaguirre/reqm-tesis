package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOIntegrantes extends DAO implements IDAO{
//#Region VarDeclarations
    public static final String tabla="tblIntegrantes";
    public static final int F_EQUIPO=1,F_NEW=2;
    private long lIntegranteId,lEmpleadoId;
    private int iEquipoId;
    //#End
//#Region Builders
    public DAOIntegrantes(){}
    public DAOIntegrantes(long lIntegranteId, int iEquipoId, long lEmpleadoId) {
        this.lIntegranteId = lIntegranteId;
        this.lEmpleadoId = lEmpleadoId;
        this.iEquipoId = iEquipoId;
    }
    //#End
//#Region SearchAndRecords
    public static SQLXML getXMLRecords(long lAnteproyectoId,int filter) throws SQLException{
        return getProcessXML("gxml_",tabla, new Object[]{lAnteproyectoId,filter});
    }
    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
    //#End
//#Region IUD
    public boolean insert() throws SQLException {
        this.lIntegranteId=insertRecord(tabla, new Object[]{iEquipoId,lEmpleadoId});
        return true;
    }
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lIntegranteId,iEquipoId,lEmpleadoId});
    }
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lIntegranteId);
    }
    //#End
//#Region Accessors
    public int getiEquipoId() {
        return iEquipoId;
    }

    public void setiEquipoId(int iEquipoId) {
        this.iEquipoId = iEquipoId;
    }

    public long getlEmpleadoId() {
        return lEmpleadoId;
    }

    public void setlEmpleadoId(long lEmpleadoId) {
        this.lEmpleadoId = lEmpleadoId;
    }

    public long getlIntegranteId() {
        return lIntegranteId;
    }

    public void setlIntegranteId(long lIntegranteId) {
        this.lIntegranteId = lIntegranteId;
    }
    //#End
}
