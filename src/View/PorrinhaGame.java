package View;

import javax.swing.*;

public class PorrinhaGame extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JPanel jPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton apostarButton;
    private JButton colocarButton;
    private JLabel autorizeAposta;
    private JLabel resultGame;
    private JLabel inscrito;

    public PorrinhaGame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(jPanel);
        setLocationRelativeTo(null);
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getApostarButton() {
        return apostarButton;
    }

    public JButton getColocarButton() {
        return colocarButton;
    }

    public JLabel getAutorizeAposta() {
        return  autorizeAposta;
    }

    public JLabel getResultGame(){
        return  resultGame;
    }

    public JLabel getInscrito() {
        return inscrito;
    }
}
