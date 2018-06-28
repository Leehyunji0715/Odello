package o;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class Board extends JFrame{
	public static int pos_x,pos_y;
	public JPanel p = new JPanel();
	JFrame window = new JFrame("othello");
	public PlayerSetting ps = new PlayerSetting();//player에 대한 각각 이미지 정보가 담겨있다.
	public static Buttons buttons[][] = new Buttons[8][8];
	GameRuleSetting grs = new GameRuleSetting();
	public static int p1_score=2, p2_score=2;
	ScoreBoard sb = new ScoreBoard();

	Board(){
		window.setTitle("O~thello");
		window.setSize(700, 700);
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
		
		buttons[2][3].setIcon(ps.point);
		buttons[2][3].value = 3;
		buttons[3][2].setIcon(ps.point);
		buttons[3][2].value = 3;
		
		buttons[5][4].setIcon(ps.point);
		buttons[5][4].value = 3;
		buttons[4][5].setIcon(ps.point);
		buttons[4][5].value = 3;
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
			boolean state = false;
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if((buttons[i][j].value==3)) {
						state = true;
						break;
					}
				}
			}
			if(state==false) {
				ps.changeUser();
				return;
			}
			
			/*x,y 위치를 찾는다 */
			findXYPos(e);
			System.out.printf("x:%d y:%d\n",pos_x,pos_y);//getSource()사랑해유ㅠㅠㅠ 엉엉 
			
			/*돌을 놓는다 */
			if(value!=3) {//Point되어있는 부분만 입력이 가능하다!! 
				countEach(e);
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
			countEach(e);
			/*다음 유저의 차례로 넘긴다.*/
			ps.changeUser();
			grs.setShadow_my();//reset + set stones
			if(p1_score + p2_score == 64) {
				if(p1_score>p2_score) {
					System.out.println("******게임 끝******");
					System.out.println("노랑이 승!");
				}
				else if(p1_score<p2_score) {
					System.out.println("******게임 끝******");
					System.out.println("검정이 승!");
					}
				else if(p1_score==p2_score) {
					System.out.println("******게임 끝******");
					System.out.println("무승부!");
				}
			}
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
		public void countEach(ActionEvent e) {
			int p1=0, p2=0;
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(buttons[i][j].value==1) {
						p1++;
					}
					else if(buttons[i][j].value==2) {
						p2++;
					}
				}
			}
			p1_score = p1;
			p2_score = p2;
			sb.changeNum();
			System.out.printf("x개수: %d,  y개수: %d \n",p1_score,p2_score);
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

		public void setShadow_my() {
			//기존의 포인트들을 모두 삭제하고....
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(buttons[i][j].value==3) {
						buttons[i][j].setIcon(null);
						buttons[i][j].value = 0;
					}
				}
			}
			//shadow가 가능한 곳 찾아보기 모두 찾아본다!! 
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(buttons[i][j].value>0) {//만약 돌이 놓여져 있다 --> 2가지 경우..
						
						if(ps.user==2 && buttons[i][j].value==1) {//검은 돌 차례일 때, 흰돌을 마주친다면??
							
							/*상 */
							if(i-1>=0) {
								for(int ix = i-1; ix>=0 ;ix--) {
									if(ix == -1)	break;
									if(buttons[i-1][j].value == 2) {//검정 돌이여야 함!
										for(int jx =i+1; jx<=7;jx++) {//현 위치서 반대 방향으로 빈공간 찾기 
											if(jx==8)	break;
											if(buttons[jx][j].value == 3)	break;
											if(buttons[jx][j].value==0) {
												buttons[jx][j].setIcon(ps.point);
												buttons[jx][j].value = 3;
												break;
											}
										}
									}
								}
							}
							/*상 왼쪽 */
							if(i-1>=0 && j-1>=0) {
								for(int ix = i-1,iy=j-1;ix>=0 && iy>=0;ix--,iy--) {
									if(ix == -1 || iy == -1)	break;
									if(buttons[i-1][j-1].value==2) {//일단 검정돌 찾음 하나 
										for(int jx=i+1,jy=j+1;jx<=7&&jy<=7;jx++,jy++) {//반대 방향 빈공간 찾기 
											if(jx==8 || jy==8)	break;
											if(buttons[jx][jy].value==3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*상 오른쪽 */ 
							if(i-1>=0 && j+1<=7) {
								for(int ix = i-1, iy = j+1; ix>=0&&iy<=7;ix--,iy++) {
									if(ix==-1 || iy==8)	break;
									if(buttons[i-1][j+1].value==2) {
										for(int jx=i+1,jy=j-1;jx<=7&&jy>=0;jx++,jy--) {
											if(jx==8 || jy ==-1)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value=3;
												break;//필수!
											}
										}
									}
								}
							}
							/*왼쪽 */
							if(j-1>=0) {
								for(int iy=j-1;iy>=0;iy--) {
									if(iy==-1)	break;
									if(buttons[i][j-1].value == 2) {
										for(int jy = j+1;jy<=7;jy++) {
											if(jy==8)break;
											if(buttons[j][jy].value == 3)	break;
											if(buttons[i][jy].value==0) {
												buttons[i][jy].setIcon(ps.point);
												buttons[i][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*오른쪽 */
							if(j+1<=7) {
								for(int iy = j+1;iy<=7;iy++) {
									if(iy==8)	break;
									if(buttons[i][j+1].value == 2) {
										for(int jy = j-1;jy>=0;jy--) {
											if(jy==-1)	break;
											if(buttons[i][jy].value == 3)	break;
											if(buttons[i][jy].value==0) {
												buttons[i][jy].setIcon(ps.point);
												buttons[i][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 왼쪽*/
							if(i+1<=7 && j-1>=0) {
								for(int ix = i+1,iy=j-1;ix<=7&&iy>=0;ix++,iy--) {
									if(ix==8||iy==-1)	break;
									if(buttons[i+1][j-1].value == 2) {
										for(int jx = i-1,jy = j+1;jx>=0&&jy<=7;jx--,jy++) {
											if(jx==-1 || jy==8)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 */
							if(i+1<=7) {
								for(int ix = i+1;ix<=7;ix++) {
									if(ix==8)	break;
									if(buttons[i+1][j].value == 2) {
										for(int jx = i-1;jx>=0;jx--) {
											if(jx == -1)		break;
											if(buttons[jx][j].value == 3)	break;
											if(buttons[jx][j].value==0) {
												buttons[jx][j].setIcon(ps.point);
												buttons[jx][j].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 오른쪽 */
							if(i+1<=7 && j+1<=7) {
								for(int ix = i+1,iy = j+1;ix<=7&&iy<=7;ix++,iy++) {
									if(ix == 8 || iy==8)		break;
									if(buttons[i+1][j+1].value == 2) {
										for(int jx = i-1,jy = j-1;jy>=0&&jx>=0;jx--,jy--) {
											if(jx==-1 || jy == -1)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value == 0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
						}// 검은 돌일때 포인트 지정...
						
						else if(ps.user==1 && buttons[i][j].value==2) {// 흰 돌 차례일 때, 검돌을 마주친다면??
							//싱하좌우 대각선 모두 검사하기~
							/*상 */
							if(i-1>=0) {
								for(int ix = i-1; ix>=0 ;ix--) {
									if(ix == -1)	break;
									if(buttons[i-1][j].value == 1) {//흰돌을 마주침 
										for(int jx =i+1; jx<=7;jx++) {//현 위치서 반대 방향으로 빈공간 찾기 
											if(jx==8)	break;
											if(buttons[jx][j].value == 3)	break;
											if(buttons[jx][j].value==0) {
												buttons[jx][j].setIcon(ps.point);
												buttons[jx][j].value = 3;
												break;
											}
										}
									}
								}
							}
							/*상 왼쪽 */
							if(i-1>=0 && j-1>=0) {
								for(int ix = i-1,iy=j-1;ix>=0 && iy>=0;ix--,iy--) {
									if(ix == -1 || iy == -1)	break;
									if(buttons[i-1][j-1].value==1) {//일단 검정돌 찾음 하나 
										for(int jx=i+1,jy=j+1;jx<=7&&jy<=7;jx++,jy++) {//반대 방향 빈공간 찾기 
											if(jx==8 || jy==8)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*상 오른쪽 */ 
							if(i-1>=0 && j+1<=7) {
								for(int ix = i-1, iy = j+1; ix>=0&&iy<=7;ix--,iy++) {
									if(ix==-1 || iy==8)	break;
									if(buttons[i-1][j+1].value==1) {
										for(int jx=i+1,jy=j-1;jx<=7&&jy>=0;jx++,jy--) {
											if(jx==8 || jy ==-1)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value=3;
												break;//필수!
											}
										}
									}
								}
							}
							/*왼쪽 */
							if(j-1>=0) {
								for(int iy=j-1;iy>=0;iy--) {
									if(iy==-1)	break;
									if(buttons[i][j-1].value == 1) {
										for(int jy = j+1;jy<=7;jy++) {
											if(jy==8)	break;
											if(buttons[i][jy].value == 3)	break;
											if(buttons[i][jy].value==0) {
												buttons[i][jy].setIcon(ps.point);
												buttons[i][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*오른쪽 */
							if(j+1<=7) {
								for(int iy = j+1;iy<=7;iy++) {
									if(iy==8)	break;
									if(buttons[i][j+1].value == 1) {
										for(int jy = j-1;jy>=0;jy--) {
											if(jy==-1)	break;
											if(buttons[i][jy].value == 3)	break;
											if(buttons[i][jy].value==0) {
												buttons[i][jy].setIcon(ps.point);
												buttons[i][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 왼쪽*/
							if(i+1<=7 && j-1>=0) {
								for(int ix = i+1,iy=j-1;ix<=7&&iy>=0;ix++,iy--) {
									if(ix==8||iy==-1)	break;
									if(buttons[i+1][j-1].value == 1) {
										for(int jx = i-1,jy = j+1;jx>=0&&jy<=7;jx--,jy++) {
											if(jx==-1 || jy==8)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value==0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 */
							if(i+1<=7) {
								for(int ix = i+1;ix<=7;ix++) {
									if(ix==8)	break;
									if(buttons[i+1][j].value == 1) {
										for(int jx = i-1;jx>=0;jx--) {
											if(jx == -1)		break;
											if(buttons[jx][j].value == 3)	break;
											if(buttons[jx][j].value==0) {
												buttons[jx][j].setIcon(ps.point);
												buttons[jx][j].value = 3;
												break;
											}
										}
									}
								}
							}
							/*하 오른쪽 */
							if(i+1<=7 && j+1<=7) {
								for(int ix = i+1,iy = j+1;ix<=7&&iy<=7;ix++,iy++) {
									if(ix == 8 || iy==8)		break;
									if(buttons[i+1][j+1].value == 1) {
										for(int jx = i-1,jy = j-1;jy>=0&&jx>=0;jx--,jy--) {
											if(jx==-1 || jy == -1)	break;
											if(buttons[jx][jy].value == 3)	break;
											if(buttons[jx][jy].value == 0) {
												buttons[jx][jy].setIcon(ps.point);
												buttons[jx][jy].value = 3;
												break;
											}
										}
									}
								}
							}
						}//흰돌 일때 포인트 경우의 수..
					}
				}
			}
		}

		}
		
	}
