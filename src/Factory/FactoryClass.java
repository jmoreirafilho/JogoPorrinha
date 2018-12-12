package Factory;

import Model.TypeProtocol;
import Protocol.IClient;
import Protocol.IServer;

import java.io.IOException;

public abstract class FactoryClass {

	public abstract IClient clientProtocol(String serverAddress, int serverPort) throws IOException;
	public abstract IServer serverProtocol(int port) throws IOException;
	
	
	public static FactoryClass createFactory( final String tipo) throws Exception{
		if (tipo.equals(TypeProtocol.TCP)){
			return new FactoryTCP();
		}else if(tipo.equals(TypeProtocol.UDP)){
			return new FactoryUDP();
		}else{
			return null;
		}
	}
}
