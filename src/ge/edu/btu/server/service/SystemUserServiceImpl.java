package ge.edu.btu.server.service;

import ge.edu.btu.common.SystemUserDTO;
import ge.edu.btu.server.model.SystemUser;
import ge.edu.btu.server.model.SystemUserException;
import ge.edu.btu.server.dao.SystemUserDAO;
import ge.edu.btu.server.dao.SystemUserDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class SystemUserServiceImpl implements SystemUserService {

    private SystemUserDAO systemUserDAO;

    public SystemUserServiceImpl() throws Exception {
        systemUserDAO = new SystemUserDAOImpl();
    }

    @Override
    public void addUser(String username, String password1, String password2) throws SystemUserException {
        if (password1.equals(password2)) {
            try {
                SystemUser systemUser = new SystemUser();
                systemUser.setUsername(username);
                systemUser.setPassword(password1);
                systemUser.setActive(true);
                systemUserDAO.addUser(systemUser);
            } catch (Exception ex) {
                throw new SystemUserException("Unexpected Error");
            }
        } else {
            throw new SystemUserException("Passwords is not equal");
        }
    }

    @Override
    public boolean authorize(String username, String password) {
        try {
            return systemUserDAO.checkIfUserExists(username, password);
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public List<SystemUserDTO> getAllUser() throws SystemUserException {
        try {
            List<SystemUser> allUser = systemUserDAO.getAll();

            List<SystemUserDTO> dtos = new ArrayList<>();
            for (SystemUser systemUser : allUser) {
                SystemUserDTO dto = new SystemUserDTO();
                dto.setUsername(systemUser.getUsername());
                dto.setActive(systemUser.isActive());
                dtos.add(dto);
            }

            return dtos;
        } catch (Exception ex) {
            throw new SystemUserException("Unexpected Exception");
        }
    }
}
