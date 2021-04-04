package ru.geekbrains.march.chat.server;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    List<User> users;

    public InMemoryAuthenticationProvider() {
        this.users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("u" + i, "p" + i, "Nickname" + i));
        }
    }

    @Override
    public void init() {

    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u.getNickName();
            }
        }
        return null;
    }

    @Override
    public void changeNickname(String oldNickname, String newNickname) {
        for (User u : users) {
            if (u.getNickName().equals(oldNickname)) {
                u.setNickName(newNickname);
                return;
            }
        }
    }

    @Override
    public boolean isNickBusy(String nickname) {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
