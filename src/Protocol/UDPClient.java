package Protocol;

import java.io.IOException;
import java.net.*;

public class UDPClient implements IClient {
    private static final int DEFAULT_BUFFER_LENGTH = 1024;
    private final DatagramSocket connection;
    private final int serverPort;
    private final String serverAddress;

    public UDPClient(String serverAddress, int serverPort) throws SocketException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        connection = new DatagramSocket();
    }

    @Override
    public String sendMessage(String message) throws IOException {
        connection.send(packMessage(message));
        return receiveMessage();
    }

    @Override
    public String receiveMessage() throws IOException {
        return unpackMessage(connection);
    }

    private DatagramPacket packMessage(String message) throws UnknownHostException {
        return new DatagramPacket(message.getBytes(),
                message.getBytes().length,
                InetAddress.getByName(serverAddress),
                serverPort);
    }

    private String unpackMessage(DatagramSocket connection) throws IOException {
        byte message[] = new byte[DEFAULT_BUFFER_LENGTH];
        connection.receive(new DatagramPacket(message,
                DEFAULT_BUFFER_LENGTH,
                InetAddress.getByName(serverAddress),
                serverPort));
        return new String(message);
    }
}
