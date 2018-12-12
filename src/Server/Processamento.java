package Server;
import java.net.InetAddress;
import java.util.ArrayList;

import Factory.FactoryClass;
import Model.Message;
import Protocol.IServer;

public class Processamento extends Thread {

	private int port = 1234;
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Person> winners = new ArrayList<Person>();
	private Integer playersQty = 3;
	private Integer playersRealQty = 0;
	
	public Processamento() {
		Thread processamento = new Thread();
		processamento.start();
	}
	
	@Override
	public void run() {
		processaRequisicoes("TCP");
		processaRequisicoes("UDP");
	}

	private void processaRequisicoes(String protocol) {
		try {
			IServer server = FactoryClass.createFactory(protocol).serverProtocol(port);
			String responseText = null;

			Message message = null;
			
			while (true) {
				// Recebe a mensagem
				message = server.receiveMessage();
				System.out.println("Received from " + protocol + message.getSenderAddress() + ":" + message.getSenderPort() + " " + message.getText());
				
				if (message != null) { // Se tiver recebido alguma mensagem
					
					// Cria mensagem de resposta
					Message responseMessage = new Message();
					responseMessage.setSenderAddress(message.getReceiverAddress());
					responseMessage.setSenderPort(message.getReceiverPort());
					responseMessage.setReceiverAddress(message.getSenderAddress());
					responseMessage.setReceiverPort(message.getSenderPort());
	
					
					// Processa as ações
					String fullMessage[] = message.getText().split(";");
					
					if (fullMessage[0].equals("novo")) { // Cria usuario no grupo
						responseText = addNewPerson(message.getReceiverAddress(), fullMessage[1]);
					}
					
					if (fullMessage[0].equals("palitos")) { // Define a quantidade de palitos
						responseText = setToothpickQuantity(message.getReceiverAddress(), fullMessage[1]);
					}
					
					if (fullMessage[0].equals("palpite")) { // Define o palpite
						responseText = setHunch(message.getReceiverAddress(), fullMessage[1]);
					}
					
					if (fullMessage[0].equals("resultado")) { // Define o palpite
						responseText = getGameResult();
					}
					
					// Envia mensagem de resposta
					responseMessage.setText(responseText);
					server.sendMessage(responseMessage);
				}
				
				if (playersRealQty == playersQty) { // Todos ja jogaram, define o vencedor
					processaVencedor();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getGameResult() {
		String winnersName = null;
		for(Person winner : winners) {
			winnersName += winner.getUsername() +"; ";
		}
		
		return winnersName;
	}

	private void processaVencedor() {
		Integer toothpickTotalQty = 0;
		
		for(Person person: people) { // conta total de palitos
			toothpickTotalQty += person.getToothpickQty();
		}
		
		for(Person person: people) { // busca quem acertou
			if (person.getHunch() == toothpickTotalQty) {
				winners.add(person);
			}
		}
		
	}

	private String setHunch(InetAddress receiverAddress, String hunch) {
		boolean finded = false;
		for (Person person : people) {
			if (person.getAddress().equals(receiverAddress)) {
				finded = true;
				person.setHunch(Integer.parseInt(hunch));
			}
		}
		
		if (finded) {
			playersRealQty++;
			return "done";
		}
		
		return "person not found";
	}

	private String setToothpickQuantity(InetAddress receiverAddress, String toothpickQty) {
		boolean finded = false;
		for (Person person : people) {
			if (person.getAddress().equals(receiverAddress)) {
				finded = true;
				person.setToothpickQty(Integer.parseInt(toothpickQty));
			}
		}
		
		if (finded) {
			return "done";
		}
		
		return "person not found";
		
	}

	private String addNewPerson(InetAddress receiverAddress, String username) {
		for (Person person : people) {
			if (person.getUsername().equals(username) || person.getAddress().equals(receiverAddress)) {
				return "user already registered";
			}
		}
		
		Person person = new Person();
		person.setAddress(receiverAddress);
		person.setUsername(username);
		
		people.add(person);
		
		return "done";
	}
	
	

}
