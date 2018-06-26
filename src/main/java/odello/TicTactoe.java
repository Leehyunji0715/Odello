package odello;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import org.junit.experimental.theories.Theories;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class TicTactoe extends JFrame{
	JPanel p = new JPanel(); 
	public static XOButton2 buttons[][] = new XOButton2[8][8];//내가 만든 오델로  
	//public static XOButton2 buttons[] = new XOButton2[64];//내가 만든 오델로  
	
	public static void main(String args[]) {
		new TicTactoe();
	}
	
	public TicTactoe() {//오델로 판의 사이즈를 모두 정하고, 기본적인 설정 및 8*8사이즈 파넬 생성 
		super("Othello");
		setSize(800, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(8, 8));

		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				buttons[i][j]=new XOButton2();//내가 만든 오델로  
				p.add(buttons[i][j]);
			}
		}

		/*
		for(int i=0;i<64;i++) {
			buttons[i]=new XOButton2();//내가 만든 오델로  
			p.add(buttons[i]);
		}
		*/
		setPanel();//setPanel()함수 호출 
		add(p);//파넬에 추가한다. 
		setVisible(true);
	}

	void setPanel() { // 중앙에 흰돌2개, 검은 돌 2개 미리 둔다. 값도 미리 설정해두어 덮어 못쓰도록 한다.
		ImageIcon O = new ImageIcon(this.getClass().getResource("../Images/X.png"));
		ImageIcon X = new ImageIcon(this.getClass().getResource("../Images/O.png"));
		
		TicTactoe.buttons[3][3].setIcon(O);
		TicTactoe.buttons[3][3].value=2;
		TicTactoe.buttons[4][4].setIcon(O);
		TicTactoe.buttons[4][4].value=2;
		TicTactoe.buttons[3][4].setIcon(X);
		TicTactoe.buttons[3][4].value=1;
		TicTactoe.buttons[4][3].setIcon(X);
		TicTactoe.buttons[4][3].value=1;
		
		/*
		TicTactoe.buttons[28].setIcon(O);
		TicTactoe.buttons[28].value=2;
		TicTactoe.buttons[35].setIcon(O);
		TicTactoe.buttons[35].value=2;
		TicTactoe.buttons[27].setIcon(X);
		TicTactoe.buttons[27].value=1;
		TicTactoe.buttons[36].setIcon(X);
		TicTactoe.buttons[36].value=1;
		*/
	}
}



 