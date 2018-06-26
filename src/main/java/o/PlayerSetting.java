package o;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;

public class PlayerSetting extends Panel {
	public ImageIcon p1, p1_shadow;	// p1 의 말 과 쉐도우
	public ImageIcon p2, p2_shadow;	// p2 의 말 과 쉐도우
	public ImageIcon point;
	public int play_x, play_y, user=2;
	public int pos_x, pos_y;//?
	/*user!
	 * 1:p1
	 * 2:p2
	 */
	public PlayerSetting() {
		p1 = new ImageIcon(this.getClass().getResource("../images/O.png"));
		p1_shadow = new ImageIcon(this.getClass().getResource("../images/O_shadow.png"));
		p2 = new ImageIcon(this.getClass().getResource("../images/X.png"));
		p2_shadow = new ImageIcon(this.getClass().getResource("../images/X_shadow.png"));
		point = new ImageIcon(this.getClass().getResource("../images/point.png"));
	}
	public void changeUser() {
		if(user==1)	
			user=2;
		else if(user==2)	
			user=1;
	}
	
	
	public void xysetting() {//보류 
		if(pos_x < 10)	
			pos_x = 0;
		else if(pos_x > 90)
			pos_x = 8;
		else
			pos_x = (int)pos_x/10;
		if(pos_y < 10)
			pos_y = 0;
		else if(pos_y > 90)
			pos_y = 8;
		else
			pos_y = (int)pos_y/10;
	}
}
