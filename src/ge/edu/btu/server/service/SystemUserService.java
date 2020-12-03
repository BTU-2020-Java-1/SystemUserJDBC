package ge.edu.btu.server.service;

import ge.edu.btu.common.SystemUserDTO;
import ge.edu.btu.server.model.SystemUserException;

import java.util.List;

public interface SystemUserService {

    void addUser(String username, String password1, String password2) throws SystemUserException;

    boolean authorize(String username, String password);

    List<SystemUserDTO> getAllUser() throws SystemUserException;
}
