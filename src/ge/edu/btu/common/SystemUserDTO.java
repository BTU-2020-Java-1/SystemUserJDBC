package ge.edu.btu.common;

import java.io.Serializable;

public class SystemUserDTO implements Serializable {

    private static final long serialVersionUID = 101L;

    private String username;

    private boolean active;

    public SystemUserDTO() {
    }

    public SystemUserDTO(String username, boolean active) {
        this.username = username;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
