package odello;
import java.awt.GridLayout;
import javax.swing.*;

public class TicTactoe extends JFrame{
	JPanel p = new JPanel();
	XOButton buttons[] = new XOButton[49];
	public static void main(String args[]) {
		new TicTactoe();
	}
	public TicTactoe() {
		super("Othello");
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(7, 7));
		for(int i=0;i<49;i++) {
			buttons[i]=new XOButton();
			p.add(buttons[i]);
		}
		add(p);
		setVisible(true);
	}
}
