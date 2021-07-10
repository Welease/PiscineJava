package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(args[0].substring(7));
        server.startServer();
    }
}
