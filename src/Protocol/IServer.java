package Protocol;

import Model.Message;

import java.io.IOException;

public interface IServer {
    void sendMessage(Message message) throws IOException;
    Message receiveMessage() throws IOException, ClassNotFoundException;
}
