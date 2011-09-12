package dao;

import java.sql.SQLException;
import java.sql.SQLXML;

public class DAOObservaciones extends DAO implements IDAO{
// <editor-fold defaultstate="collapsed" desc="VarDeclarations">
    public static final String tabla="tblObservaciones";
    public static final int F_LISTA=1,F_OBSERVACION=2,F_NEW=3;
    private long lObservacionId,lRequerimientoId,lPOrigenId,lPDestinoId;
    private String sObservacionNm,sObservacionDesc;
    private boolean bLeidoFl;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Builders">
    public DAOObservaciones() {
    }

    public DAOObservaciones(long lObservacionId, long lRequerimientoId, long lPOrigenId, long lPDestinoId, String sObservacionNm, String sObservacionDesc, boolean bLeidoFl) {
        this.lObservacionId = lObservacionId;
        this.lRequerimientoId = lRequerimientoId;
        this.lPOrigenId = lPOrigenId;
        this.lPDestinoId = lPDestinoId;
        this.sObservacionNm = sObservacionNm;
        this.sObservacionDesc = sObservacionDesc;
        this.bLeidoFl = bLeidoFl;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SearchAndRecords">
    public static SQLXML getXMLRecords(long lPersonaId) throws SQLException{
        return getXMLRecords(tabla,lPersonaId);
    }
    public static SQLXML getXMLRecords(long lPersonaId,long lDocumentoId) throws SQLException{
        return getProcessXML("gxml_", tabla, new Object[]{lDocumentoId,lPersonaId});
    }

    public static SQLXML search(String value) throws SQLException {
        return searchXMLRecords(tabla,"%"+value+"%");
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="IUD">
    @Override
    public boolean insert() throws SQLException {
        this.lObservacionId=insertRecord(tabla, new Object[]{lPOrigenId,lPDestinoId,lRequerimientoId,sObservacionNm,sObservacionNm});
        return true;
    }
    @Override
    public boolean update() throws SQLException {
            return updateRecord(tabla, new Object[]{lObservacionId,lPOrigenId,lPDestinoId,lRequerimientoId,sObservacionNm,sObservacionNm,bLeidoFl});
    }
    @Override
    public boolean delete() throws SQLException {
            return deleteRecord(tabla, lObservacionId);
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Accessors">
    public boolean isbLeidoFl() {
        return bLeidoFl;
    }

    public void setbLeidoFl(boolean bLeidoFl) {
        this.bLeidoFl = bLeidoFl;
    }

    public long getlObservacionId() {
        return lObservacionId;
    }

    public void setlObservacionId(long lObservacionId) {
        this.lObservacionId = lObservacionId;
    }

    public long getlPDestinoId() {
        return lPDestinoId;
    }

    public void setlPDestinoId(long lPDestinoId) {
        this.lPDestinoId = lPDestinoId;
    }

    public long getlPOrigenId() {
        return lPOrigenId;
    }

    public void setlPOrigenId(long lPOrigenId) {
        this.lPOrigenId = lPOrigenId;
    }

    public long getlRequerimientoId() {
        return lRequerimientoId;
    }

    public void setlRequerimientoId(long lRequerimientoId) {
        this.lRequerimientoId = lRequerimientoId;
    }

    public String getsObservacionDesc() {
        return sObservacionDesc;
    }

    public void setsObservacionDesc(String sObservacionDesc) {
        this.sObservacionDesc = sObservacionDesc;
    }

    public String getsObservacionNm() {
        return sObservacionNm;
    }

    public void setsObservacionNm(String sObservacionNm) {
        this.sObservacionNm = sObservacionNm;
    }    
// </editor-fold>
}