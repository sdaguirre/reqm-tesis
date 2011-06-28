package dao;

import java.sql.SQLException;

public interface IDAO {
    /**M&eacute;todo para la inserci&oacute;n de registros.
     * @return <code>true</code> -> Si se insert&oacute; correctamente el registro<br><code>false</code> ->Si fallo al insertar el registro.
     * @throws SQLException Excepci&oacute;n de Base de Datos.
     */
    public boolean insert() throws SQLException ;
    /**M&eacute;todo para la actualizaci&oacute; de registros.
     * @return <code>true</code> -> Si se actualiz&oacute; correctamente<br><code>false</code>-> Si fall&oacute; al actualizar el registro.
     * @throws SQLException Excepci&oacute;n de Base de Datos.
     */
    public boolean update() throws SQLException ;
    /**M&eacute;todo para eliminar registros.
     * @return <code>true</code> -> Si elimin&oacute; correctamente el registro<br><code>false</code> Si fall&oacute; al eliminar el registro.
     * @throws SQLException Excepci&oacute;n de Base de Datos.
     */
    public boolean delete() throws SQLException ;
}
