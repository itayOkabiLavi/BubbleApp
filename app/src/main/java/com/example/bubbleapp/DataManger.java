package com.example.bubbleapp;

import java.util.List;

interface DataManager {

    public String login(String name, String password);

    public String register(String name, String nickname, String password) ;

    public List<ChatInfo> getContacts(String token);

    public boolean sendMessage(String token, String destination, String textMessage);

    public void getMessage();
}
