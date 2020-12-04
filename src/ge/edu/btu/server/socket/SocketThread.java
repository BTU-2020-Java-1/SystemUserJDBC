package ge.edu.btu.server.socket;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.SystemUserDTO;
import ge.edu.btu.server.model.SystemUserException;
import ge.edu.btu.server.service.SystemUserService;
import ge.edu.btu.server.service.SystemUserServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class SocketThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private SystemUserService systemUserService;

    public SocketThread(Socket socket) throws Exception {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.systemUserService = new SystemUserServiceImpl();
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Command command = (Command) in.readObject();
                switch (command) {
                    case ADD_USER:
                        String username = (String) in.readObject();
                        String password1 = (String) in.readObject();
                        String password2 = (String) in.readObject();
                        try {
                            systemUserService.addUser(username, password1, password2);
                            out.writeObject("Success");
                        } catch (SystemUserException ex) {
                            out.writeObject(ex.getMessage());
                        }
                        out.reset();
                        out.flush();
                        break;
                    case AUTHORIZE:
                        username = (String) in.readObject();
                        String password = (String) in.readObject();
                        boolean authorized = systemUserService.authorize(username, password);
                        out.writeObject(authorized);
                        out.reset();
                        out.flush();
                        break;
                    case GET_ALL_USER:
                        List<SystemUserDTO> dtos = systemUserService.getAllUser();
                        out.writeObject(dtos);
                        out.reset();
                        out.flush();
                        break;
                    case EXIT:
                        out.close();
                        in.close();
                        socket.close();
                        finished = true;
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
