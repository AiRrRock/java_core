package ru.geekbrains.march.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String filePath = "history.txt";

    @FXML
    TextField msgField, usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextArea msgArea;

    @FXML
    HBox loginPanel, msgPanel;

    @FXML
    ListView<String> clientsList;

    @FXML
    Button logoutButton;

    @FXML
    VBox userList;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;

    public void setUsername(String username) {
        this.username = username;
        boolean userNameIsNull = username == null;
        loginPanel.setVisible(userNameIsNull);
        loginPanel.setManaged(userNameIsNull);
        msgPanel.setVisible(!userNameIsNull);
        msgPanel.setManaged(!userNameIsNull);
        clientsList.setVisible(!userNameIsNull);
        clientsList.setManaged(!userNameIsNull);
        logoutButton.setVisible(!userNameIsNull);
        logoutButton.setManaged(!userNameIsNull);
        userList.setVisible(!userNameIsNull);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsername(null);
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logoutButton.setVisible(false);
    }

    public void login() {

        if (usernameField.getText().isEmpty()) {
            showErrorAlert("Username/password cannot be empty");
            return;
        }

        if (socket == null || socket.isClosed()) {
            connect();
        }

        try {
            out.writeUTF("/login " + usernameField.getText() + " " + passwordField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try  {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread t = new Thread(() -> {
                try(FileOutputStream fileOutputStream = new FileOutputStream(filePath, true)) {
                    // Цикл авторизации
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/login_ok ")) {
                            setUsername(msg.split("\\s+")[1]);
                            byte[] data = new byte[64];
                            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
                                int len = 0;
                                while ((len = in.read(data)) != -1) {
                                    msgArea.appendText(new String(data, 0, len));
                                }
                            }
                            if (msgArea.getText().isEmpty()) {
                                msgArea.appendText("\n");
                            }
                            break;
                        }
                        if (msg.startsWith("/login_failed ")) {
                            String cause = msg.split("\\s+", 2)[1];
                            msgArea.appendText(cause + "\n");
                        }
                    }
                    // Цикл общения
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/")) {
                            if (msg.startsWith("/clients_list ")) {
                                String[] tokens = msg.split("\\s");
                                Platform.runLater(() -> {
                                    System.out.println(Thread.currentThread().getName());
                                    clientsList.getItems().clear();
                                    for (int i = 1; i < tokens.length; i++) {
                                        clientsList.getItems().add(tokens[i]);
                                    }
                                });
                            }
                            if (msg.equals("/logout_ok")) {
                                break;
                            }
                            continue;
                        }
                        String message = msg + "\n";
                        msgArea.appendText(message);
                        fileOutputStream.write(message.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    disconnect();
                }
            });
            t.start();
        } catch (IOException e) {
            showErrorAlert("Unable to connect to the server");
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            showErrorAlert("Unable to send message");
        }
    }

    private void disconnect() {
        setUsername(null);
        msgArea.clear();
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // Do nothing
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        out.writeUTF("/logout");
    }

    private void showErrorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.setTitle("March chat FX");
        alert.showAndWait();
    }
}
