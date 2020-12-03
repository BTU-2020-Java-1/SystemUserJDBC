package ge.edu.btu.server.dao;

import ge.edu.btu.server.model.SystemUser;

import java.util.List;

public interface SystemUserDAO {

    void addUser(SystemUser user) throws Exception;

    void deleteById(long id) throws Exception;

    void setActiveForUser(long id, boolean active);

    List<SystemUser> getAll() throws Exception;

    void closeConnection() throws Exception;

    boolean checkIfUserExists(String username, String password) throws Exception;
}
