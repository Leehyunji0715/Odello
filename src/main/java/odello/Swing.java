package odello;
import javax.swing.*;

public class Swing extends JFrame{
	JPanel p = new JPanel();
	JButton b = new JButton("Start Game! "); //JButton 을 아이콘으로도 추가할 수 있다. 
	
	JTextField t = new JTextField("Hi",20);
	JTextArea ta=new JTextArea("How\nare\nyou?",5,20);
	JLabel l = new JLabel("What's up?");
	
	public static void main(String args[]) {
		new Swing();
	}
	
	public Swing() {
		super("Basic Swing App");
		setSize(400,300);
		setResizable(true);
		p.add(b);//panel에버튼 b 추가 
		p.add(t);
		p.add(ta);
		p.add(l); 
		//p.add(cb);
		
		add(p);
		
		setVisible(true);
	}
}
