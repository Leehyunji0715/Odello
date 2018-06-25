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
	public JLayeredPane base = new JLayeredPane();
	public static XOButton2 buttons[] = new XOButton2[64];//내가 만든 오델로  
	
	public static void main(String args[]) {
		new TicTactoe();
	}
	
	public TicTactoe() {//오델로 판의 사이즈를 모두 정하고, 기본적인 설정 및 8*8사이즈 파넬 생성 
		super("Othello");
		setSize(800, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(8, 8));
		for(int i=0;i<64;i++) {
			buttons[i]=new XOButton2();//내가 만든 오델로  
			p.add(buttons[i]);
		}
		setPanel();//setPanel()함수 호출 
		add(p);//파넬에 추가한다. 
		setVisible(true);
	}

	void setPanel() { // 중앙에 흰돌2개, 검은 돌 2개 미리 둔다. 값도 미리 설정해두어 덮어 못쓰도록 한다.
		ImageIcon O = new ImageIcon(this.getClass().getResource("../Images/X.png"));
		ImageIcon X = new ImageIcon(this.getClass().getResource("../Images/O.png"));
		TicTactoe.buttons[28].setIcon(O);
		TicTactoe.buttons[28].value=2;
		TicTactoe.buttons[35].setIcon(O);
		TicTactoe.buttons[35].value=2;
		TicTactoe.buttons[27].setIcon(X);
		TicTactoe.buttons[27].value=1;
		TicTactoe.buttons[36].setIcon(X);
		TicTactoe.buttons[36].value=1;
	}

class PlayerSetting extends Panel implements MouseListener, MouseMotionListener{
	ImageIcon p1,p2,Point,p1_shadow,p2_shadow;//p1, p2의 말과 각각 셰도우 이미지 
	public JLabel labelshadow = new JLabel();// 쉐도우이미지 라벨
	public int play_x, play_y, turn=0;	// 돌이 노여질 좌표, 파란돌부터 시작, 승리선언을 위한 돌 개수

	public PlayerSetting() {
		// ** play1 의 말 과 쉐도우, 로고 파일 설정
	    p1 = new ImageIcon(this.getClass().getResource("../Images/O.png"));
	    p1_shadow = new ImageIcon(this.getClass().getResource("../Images/O_shadow.png"));

		// ** play2 의 말 과 쉐도우, 로고 파일 설정
	    p2 = new ImageIcon(this.getClass().getResource("../Images/X.png"));
	    p2_shadow = new ImageIcon(this.getClass().getResource("../Images/X_shadow.png"));
		
	    base.addMouseListener(this);	// 클릭 모션을 위한 설정
	    base.addMouseMotionListener(this);	// 무브모션을 위한 설정
	}
	
	
	public void xysetting()	// 좌표를 배열 크기에 맞게 자르는 함수
	{
		if(play_x < 40)		// 좌표를 배열크기(40)와 동일하게 자르기
			play_x = 0;
		else if(play_x > 520)
			play_x = 12;
		else
			play_x = (int)play_x/40;	// 소숫점 없애기
		
		if(play_y < 40)
			play_y = 0;
		else if(play_y > 520)
			play_y = 12;
		else
			play_y = (int)play_y/40;	
		/*
		 if(play_x < 100)		// 좌표를 배열크기(40)와 동일하게 자르기
			play_x = 0;
		else if(play_x > 900)
			play_x = 8;
		else
			play_x = (int)play_x/100;	// 소숫점 없애기
		
		if(play_y < 100)
			play_y = 0;
		else if(play_y > 900)
			play_y = 8;
		else
			play_y = (int)play_y/100;	
		 */
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		play_x = arg0.getX();	// 마우스가 있는 곳의 x좌표 얻어오기
		play_y = arg0.getY();	// 마우스가 있는 곳의 x좌표 얻어오기
		xysetting();
		shadow_Find(play_x, play_y);	// shadow_Find 함수 호출
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		play_x = arg0.getX();	
		play_y = arg0.getY();
		xysetting();//x,y의 위치 각각 정수로 정해버리기 
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
	private void shadow_Find(int play_x2, int play_y2) {
		//해당 좌표에 셰도우 이미지가 올 수 있는지 확인   
		
		
		
	}
	public void shadow_Stone(int x, int y, int turn)	// 해당 좌표에  쉐도우이미지를 생성 하는 함수.
	{			
		if(turn == 0)	// player1(파란돌)이면,
		{
		   	labelshadow.setIcon(p1_shadow);	// 파란쉐도우이미지 출력
		    labelshadow.setBounds(x*100,y*100,100,100);
		    base.add(labelshadow,0);
		    
		}
		else if(turn == 1)
		{
			labelshadow.setIcon(p2_shadow);
		    labelshadow.setBounds(x*100,y*100,100,100);
		    base.add(labelshadow,0);
		}
	}
	
	public void draw_Stone(int x, int y, int turn)	// 해당 좌표에  돌을 생성 하는 함수.
	{			
		if(turn == 0)	// player1(파란돌)이면,
		{
			JLabel label = new JLabel();
			label.setIcon(p1);	// 파란돌 생성
		    label.setBounds(x*100,y*100,100,100);
		    base.add(label,0);    	
		}
		else
		{
		    JLabel label = new JLabel();
		    label.setIcon(p2);
		    label.setBounds(x*100,y*100,100,100);
		    base.add(label,0);	
		}
	}
class Pan	// 오델로판
{
	ImageIcon backImageIcon;	// 배경 이미지 아이콘
	public int[][] othello_pan;	// 오델로판 배열 선언
	
	public Pan()
	{
		othello_pan = new int[13][13];	// 배열을 13*13으로 오델로판 생성
		for(int i=0; i<13; i++)
		{
			for(int j=0; j<13; j++)
				othello_pan[i][j] = 2;	// 0=play1, 1=play, 2=빈자리 -> 초기화를 위한 빈자리 선언
		}
		
		othello_pan[5][5] = 1;	//게임시작시 처음 갖고있는 돌 4개 생성		
		draw_Stone(5,5,1);		
		othello_pan[6][6] = 1;
		draw_Stone(6,6,1);				
		othello_pan[5][6] = 0;
		draw_Stone(6,5,0);				
		othello_pan[6][5] = 0;
		draw_Stone(5,6,0);

//		backImageIcon = new ImageIcon("./pan.gif");	// 오델로판 배경이미지				
//		imageLabel.setIcon(backImageIcon);
//		imageLabel.setBounds(0, 0, 520, 520);
//		base.add(imageLabel);
	}

	public int indata(int x, int y)	//해당 좌표에 매칭되는 배열값을 얻어오는 함수.
	{
		if(x<0 || x>12)
			return -1;
		if(y<0 || y>12)
			return -1;
		
		return othello_pan[y][x];
	}
	
	public void outdata(int x, int y, int data)	//해당 좌표에 배열값 대입.
	{
		if(x<0 || x>12)
			return ;
		if(y<0 || y>12)
			return ;
		
		othello_pan[y][x] = data;
	}
}
}
}



 