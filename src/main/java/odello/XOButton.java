package odello;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XOButton extends JButton implements ActionListener {
	ImageIcon X,O,Point;
	byte value=0;
	/*
	 0:nothing
	 1:X
	 2:O
	 */
	
	public XOButton() {
		O = new ImageIcon(this.getClass().getResource("../Images/X.png"));
		X = new ImageIcon(this.getClass().getResource("../Images/O.png"));
		Point = new ImageIcon(this.getClass().getResource("../Images/point.png"));
		this.addActionListener(this);//button 누르면 발생하는 이벤트  
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}

	/*
	public void actionPerformed(ActionEvent e) {//Othello action 적기.... 
		value++;
		value%=3;
		switch(value) {
		case 0: 
			setIcon(null);
			break;
		case 1:
			setIcon(X);
			break;
		case 2:
			setIcon(O);
			break;
		}
	}
	*/

}
