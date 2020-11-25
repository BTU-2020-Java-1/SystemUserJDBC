package ge.edu.btu.user;

import ge.edu.btu.user.dao.SystemUserDAO;
import ge.edu.btu.user.dao.SystemUserDAOImpl;
import ge.edu.btu.user.model.SystemUser;

import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {
        SystemUserDAO systemUserDAO = new SystemUserDAOImpl();

//        systemUserDAO.addUser(new SystemUser(0, "Soso", "Gobronidze", true));
//        systemUserDAO.addUser(new SystemUser(0, "George", "Arabidze", false));

//        systemUserDAO.deleteById(1);

        List<SystemUser> users = systemUserDAO.getAll();
        for (SystemUser user : users) {
            System.out.println(user);
        }

        systemUserDAO.closeConnection();
    }
}
