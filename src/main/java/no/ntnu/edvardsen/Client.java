package no.ntnu.edvardsen;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }

    private void run() {
        System.out.println("Client started...");

        try {
            // Establish connection to the remote server
            Socket socket = new Socket("ntnu.no", 80);
            System.out.println("Successfully connected");

            // Send HTTP request to server
            String commandToSend = "Get / HTTP/1.0";
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(commandToSend);
            writer.println("");
            //out.write(commandToSend.getBytes());

            // Get HTTP response from the server
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String oneResponseLine;
            do {
                oneResponseLine = reader.readLine();
                if (oneResponseLine != null) {
                    System.out.println(oneResponseLine);
                }
            } while (oneResponseLine != null);

            // Data transfer


            // Closing the connection
            socket.close();

        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
        }
    }
}
