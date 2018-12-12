package Factory;

import Protocol.*;

import java.io.IOException;

public class FactoryUDP extends FactoryClass {

	@Override
	public IClient clientProtocol(String serverAddress, int serverPort) throws IOException {
		return new UDPClient(serverAddress, serverPort);
	}

	@Override
	public IServer serverProtocol(int port) throws IOException {
		return new UDPServer(port);
	}
}
