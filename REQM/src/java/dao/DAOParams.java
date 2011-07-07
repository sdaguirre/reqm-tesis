package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOParams extends DAO implements IDAO {
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">

    public static final String CARGOS = "tblPCargos", RUBROS = "tblPRubros", DOCUMENTOS = "tblPDocumentos",
            CONTACTOS = "tblPContactos", TIPOS = "tblPTipos", CATEGORIAS = "tblPCategorias", COMPONENTES = "tblComponentes",
            PRODUCTOS="tblProductos",EQUIPOS="tblEquipos",SITIOS="tblSitios";
    private String tabla;
    private Long lParamId;
    private String sParamNm;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">

    public DAOParams(String tabla) {
        this.tabla = tabla;
    }

    public DAOParams(String tabla, Long lParamId, String sParamNm) {
        this.tabla = tabla;
        this.lParamId = lParamId;
        this.sParamNm = sParamNm;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">

    public static SQLXML getXMLRecords(String tabla) throws SQLException {
        return getXMLTable(tabla);
    }
    public static SQLXML getXMLRecord(String tabla,int key) throws SQLException {
        return getXMLRecords(tabla,key);
    }

    public static SQLXML search(String tabla,String value) throws SQLException {
        return searchXMLRecords(tabla, "%" + value + "%");
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">

    public boolean insert() throws SQLException {
        this.lParamId=insertRecord(tabla, new Object[]{sParamNm});
        return true;
    }

    public boolean update() throws SQLException {
        return updateRecord(tabla, new Object[]{lParamId,sParamNm});
    }

    public boolean delete() throws SQLException {
        return deleteRecord(tabla, lParamId);
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">

    public Long getlParamId() {
        return lParamId;
    }

    public void setlParamId(Long lParamId) {
        this.lParamId = lParamId;
    }

    public String getsParamNm() {
        return sParamNm;
    }
    public static String getXMLParamNm(String tabla){
        return "<param>"+tabla.substring(3).toLowerCase()+"</param>";
    }
    public static String formatXMLParamNm(String tabla){
        return "<param>"+tabla+"</param>";
    }

    public void setsParamNm(String sParamNm) {
        this.sParamNm = sParamNm;
    }
// </editor-fold>
}
