import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;

    public Server() throws IOException {

        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket one = null;

        try {
            one = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }

        Socket two = null;

        try {
            two = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        ObjectInputStream inOne = new ObjectInputStream(one.getInputStream());
        ObjectOutputStream outTwo = new ObjectOutputStream(two.getOutputStream());

        Object line;
        try {
            while ((line = inOne.readObject()) != null) {
                outTwo.writeObject(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        outTwo.close();
        inOne.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            new Server();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
