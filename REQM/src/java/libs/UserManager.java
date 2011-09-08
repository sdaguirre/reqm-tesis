package libs;

import java.io.Serializable;
import org.w3c.dom.Document;

/**
 *
 * @author Sergio Aguirre
 */
public class UserManager implements Serializable {

    private String username = "";
    private String permisos;
    private Document brief;
    private Long PersonaId;
    private boolean logged = false, bClient = false;

    public UserManager() {
    }

    public Document getBrief() {
        return brief;
    }

    public void setBrief(Document brief) {
        this.brief = brief;
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

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Long getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(Long PersonaId) {
        this.PersonaId = PersonaId;
    }

    public boolean isbClient() {
        return bClient;
    }

    public void setbClient(boolean bClient) {
        this.bClient = bClient;
    }
}
