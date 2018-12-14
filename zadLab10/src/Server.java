import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private ServerSocket serverSocket = null;

    public static final int MAX_CLIENTS_NUMBER = 5;

    private LinkedList<ClientHandler> clients = new LinkedList<>();

    public Server() throws IOException {

        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket sender = null;

        try {
            sender = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        ObjectInputStream senderIn = new ObjectInputStream(sender.getInputStream());

        createClients();

        Object line;
        try {
            while ((line = senderIn.readObject()) != null) {
                sendToAllClients(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        senderIn.close();
        serverSocket.close();
    }

    public void sendToAllClients(Object line) {
        for(ClientHandler client: clients) {
            try {
                client.output.writeObject(line);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    private void createClients() {
        System.out.println("started");
        for(int i=0; i<MAX_CLIENTS_NUMBER; ++i) {
            clients.add(new ClientHandler(getSocket()));
        }
        System.out.println("clients created");
    }

    public ServerSocket getSocket() {
        return this.serverSocket;
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
