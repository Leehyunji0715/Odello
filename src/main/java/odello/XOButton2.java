package odello;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XOButton2 extends JButton implements ActionListener {
	ImageIcon X,O,Point;
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
		O = new ImageIcon(this.getClass().getResource("../Images/X.png"));
		X = new ImageIcon(this.getClass().getResource("../Images/O.png"));
		Point = new ImageIcon(this.getClass().getResource("../Images/point.png"));
		this.addActionListener(this);//button 누르면 발생하는 이벤트  
	}
	public void actionPerformed(ActionEvent e) {
		
		if(value>0) {
			actionPerformed(e);
			//만약 이미 돌이 놓여있다면, 돌을 못 놓는다.
			//그리고 현 유저의 차례일때, 그 유저의 돌 상하좌우 위치에 포인트 표시를 한다. 
		}
		else {
			value=user;//value의 값에 user의 돌값을 넣는다. 
			switch(value) {
			case 1:
				setIcon(X);
				break;
			case 2:	
				setIcon(O);
				break;
			}
			this.changeUser();
		}
	}

	public void changeUser() {
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
	
}
/*
class TicTacto extends JFrame{
	JPanel p = new JPanel(); 
	XOButton2 buttons[] = new XOButton2[49];//내가 만든 오델로  
	
	public TicTacto() {
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
		
		//point 표시할 곳 
		for(int i=0;i<49;i++) {
			if(buttons[i].value == this.user) {//????? 
				
			}
		}
		
		add(p);
		setVisible(true);
	}
}*/






