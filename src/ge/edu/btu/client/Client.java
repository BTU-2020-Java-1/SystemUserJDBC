package ge.edu.btu.client;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.SystemUserDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8080);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true) {
            System.out.println("1. მომხმარებლის დამატება");
            System.out.println("2. ავტორიზაცია");
            System.out.println("3. მომხმარებლების ნახვა");
            System.out.println("0. გასვლა");

            String option = scanner.nextLine();
            if (option.equals("0")) {
                out.writeObject(Command.EXIT);
                out.close();
                in.close();
                socket.close();
                break;
            }
            switch (option) {
                case "1" :
                    System.out.println("სახელი:");
                    String username = scanner.nextLine();
                    System.out.println("პაროლი:");
                    String password1 = scanner.nextLine();
                    System.out.println("გაიმეორეთ პაროლი:");
                    String password2 = scanner.nextLine();

                    out.writeObject(Command.ADD_USER);
                    out.writeObject(username);
                    out.writeObject(password1);
                    out.writeObject(password2);
                    out.reset();
                    out.flush();

                    String message = (String) in.readObject();
                    System.out.println(message);
                    break;
                case "2" :
                    System.out.println("სახელი:");
                    username = scanner.nextLine();
                    System.out.println("პაროლი:");
                    String password = scanner.nextLine();

                    out.writeObject(Command.AUTHORIZE);
                    out.writeObject(username);
                    out.writeObject(password);
                    Boolean authorized = (Boolean) in.readObject();
                    if (authorized) {
                        System.out.println("Success");
                    } else {
                        System.out.println("Failure");
                    }
                    break;
                case "3" :
                    out.writeObject(Command.GET_ALL_USER);
                    List<SystemUserDTO> dtos = (List<SystemUserDTO>) in.readObject();
                    for (SystemUserDTO dto : dtos) {
                        System.out.println(dto.getUsername() + " " + dto.isActive());
                    }
                    break;
            }
        }
    }
}
