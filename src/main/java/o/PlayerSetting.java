package o;

import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;

public class PlayerSetting extends Panel {
	public static ImageIcon p1;	// p1 의 말 과 쉐도우
	public ImageIcon p1_shadow;
	public static ImageIcon p2;	// p2 의 말 과 쉐도우
	public ImageIcon p2_shadow;
	public ImageIcon point;
	public static int user=2;
	public static ImageIcon sbak;
	public static ImageIcon p1_win;
	public static ImageIcon p2_win;
	
	public static ImageIcon one,two,three,four,five,six,seven,eight,nine,zero;
	/*user!
	 * 1:p1
	 * 2:p2
	 */
	public PlayerSetting() {
		p1 = new ImageIcon(this.getClass().getResource("../images/O.png"));
		p1_shadow = new ImageIcon(this.getClass().getResource("../images/O_shadow.png"));
		p1_win = new ImageIcon(this.getClass().getResource("../images/O_win.png"));
		
		p2 = new ImageIcon(this.getClass().getResource("../images/X.png"));
		p2_shadow = new ImageIcon(this.getClass().getResource("../images/X_shadow.png"));
		p2_win = new ImageIcon(this.getClass().getResource("../images/X_win.png"));
		
		point = new ImageIcon(this.getClass().getResource("../images/point.png"));
		one = new ImageIcon(this.getClass().getResource("../images/1.png"));
		two = new ImageIcon(this.getClass().getResource("../images/2.png"));
		three = new ImageIcon(this.getClass().getResource("../images/3.png"));
		four = new ImageIcon(this.getClass().getResource("../images/4.png"));
		five = new ImageIcon(this.getClass().getResource("../images/5.png"));
		six = new ImageIcon(this.getClass().getResource("../images/6.png"));
		seven = new ImageIcon(this.getClass().getResource("../images/7.png"));
		eight = new ImageIcon(this.getClass().getResource("../images/8.png"));
		nine = new ImageIcon(this.getClass().getResource("../images/9.png"));
		zero = new ImageIcon(this.getClass().getResource("../images/0.png"));
		
		sbak = new ImageIcon(this.getClass().getResource("../images/sbak.png"));
	}
	public void changeUser() {
		if(user==1)	
			user=2;
		else if(user==2)	
			user=1;
	}
}
