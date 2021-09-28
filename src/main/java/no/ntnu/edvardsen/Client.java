package no.ntnu.edvardsen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
            String commandToSend = "Get / HTTP/1.0\r\n\r\n";
            OutputStream out = socket.getOutputStream();
            out.write(commandToSend.getBytes());

            // Get HTTP response from the server
            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[10000];
            int bytesReceived;
            do {
                bytesReceived = in.read(buffer);
                String responsePart = new String(buffer);
                if (bytesReceived > 0) {
                    //System.out.println("Received " + bytesReceived + " bytes: ");
                    System.out.print(responsePart);
                }
            } while (bytesReceived > 0);

            // Data transfer


            // Closing the connection
            socket.close();

        } catch (IOException e) {
            System.out.println("Socket error: " + e.getMessage());
        }
    }
}
