import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // ή η διεύθυνση IP του server
        int port = 1234; // Ο αριθμός της θύρας που ακούει ο server

        try {
            Socket socket = new Socket(hostname, port); // Δημιουργία σύνδεσης με τον server

            // Δημιουργία output stream για την αποστολή μηνυμάτων στον server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            // Δημιουργία input stream για την λήψη απαντήσεων από τον server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Αποστολή μηνύματος στον server
            writer.println("Hello Server");

            // Λήψη απάντησης από τον server
            String response = reader.readLine();
            System.out.println("Server response: " + response);

            socket.close(); // Κλείσιμο της σύνδεσης
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
