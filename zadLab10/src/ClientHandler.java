import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler {

    private ServerSocket socket;
    private Client receiver;
    public Socket out = null;
    ObjectOutputStream output;
    public ClientHandler(ServerSocket s){

        socket = s;

        Thread tread = new Thread() {
            public void run() {
                receiver = new Client();
                try {
                    out = socket.accept();
                } catch (
                        IOException e) {
                    System.out.println("Accept failed: 6666");
                    System.exit(-1);
                }
                try {
                    output = new ObjectOutputStream(out.getOutputStream());
                } catch (
                        IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        tread.start();

    }
}
