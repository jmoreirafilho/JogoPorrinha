package Protocol;

import java.io.IOException;

public interface IClient {
    String sendMessage(String message) throws IOException, ClassNotFoundException;
    String receiveMessage() throws IOException, ClassNotFoundException;
}
