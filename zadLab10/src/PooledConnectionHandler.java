import java.io.*;
import java.net.*;
import java.util.*;
public class PooledConnectionHandler implements Runnable {
    protected Socket connection;
    protected static List pool = new LinkedList();
    public static PooledServer server;

    protected ObjectInputStream objectReader = null;
    protected ObjectOutputStream objectWriter = null;

    public PooledConnectionHandler() {
    }

    public void handleConnection() {

        try {
            objectReader = new ObjectInputStream(connection.getInputStream());
            objectWriter = new ObjectOutputStream(connection.getOutputStream());

            Object line = null;
            try {
                while((line = objectReader.readObject()) != null) {
                    server.sendToAllClients(line);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not find requested file on the server.");
        } catch (IOException e) {
            System.out.println("Error handling a client: " + e);
        }
    }

    public void sendLineToSocket(Object line) {
        if(connection != null && objectWriter != null) {
            try {
                objectWriter.writeObject(line);
                objectWriter.flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void processRequest(Socket requestToHandle) {
        synchronized (pool) {
            pool.add(pool.size(), requestToHandle);
            pool.notifyAll();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        objectReader.close();
        objectWriter.close();
    }

    public void run() {
        while (true) {
            synchronized (pool) {
                while (pool.isEmpty()) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                connection = (Socket) pool.remove(0);
            }
            handleConnection();
        }
    }
}