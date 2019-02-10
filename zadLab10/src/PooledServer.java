import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PooledServer {
    private ServerSocket serverSocket = null;
    public static final int MAX_CLIENTS_NUMBER = 5;
    private ArrayList<PooledConnectionHandler> handlers = new ArrayList<>();
    protected int listenPort;

    public PooledServer(int aListenPort) {
        listenPort = aListenPort;
    }

    public void sendToAllClients(Object line) {
        for(PooledConnectionHandler handler: handlers) {
            try {
                handler.sendLineToSocket(line);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void setUpHandlers() {
        for (int i = 0; i < MAX_CLIENTS_NUMBER; i++) {
            PooledConnectionHandler currentHandler = new PooledConnectionHandler();
            handlers.add(currentHandler);
            new Thread(currentHandler, "Handler " + i).start();
        }
    }

    public void acceptConnections() {
        try {
            serverSocket = new ServerSocket(listenPort, 5);
            Socket incomingConnection = null;
            while (true) {
                incomingConnection = serverSocket.accept();
                handleConnection(incomingConnection);
            }
        } catch (BindException e) {
            System.out.println("Unable to bind to port " + listenPort);
        } catch (IOException e) {
            System.out.println("Unable to instantiate a ServerSocket on port: " + listenPort);
        }
    }

    public void shutDown() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Cannot close socket.");
            System.out.println(e.getMessage());
        }
    }

    protected void handleConnection(Socket connectionToHandle) {
        PooledConnectionHandler.processRequest(connectionToHandle);
    }

    public static void main(String[] args) {

        PooledServer server = new PooledServer(3000);
        PooledConnectionHandler.server = server;

        server.setUpHandlers();

        server.acceptConnections();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutDown();
        }));

    }
}
