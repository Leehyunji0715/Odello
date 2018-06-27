package o;
import o.Board.*;

public class GameRuleSetting {
	int x = Board.pos_x;//static 변수 pos_x를 임시 저장하는 x 
	int y = Board.pos_y;//static 변수 pos_y를 임시 저장하는 y 

	public void checkChacngeStone() {//상하좌우대각선 모두 돌이 있는 지 확인하는 것 
		//윗 방향 
		System.out.printf("%d %d\n",x,y);
	}
	
	public void isOKShadow() {
		
	}
	public void setStone(int x, int y, int user_value) {

	}
}
/*			for(int ix=x-1; ix>=0; ix--)	// 상 가운대 방향으로 돌 조사
			{
				if(Board.buttons[x-1][y].value==0)
					break;
				if((Board.buttons[x-1][y].value==2 && Board.buttons[ix][y].value==1 || (Board.buttons[x-1][y].value==1 && Board.buttons[ix][y].value==2)))	
				{
					for(int jx=ix; jx<=x; jx++)
					{	
						if(user==1) {
							Board.buttons[jx][y].setIcon(PlayerSetting.p1);
						}
						else if(user==2) {
							Board.buttons[jx][y].setIcon(PlayerSetting.p2);
						}
					}
					break;
				}
			}		
 */
