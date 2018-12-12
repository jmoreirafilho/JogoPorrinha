package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Factory.FactoryClass;
import Model.TypeProtocol;
import Protocol.IClient;
import View.PorrinhaGame;

public class PorrinhaController {

    private static final String IP = "172.18.0.1";


    private PorrinhaGame porrinhaGame;
    private JTextField textField1;
    private JTextField textField2;
    private JButton apostarButton;
    private JButton colocarButton;
    private JLabel resultGame;
    private JLabel autorizeAposta;
    private JLabel inscrito;
    
    private String username = "Airton Filho";

    public PorrinhaController(){
        initComponents();
        initListener();
        initInscrito();
    }

    public void showWindow(){
        porrinhaGame.setVisible(true);
    }

    private void initComponents(){
        porrinhaGame = new PorrinhaGame();

        textField1 = porrinhaGame.getTextField1();
        textField2 = porrinhaGame.getTextField2();

        apostarButton = porrinhaGame.getApostarButton();
        colocarButton = porrinhaGame.getColocarButton();

        resultGame = porrinhaGame.getResultGame();
        autorizeAposta = porrinhaGame.getAutorizeAposta();

        inscrito = porrinhaGame.getInscrito();
    }

    private void initListener() {

        colocarButton.addActionListener(new ColocarBtnListener());
        apostarButton.addActionListener(new ApostarBtnListener());

    }

    public void initInscrito(){
        String result = send("novo;"+username);

        inscrito.setText(result);
    }

    private class ColocarBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String text = textField1.getText();

            String result = send("palitos;"+text);

            autorizeAposta.setText(result);
        }
    }

    private class ApostarBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String text = textField2.getText();

            String result = send("palpite;"+text);
            resultGame.setText(result);
        }
    }

    private String send(String text)
    {
        int port = 1024;

        try {
            FactoryClass client = FactoryClass.createFactory(TypeProtocol.TCP);
            IClient iClient = client.clientProtocol("127.0.0.1", port);
            return iClient.sendMessage(text);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


}
