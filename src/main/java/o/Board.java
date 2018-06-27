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
	public static int pos_x,pos_y;
	public JPanel p = new JPanel();
	JFrame window = new JFrame("othello");
	public PlayerSetting ps = new PlayerSetting();//player에 대한 각각 이미지 정보가 담겨있다.
	public static Buttons buttons[][] = new Buttons[8][8];
	GameRuleSetting grs = new GameRuleSetting();

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
				buttons[i][j].value = 0;
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
			/*x,y 위치를 찾는다 */
			findXYPos(e);
			System.out.printf("x:%d y:%d\n",pos_x,pos_y);//getSource()사랑해유ㅠㅠㅠ 엉엉 
			
			/*돌을 놓는다 */
			if(value>0) {
				//grs.checkStoneEWSN();//할 필요 없
				return;
			}
			else {
				grs.checkChangeStone();
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
			/*다음 유저의 차례로 넘긴다.*/
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
	public class GameRuleSetting{
		
		public void checkChangeStone() {

			/*상 방향으로 검사 및 체인지 */
			if(pos_x-1>=0) {
			if((ps.user==1&&buttons[pos_x-1][pos_y].value==2)||ps.user==2&&buttons[pos_x-1][pos_y].value==1) {
				for(int ix=pos_x-1; ix>=0; ix--)	// 상 방향으로 돌 조사
				{
					if(ix==-1)	break;
					if(Board.buttons[pos_x-1][pos_y].value==0)
						break;
					if((Board.buttons[pos_x-1][pos_y].value==2 && Board.buttons[ix][pos_y].value==1 || (Board.buttons[pos_x-1][pos_y].value==1 && Board.buttons[ix][pos_y].value==2)))	
					{
						for(int jx=ix; jx<=pos_x; jx++)
						{	
							if(ps.user==1) {
								Board.buttons[jx][pos_y].setIcon(ps.p1);
								Board.buttons[jx][pos_y].value = 1;
							}
							else if(ps.user==2) {
								Board.buttons[jx][pos_y].setIcon(ps.p2);
								Board.buttons[jx][pos_y].value = 2;
							}
						}
						break;
						}
					}
				}
			}
			/*왼쪽 위 대각선 */
			if(pos_x-1>=0&&pos_y-1>=0) {
				if ((ps.user == 1 && buttons[pos_x-1][pos_y-1].value == 2) || (ps.user == 2 && buttons[pos_x-1][pos_y-1].value == 1))	// 상 왼쪽 방향
				{
					for(int ix=pos_x-1,iy=pos_y-1; ix>=0 || iy>=0; ix-- , iy--)	// 상 왼쪽 방향으로 돌 조사
					{
						if(ix==-1 || iy==-1)	break;
						if(buttons[ix][iy].value == 0)
							break;
						if((buttons[pos_x-1][pos_y-1].value==2 && buttons[ix][iy].value==1) || (buttons[pos_x-1][pos_y-1].value==1 && buttons[ix][iy].value==2))	// 반대속성의 돌이 나오면
						{
							for(int jx=ix,jy=iy ; jx<=pos_x; jx++, jy++)
							{	
								if(ps.user==1) {
									Board.buttons[jx][jy].setIcon(ps.p1);
									Board.buttons[jx][jy].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[jx][jy].setIcon(ps.p2);
									Board.buttons[jx][jy].value = 2;
								}
							}
							break;
						}
					}
				}
			}
			/*오른쪽 위 대각선 */
			 if(pos_x-1>=0&&pos_y+1<8){
				 
				 if ((ps.user == 1 && buttons[pos_x-1][pos_y+1].value==2) || (ps.user == 2 && buttons[pos_x-1][pos_y+1].value==1))	// 상 오른쪽 방향
					{
						for(int iy=pos_y+1,ix=pos_x-1; iy<8 || ix>=0; iy++ , ix--)
						{
							if(iy==8||ix==-1)	break;
							if(buttons[ix][iy].value==0)
								break;
							if((buttons[pos_x-1][pos_y+1].value==2 && buttons[ix][iy].value==1) || (buttons[pos_x-1][pos_y+1].value==1 && buttons[ix][iy].value==2))	
							{
								for(int jy=iy,jx=ix ; jy>=pos_y; jy--, jx++)
								{	
									if(ps.user==1) {
										Board.buttons[jx][jy].setIcon(ps.p1);
										Board.buttons[jx][jy].value = 1;
									}
									else if(ps.user==2) {
										Board.buttons[jx][jy].setIcon(ps.p2);
										Board.buttons[jx][jy].value = 2;
									}
								}
								break;
							}
						}
					}
			 }

			/*왼쪽 */
			if(pos_y-1>=0) {
				if ((ps.user == 1 && buttons[pos_x][pos_y-1].value==2) || (ps.user == 2 && buttons[pos_x][pos_y-1].value==1))	// 중간 왼쪽 방향
				{
					for(int iy=pos_y-1; iy>=0; iy--)	// 왼쪽 방향으로 돌 조사
					{
						if(iy==-1)	break;
						if(buttons[pos_x][iy].value==0)
							break;		// 돌이 발견 되지 않으면 스톱
						if((buttons[pos_x][pos_y-1].value==2 && buttons[pos_x][iy].value==1) || (buttons[pos_x][pos_y-1].value==1 && buttons[pos_x][iy].value==2))	// 반대속성의 돌이 나오면
						{
							for(int jy=iy; jy<=pos_y; jy++)	// 발생지점 -> 해당 좌표까지 x축의 반대속성의 돌을 해당돌로 변경
							{	
								if(ps.user==1) {
									Board.buttons[pos_x][jy].setIcon(ps.p1);
									Board.buttons[pos_x][jy].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[pos_x][jy].setIcon(ps.p2);
									Board.buttons[pos_x][jy].value = 2;
								}
							}
							break;
						}
					}
				}
			}	
			/*오른쪽 */
			if(pos_y+1<8) {
				if ((ps.user == 1 && buttons[pos_x][pos_y+1].value==2) || (ps.user == 2 && buttons[pos_x][pos_y+1].value==1))	// 중간 오른쪽 방향
				{
					for(int iy=pos_y+1; iy<=7; iy++)
					{
						if(iy==0)	break;
						if(buttons[pos_x][iy].value==0)
							break;	
						if((buttons[pos_x][pos_y+1].value==2 && buttons[pos_x][iy].value==1) || (buttons[pos_x][pos_y+1].value==1 && buttons[pos_x][iy].value==2))	
						{
							for(int jy=iy; jy>=pos_y; jy--)	
							{	
								if(ps.user==1) {
									Board.buttons[pos_x][jy].setIcon(ps.p1);
									Board.buttons[pos_x][jy].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[pos_x][jy].setIcon(ps.p2);
									Board.buttons[pos_x][jy].value = 2;
								}	
							}
							break;
						}
					}
				}
			}
			/*아래 왼쪽 대각선 */
			if(pos_x+1<8 && pos_y-1>=0) {
				if ((ps.user == 1 && buttons[pos_x+1][pos_y-1].value==2) || (ps.user == 2 && buttons[pos_x+1][pos_y-1].value==1))	// 하 왼쪽 방향
				{
					for(int iy=pos_y-1,ix=pos_x+1; iy>=0 || ix<=7; ix++ , iy--)
					{
						if(ix==8||iy==-1)	break;
						if(buttons[ix][iy].value==0)
							break;
						if((buttons[pos_x+1][pos_y-1].value==2 && buttons[ix][iy].value==1) || (buttons[pos_x+1][pos_y-1].value==1 && buttons[ix][iy].value==2))
						{
							for(int jy=iy,jx=ix ; jy<=pos_y; jy++, jx--)
							{	
								if(ps.user==1) {
									Board.buttons[jx][jy].setIcon(ps.p1);
									Board.buttons[jx][jy].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[jx][jy].setIcon(ps.p2);
									Board.buttons[jx][jy].value = 2;
								}
							}
							break;
						}
					}
				}
			}
			/*아래 */
			if(pos_x+1<8) {
				if ((ps.user == 1 && buttons[pos_x+1][pos_y].value==2) || (ps.user == 2 && buttons[pos_x+1][pos_y].value==1))	// 하 가운데 방향
				{
					for(int ix=pos_x+1; ix<=7; ix++)
					{
						if(ix==8)	break;
						if(buttons[ix][pos_y].value==0)
							break;		
						if((buttons[pos_x+1][pos_y].value==2 && buttons[ix][pos_y].value==1) || (buttons[pos_x+1][pos_y].value==1 && buttons[ix][pos_y].value==2))	
						{
							for(int jx=ix; jx>=pos_x; jx--)	
							{	
								if(ps.user==1) {
									Board.buttons[jx][pos_y].setIcon(ps.p1);
									Board.buttons[jx][pos_y].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[jx][pos_y].setIcon(ps.p2);
									Board.buttons[jx][pos_y].value = 2;
								}
							}
							break;
						}
					}
				}
			}
			/*아래 오른쪽 대각선 */
			if(pos_x+1<8 && pos_y+1<8) {
				if ((ps.user == 1 && buttons[pos_x+1][pos_y+1].value==2) || (ps.user == 2 && buttons[pos_x+1][pos_y+1].value==1))	// 하 오른쪽 방향
				{
					for(int iy=pos_y+1,ix=pos_x+1; iy<=7 || ix<=7; ix++ , iy++)
					{
						if(ix==8||iy==8)		break;
						if(buttons[ix][iy].value==0)
							break;
						if((buttons[pos_x+1][pos_y+1].value==2 && buttons[ix][iy].value==1) || (buttons[pos_x+1][pos_y+1].value==1 && buttons[ix][iy].value==2))
						{
							for(int jy=iy,jx=ix ; jy>=pos_y; jx--, jy--)
							{	
								if(ps.user==1) {
									Board.buttons[jx][jy].setIcon(ps.p1);
									Board.buttons[jx][jy].value = 1;
								}
								else if(ps.user==2) {
									Board.buttons[jx][jy].setIcon(ps.p2);
									Board.buttons[jx][jy].value = 2;
								}
							}
							break;
						}
					}
				}
			}
		}
	}
	
}
