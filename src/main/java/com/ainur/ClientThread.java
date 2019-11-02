package com.ainur;

import com.ainur.model.messages.Message;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

/**
 * Ожидает авторизацию клиента и, после получения, уничтожается
 */
public class ClientThread extends Thread {

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private MessageProcessor processor;
    private Gson gson = new Gson();

    public ClientThread(MessageProcessor processor, Socket socket) {
        this.socket = socket;
        this.processor = processor;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String jsonMessage = null;
            jsonMessage = reader.readLine();
            while (true) {
                Message message = gson.fromJson(jsonMessage, Message.class);
                processor.addMessage(message, socket);
                jsonMessage = reader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
