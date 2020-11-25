package ge.edu.btu.user.dao;

import ge.edu.btu.user.model.SystemUser;

import java.util.List;

public interface SystemUserDAO {

    void addUser(SystemUser user) throws Exception;

    void addUsers(List<SystemUser> users);

    void deleteById(long id) throws Exception;

    void setActiveForUser(long id, boolean active);

    List<SystemUser> getAll() throws Exception;

    void closeConnection() throws Exception;
}
