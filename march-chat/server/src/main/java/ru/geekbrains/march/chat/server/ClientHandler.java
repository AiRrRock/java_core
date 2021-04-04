package ru.geekbrains.march.chat.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Statement;

public class ClientHandler {
    private static final String IDENTITY_REQUEST = "who_am_i";
    private static final String PERSONAL_MSG_REQUEST = "w";
    private static final String CHANGE_NAME_REQUEST = "change_nick";
    private static final String LOGOUT_REQUEST = "logout";


    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private boolean loggingOut;

    public String getUsername() {
        return username;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.loggingOut = false;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) { // Цикл авторизации
                    String msg = in.readUTF();
                    if (msg.startsWith("/login ")) {
                        // /login Bob 100xyz
                        String[] tokens = msg.split("\\s+");
                        if (tokens.length != 3) {
                            sendMessage("/login_failed Enter login and password");
                            continue;
                        }
                        String login = tokens[1];
                        String password = tokens[2];

                        String userNickname = server.getAuthenticationProvider().getNicknameByLoginAndPassword(login, password);
                        if (userNickname == null) {
                            sendMessage("/login_failed Incorrect login/password");
                            continue;
                        }
                        if (server.isUserOnline(userNickname)) {
                            sendMessage("/login_failed Current nickname is already in use\"1111");
                            continue;
                        }
                        username = userNickname;
                        sendMessage("/login_ok " + username);
                        server.subscribe(this);
                        sendMessage("Welcome back, " + username);
                        break;
                    }
                }

                while (true) { // Цикл общения с клиентом
                    if (loggingOut) {
                        System.out.println(username + " disconnected");
                        break;
                    }
                    String msg = in.readUTF();
                    if (msg.startsWith("/")) {
                        processCommands(msg.substring(1));
                        continue;
                    }
                    server.broadcastMessage(username + ": " + msg);
                }
            } catch (SocketException sc) {
                System.out.println(username + " disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            disconnect();
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processCommands(String command) throws IOException {
        String[] strings = command.split("\\s+");
        switch (strings[0].toLowerCase()) {
            case PERSONAL_MSG_REQUEST:
                StringBuilder msg = new StringBuilder();
                if (strings.length > 2) {
                    for (int i = 2; i < strings.length; i++) {
                        msg.append(strings[i]);
                        msg.append(" ");
                    }
                }
                server.sendPrivateMessage(this, strings[1], msg.toString());
                break;
            case IDENTITY_REQUEST:
                this.sendMessage(username);
                break;
            case CHANGE_NAME_REQUEST:
                if (strings.length == 2) {
                    String newName = strings[1];
                    if (server.getAuthenticationProvider().isNickBusy(newName)) {
                        sendMessage("Server: Nickname is already in use");
                        return;
                    }
                    else {
                        server.getAuthenticationProvider().changeNickname(username, newName);
                        username = newName;
                        this.sendMessage(String.format("Server: Changed name to %s", newName));
                        server.broadcastClientsList();
                    }
                } else {
                    this.sendMessage("Server: Unable to change name. Incorrect parameters.");
                }
                break;
            case LOGOUT_REQUEST:
                sendMessage("/logout_ok");
                loggingOut = true;
                break;
        }
    }
}
