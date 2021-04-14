package ru.geekbrains.march.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private AuthenticationProvider authenticationProvider;
    private ExecutorService executorService;
    private static final Logger LOGGER = LogManager.getLogger(Server.class.getName());

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.authenticationProvider = new DbAuthenticationProvider();
        this.authenticationProvider.init();
        LOGGER.info("Started server");
        // In this example we can use Executors.newFixedThreadPool(N) to limit the number of active users

        //If we use Executors.newCachedThreadPool() the performance may degrade(in case we start with 15 active user and then decrease their number to i.e. 5)

        // The only benefit is the ability to shutdown all the clients safely .
        this.executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started server. Listening at " + port);
            while (true) {
                System.out.println("Awaiting connections...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new ClientHandler(this, socket);
                LOGGER.info("New client connected");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            LOGGER.info("Server is shutting down");
            executorService.shutdownNow();
            this.authenticationProvider.shutdown();
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
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
