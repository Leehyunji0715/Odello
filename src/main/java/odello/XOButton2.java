package odello;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class XOButton2 extends JButton implements ActionListener, MouseListener, MouseMotionListener {
	ImageIcon X,O,Point;
	ImageIcon O_shadow, X_shadow;
	//public JLayeredPane base = new JLayeredPane();
	public int pos_x,pos_y;//x,y의 위치를 각 나타냄 
	
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
		//setPoints();
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
			//clearPoints(TicTactoe.buttons);?? 
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
	}
	
	public void checkEWSN() {
		
	}
	
	public void showPoints(XOButton2[] buttons) {
		
	}
	public void clearPoints(XOButton2[] buttons) {
		
	}
	/*마우스 위치의 값을 불러오기~~*/
	public void xysetting() {
		if(pos_x < 100)		// 좌표를 배열크기(40)와 동일하게 자르기
			pos_x = 0;
		else if(pos_x > 900)
			pos_x = 8;
		else
			pos_x = (int)pos_x/100;	// 소숫점 없애기
		
		if(pos_y < 100)
			pos_y = 0;
		else if(pos_y > 900)
			pos_y = 8;
		else
			pos_y = (int)pos_y/100;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {//MouseMotionListener
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		pos_x = arg0.getX();	// 마우스가 있는 곳의 x좌표 얻어오기
		pos_y = arg0.getY();	// 마우스가 있는 곳의 y좌표 얻어오기
		xysetting();
		//셰도우값...
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		pos_x = arg0.getX();	// 마우스가 있는 곳의 x좌표 얻어오기
		pos_y = arg0.getY();	// 마우스가 있는 곳의 y좌표 얻어오기
		xysetting();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {	
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}





