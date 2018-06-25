package odello;
import java.awt.GridLayout;
import javax.swing.*;

public class TicTactoe extends JFrame{
	JPanel p = new JPanel(); 
	XOButton2 buttons[] = new XOButton2[49];//내가 만든 오델로  
	
	public static void main(String args[]) {
		new TicTactoe();
	}
	
	public TicTactoe() {
		super("Othello");
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(7, 7));
		ImageIcon Point = new ImageIcon(this.getClass().getResource("../Images/point.png"));//실제 가능한 곳에 포인트 표시 
		
		for(int i=0;i<49;i++) {
			buttons[i]=new XOButton2();//내가 만든 오델로  
			p.add(buttons[i]);
		}
		/*
		for(int i=0;i<49;i++) {
			if(buttons[i].value == buttons[i].user) {//????? 
				
			}
		}
		*/
		add(p);
		setVisible(true);
	}
}
