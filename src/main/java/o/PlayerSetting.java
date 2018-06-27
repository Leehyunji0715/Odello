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
}
