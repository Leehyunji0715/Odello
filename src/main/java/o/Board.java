package o;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Board extends JFrame{
	int pos_x,pos_y;
	public JPanel p = new JPanel();
	JFrame window = new JFrame("othello");
	PlayerSetting ps = new PlayerSetting();//player에 대한 각각 이미지 정보가 담겨있다.
	public JLayeredPane base = new JLayeredPane();
	public Buttons buttons[][] = new Buttons[8][8];

	Board(){
		window.setTitle("O~thello");
		window.setSize(800, 800);
		window.setResizable(false);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(8, 8));
		makeFrame();
		settingBase();
		window.add(p);
		window.setVisible(true);
	}
	
	public void makeFrame() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				buttons[i][j] = new Buttons();
				p.add(buttons[i][j]);
			}
		}
	}
	
	public void settingBase() {
		buttons[3][3].setIcon(ps.p1);
		buttons[3][3].value = 1;
		buttons[4][4].setIcon(ps.p1);
		buttons[4][4].value = 1;
		buttons[3][4].setIcon(ps.p2);
		buttons[3][4].value = 2;
		buttons[4][3].setIcon(ps.p2);
		buttons[4][3].value = 2;
	}
	

	class Buttons extends JButton implements ActionListener{
		int value = 0;
		/*
		 * 0:NULL
		 * 1:pos_x
		 * 2:pos_y
		 */
		Buttons(){
			setIcon(null);//d일단 생성시 아무런 아이콘이 뜨지 않도록 한다.  
			this.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			findXYPos(e);
			System.out.printf("x:%d y:%d\n",pos_x,pos_y);//getSource()사랑해유ㅠㅠㅠ 엉엉 
			if(value>0) {
				return;
			}
			else {
				value = ps.user;
				switch(value) {
				case 1:
					setIcon(ps.p1);
					break;
				case 2:	
					setIcon(ps.p2);
					break;
				}
			}
			ps.changeUser();
		}
		
		public void findXYPos(ActionEvent e) {
			JButton bx = (JButton) e.getSource();
			
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(buttons[i][j]==bx) {
						pos_x = i;
						pos_y = j;
					}
				}
			}
		}
		
	}
}
