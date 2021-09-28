package no.ntnu.edvardsen;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        Server s = new Server();
        s.run();
    }

    private void run() {
        try {
            ServerSocket welcomeSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            boolean mustRun = true;

            while (mustRun) {
                // Accept the next client connection
                Socket clientSocket = welcomeSocket.accept();

                BufferedReader bufReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String clientInput = bufReader.readLine();
                System.out.println("Client sent: " + clientInput);
                String[] parts = clientInput.split(" ");

                String response;
                if (parts.length == 3) {
                    response = parts[0] + " " + parts[1].toUpperCase() + " " + parts[2];
                } else {
                    response = "Error";
                }

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println(response);

                // Close connection to this particular client
                clientSocket.close();
            }

            // Close the listening socket, allow other services to listen on this TCP port
            welcomeSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
