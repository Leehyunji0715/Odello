package odello;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XOButton2 extends JButton implements ActionListener {
	ImageIcon X,O,Point;
	ImageIcon O_shadow, X_shadow;
	int value=0;
	/*
	 0:nothing
	 1:X
	 2:O
	 */
	public static int user=1;
	/*
	 1:X
	 2:O
	 */

	public XOButton2() {
		O = new ImageIcon(this.getClass().getResource("../Images/O.png"));
		O_shadow = new ImageIcon(this.getClass().getResource("../Images/O_shadow.png"));
		X = new ImageIcon(this.getClass().getResource("../Images/X.png"));
		O_shadow = new ImageIcon(this.getClass().getResource("../Images/X_shadow.png"));
		Point = new ImageIcon(this.getClass().getResource("../Images/point.png"));
		this.addActionListener(this);//button 누르면 발생하는 이벤트  
	}
	
	public void actionPerformed(ActionEvent e) {//okok
		//setPoints;
		if(value>0) {
			actionPerformed(e);//만약 이미 돌이 놓여있다면, 돌을 못 놓는다.//recursive이용하여 구현....
		}
		else {
			value=user;//value의 값에 user의 돌값을 넣는다. 
			switch(value) {
			case 0:
				setIcon(null);
			case 1:
				setIcon(X);
				break;
			case 2:	
				setIcon(O);
				break;
			}
			//clearPoints(TicTactoe.buttons);//Points들 표시들을 삭제한다. 
			this.changeUser();
		}
	}

	public void changeUser() {//okok
		if(user==1) {
			user=2;
		}
		else if(user ==2) {
			user=1;
		}
		else {
			System.out.printf("잘못된 유저 값입니다.\n");
			return;
		}
	}
	
	public void showPoints(XOButton2[] buttons) {
		
	}
	public void clearPoints(XOButton2[] buttons) {
		
	}

}






