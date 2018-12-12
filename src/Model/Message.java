package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Message {
	private String text;
	private InetAddress senderAddress;
	private int senderPort;
	private InetAddress receiverAddress;
	private int receiverPort;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public InetAddress getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(InetAddress senderAddress) {
		this.senderAddress = senderAddress;
	}

	public int getSenderPort() {
		return senderPort;
	}

	public void setSenderPort(int senderPort) {
		this.senderPort = senderPort;
	}

	public InetAddress getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(InetAddress receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public int getReceiverPort() {
		return receiverPort;
	}

	public void setReceiverPort(int receiverPort) {
		this.receiverPort = receiverPort;
	}

	public static Message fromSocket(Socket socket) throws IOException, ClassNotFoundException {
		Message message = new Message();
		message.setSenderAddress(socket.getInetAddress());
		message.setSenderPort(socket.getPort());
		message.setReceiverAddress(socket.getLocalAddress());
		message.setReceiverPort(socket.getLocalPort());
		message.setText((String)(new ObjectInputStream(socket.getInputStream()).readObject()));

		return message;
	}

	public static Message fromDatagramPacket(DatagramPacket packet, DatagramSocket socket) {
		Message message = new Message();
		message.setSenderPort(packet.getPort());
		message.setSenderAddress(packet.getAddress());
		message.setReceiverPort(socket.getLocalPort());
		message.setReceiverAddress(socket.getLocalAddress());
		byte buffer[] = packet.getData();
		message.setText(new String(buffer, 0, packet.getLength()));
		return message;
	}
}
