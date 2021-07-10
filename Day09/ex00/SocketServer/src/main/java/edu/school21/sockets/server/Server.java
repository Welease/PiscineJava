package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private Socket clientSocket = null;
    private ServerSocket server = null;
    private BufferedReader reader = null;
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private Integer port;

    public Server(String port) {
        this.port = Integer.parseInt(port);
    }

    public void startServer() {
        try {
            server = new ServerSocket(port);
            clientSocket = server.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));
            out.write("Hello from Server!\n");
            out.flush();
            String startMsg = in.readLine();
            System.out.println(startMsg);
            out.write("Enter username:\n");
            out.flush();
            String userName = in.readLine();
            out.write("Enter password:\n");
            out.flush();
            String password = in.readLine();
            ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
            UsersService usersService = context.getBean(UsersService.class);
            if (usersService.signUp(userName, password)) {
                out.write("Successful!\n");
            } else {
                out.write("Такой пользователь уже зарегистрирован\n");
            }
            out.flush();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    in.close();
                    clientSocket.close();
                    server.close();
                    out.close();
                } catch (IOException e) { e.printStackTrace(); }
            }
    }
}