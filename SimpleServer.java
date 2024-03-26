import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 1234; // Ορίστε τον αριθμό της θύρας που θέλετε να ακούσει ο server
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port); // Δημιουργία του ServerSocket
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Ακούστε για εισερχόμενες συνδέσεις
                System.out.println("New client connected");

                // Δημιουργήστε τα input και output streams για την επικοινωνία
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Διαβάστε ένα μήνυμα από τον client και απαντήστε
                String message = reader.readLine();
                System.out.println("Received from client: " + message);
                writer.println("Echo: " + message); // Στείλτε την απάντηση πίσω στον client

                socket.close(); // Κλείστε τη σύνδεση με τον client
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close(); // Σιγουρευτείτε ότι το ServerSocket κλείνει σωστά
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

