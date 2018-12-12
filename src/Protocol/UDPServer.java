package Protocol;

import Model.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer implements IServer {
    private static final int DEFAULT_BUFFER_LENGTH = 1024;
    private final DatagramSocket connection;

    public UDPServer(int port) throws SocketException {
        connection = new DatagramSocket(port);
        System.out.println("Started UDP server in port " + port);
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        String text = message.getText();
        byte bytes[] = new byte[DEFAULT_BUFFER_LENGTH];
        System.arraycopy(text.getBytes(), 0, bytes, DEFAULT_BUFFER_LENGTH - text.length(), text.length());
        DatagramPacket packet = new DatagramPacket(
                bytes,
                DEFAULT_BUFFER_LENGTH,
                message.getReceiverAddress(),
                message.getReceiverPort());
        connection.send(packet);
    }

    @Override
    public Message receiveMessage() throws IOException {
        byte message[] = new byte[DEFAULT_BUFFER_LENGTH];
        DatagramPacket packet = new DatagramPacket(message, DEFAULT_BUFFER_LENGTH);
        connection.receive(packet);
        return Message.fromDatagramPacket(packet, connection);
    }
}
