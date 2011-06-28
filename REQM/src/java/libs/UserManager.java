package libs;

import java.io.Serializable;

/**
 *
 * @author Sergio Aguirre
 */
public class UserManager implements Serializable{
    private String username="";
    private String permisos;

    public UserManager() {
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
}
