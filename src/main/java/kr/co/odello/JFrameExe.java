package kr.co.odello;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class JFrameExe extends JFrame{
	JPanel p = new JPanel();
	JButton b = new JButton("Hello");
	public static void main(String args[]) {
		new BasicSwing();
	}
	public JFrameExe() {
		super("Basic Swing App");
		setSize(400,300);
		setResizable(true);
		
		p.add(b);//panel에버튼 b 추가 
		add(p);
		
		setVisible(true);
	}
}
