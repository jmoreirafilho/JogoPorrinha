package Factory;

import Protocol.*;

import java.io.IOException;

public class FactoryTCP extends FactoryClass {
	@Override
	public IClient clientProtocol(String serverAddress, int serverPort) throws IOException {
		return new TCPClient(serverAddress, serverPort);
	}

	@Override
	public IServer serverProtocol(int port) throws IOException {
		return new TCPServer(port);
	}
}
