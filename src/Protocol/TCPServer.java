package Protocol;

import Model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer implements IServer {
    private final ServerSocket connection;
    private ArrayList<Socket> clientConnections = new ArrayList<>();

    public TCPServer(int port) throws IOException {
        connection = new ServerSocket(port);
        System.out.println("Started TCP server in port " + port);
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        Socket socket = clientConnections.stream()
                .filter(x -> x.getInetAddress() == message.getReceiverAddress()
                        && x.getPort() == message.getReceiverPort()).findFirst().get();
        ObjectOutputStream io = new ObjectOutputStream(socket.getOutputStream());
        io.writeObject(message.getText());
    }

    @Override
    public Message receiveMessage() throws IOException, ClassNotFoundException {
        Socket clientSocket = connection.accept();
        clientConnections.add(clientSocket);
        return Message.fromSocket(clientSocket);
    }
}
