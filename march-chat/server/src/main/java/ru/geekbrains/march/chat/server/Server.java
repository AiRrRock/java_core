package ru.geekbrains.march.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private AuthenticationProvider authenticationProvider;

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authenticationProvider = new DbAuthenticationProvider();
        this.authenticationProvider.init();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started server. Listening at " + port);
            while (true) {
                System.out.println("Awaiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.authenticationProvider.shutdown();
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        broadcastMessage("Client " + clientHandler.getUsername() + " logged in");
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastMessage("Client " + clientHandler.getUsername() + " left");
        broadcastClientsList();
    }

    public synchronized void broadcastMessage(String message)  {
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(message);
        }
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String receiverUsername, String message) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(receiverUsername)) {
                c.sendMessage("From " + sender.getUsername() + ": " + message);
                sender.sendMessage("To " + receiverUsername + ": " + message);
                return;
            }
        }
        sender.sendMessage("Unable to send message to:" + receiverUsername + "User is offline");
    }

    public synchronized boolean isUserOnline(String username) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastClientsList() {
        StringBuilder stringBuilder = new StringBuilder("/clients_list ");
        for (ClientHandler c : clients) {
            stringBuilder.append(c.getUsername()).append(" ");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        String clientsList = stringBuilder.toString();
        for (ClientHandler clientHandler : clients) {
            clientHandler.sendMessage(clientsList);
        }
    }
}
