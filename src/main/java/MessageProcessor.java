import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class MessageProcessor extends Thread {
    private Socket socket;

    BufferedReader reader;
    BufferedWriter writer;

    Gson gson;

    public MessageProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            gson = new Gson();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String message = null;
            message = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                reader.close();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
