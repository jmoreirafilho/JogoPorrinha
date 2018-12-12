package Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient implements IClient {
    private final Socket connection;

    public TCPClient(String serverAddress, int serverPort) throws IOException {
        connection = new Socket(serverAddress, serverPort);
    }
    @Override
    public String sendMessage(String message) throws IOException, ClassNotFoundException {
        ObjectOutputStream io = new ObjectOutputStream(connection.getOutputStream());
        io.writeObject(message);
        return receiveMessage();
    }

    @Override
    public String receiveMessage() throws IOException, ClassNotFoundException {
        ObjectInputStream io = new ObjectInputStream(connection.getInputStream());
        return (String)io.readObject();
    }
}
