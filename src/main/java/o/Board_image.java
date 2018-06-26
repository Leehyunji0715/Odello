package o;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Board_image implements MouseMotionListener, MouseListener{
	ImageIcon backImageIcon;
	public static int[][] othello_pan;
	public JLabel imageLabel = new JLabel();	// 오델로판이미지 라벨
	public static JLayeredPane base;
	public int p1, p2;
	public int user = 0;
	PutStone drawer = new PutStone();
	
	public Board_image () {
		// ** 전체 창을 생성
		JFrame window = new JFrame();
		window.setTitle("Othello");
		window.setSize(800,800);  

		// ** 게임 베이스 생성
		base = new JLayeredPane();

		// ** 게임 베이스를 창에 띄운다
		window.setContentPane(base);	
		window.setVisible(true);

		othello_pan = new int[8][8];	// 배열을 8*8으로 오델로판 생성
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
				othello_pan[i][j] = 0;	// 0=null, 1=play1, 2=play2 -> 초기화를 위한 빈자리 선언
		}


		backImageIcon = new ImageIcon(this.getClass().getResource("../images/board.png"));	// 오델로판 배경이미지				
		imageLabel.setIcon(backImageIcon);
		imageLabel.setBounds(0, 0, 800, 800);
		base.add(imageLabel);

//		Board_image.othello_pan[3][3] = 1;	//게임시작시 처음 갖고있는 돌 4개 생성		
//		drawer.putStone(3, 3, 1);		
//		Board_image.othello_pan[4][4] = 1;
//		drawer.putStone(4,4,1);				
//		Board_image.othello_pan[3][4] = 2;
//		drawer.putStone(3,4,2);				
//		Board_image.othello_pan[4][3] = 2;
//		drawer.putStone(4,3,2);
		Board_image.base.addMouseListener(this);	// 클릭 모션을 위한 설정
		Board_image.base.addMouseMotionListener(this);	// 무브모션을 위한 설정
	}

	public void xysetting() {	// 좌표를 배열 크기에 맞게 자르는 함수

		if(p1 < 80)		// 좌표를 배열크기(80)와 동일하게 자르기
			p1 = 0;
		else if(p1 > 800)
			p1 = 7;
		else
			p1 = (int)p1/80;	// 소숫점 없애기

		if(p2 < 80)
			p2 = 0;
		else if(p2 > 800)
			p2 = 7;
		else
			p2 = (int)p2/80;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		p1 = e.getX();
		p2 = e.getY();
		xysetting();	
		System.out.printf("x:%d y:%d\n",p1,p2);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		p1 = e.getX();	// x좌표 얻어오기
		p2 = e.getY();	// y좌표 얻어오기
		xysetting();
	}
	
}
