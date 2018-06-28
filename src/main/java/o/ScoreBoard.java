package o;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ScoreBoard extends JFrame{
		JFrame scoreShowBoard = new JFrame();
		int p1_10digits_score,p1_1digit_score;
		int p2_10digits_score,p2_1digit_score;
		ImageIcon score = new ImageIcon();//점수 그림 + 플레이어 그림 
		
		static JLabel p1 = new JLabel();
		static JLabel p2 = new JLabel();

		JLabel sc = new JLabel();
		JLabel p1_10digits = new JLabel();			
		JLabel p1_1digit = new JLabel();		
		JLabel p2_10digits = new JLabel();			
		JLabel p2_1digit = new JLabel();
		
		
		public ScoreBoard(){
			p1.setIcon(PlayerSetting.p1);
			p1.setBounds(50, 0, 100, 100);
			
			p2.setIcon(PlayerSetting.p2);
			p2.setBounds(200, 0, 100, 100);
			
			scoreShowBoard.setTitle("Score Board");
			scoreShowBoard.setSize(300, 200);
			scoreShowBoard.setResizable(false);
			scoreShowBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);

			scoreShowBoard.add(p1);
			scoreShowBoard.add(p2);
		}
		
		public void changeNum() {
			p1_10digits_score = (int)(Board.p1_score/10);
			p1_1digit_score = (Board.p1_score%10);
			
			p2_10digits_score = (int)(Board.p2_score/10);
			p2_1digit_score = (Board.p2_score%10);
			
			if (p1_10digits_score == 0)	
				score = PlayerSetting.zero;
			else if (p1_10digits_score == 1)
				score = PlayerSetting.one;
			else if (p1_10digits_score == 2)
				score = PlayerSetting.two;
			else if (p1_10digits_score == 3)
				score = PlayerSetting.three;
			else if (p1_10digits_score == 4)
				score = PlayerSetting.four;
			else if (p1_10digits_score == 5)
				score = PlayerSetting.five;
			else if (p1_10digits_score == 6)
				score = PlayerSetting.six;
			
			p1_10digits.setIcon(score);	
			p1_10digits.setBounds(40,70,75,100);
			scoreShowBoard.add(p1_10digits);
			
			if (p1_1digit_score == 0)
				score = PlayerSetting.zero;
			else if (p1_1digit_score == 1)
				score = PlayerSetting.one;
			else if (p1_1digit_score == 2)
				score = PlayerSetting.two;
			else if (p1_1digit_score == 3)
				score = PlayerSetting.three;
			else if (p1_1digit_score == 4)
				score = PlayerSetting.four;
			else if (p1_1digit_score == 5)
				score = PlayerSetting.five;
			else if (p1_1digit_score == 6)
				score = PlayerSetting.six;
			else if (p1_1digit_score == 7)
				score = PlayerSetting.seven;
			else if (p1_1digit_score == 8)
				score = PlayerSetting.eight;
			else if (p1_1digit_score == 9)
				score = PlayerSetting.nine;
			
			p1_1digit.setIcon(score);	
			p1_1digit.setBounds(80,70,75,100);
			scoreShowBoard.add(p1_1digit);
			
			if (p2_10digits_score == 0)
				score = PlayerSetting.zero;
			else if (p2_10digits_score == 1)
				score = PlayerSetting.one;
			else if (p2_10digits_score == 2)
				score = PlayerSetting.two;
			else if (p2_10digits_score == 3)
				score = PlayerSetting.three;
			else if (p2_10digits_score == 4)
				score = PlayerSetting.four;
			else if (p2_10digits_score == 5)
				score = PlayerSetting.five;
			else if (p2_10digits_score == 6)
				score = PlayerSetting.six;
			
			p2_10digits.setIcon(score);
			p2_10digits.setBounds(190,70,75,100);
			scoreShowBoard.add(p2_10digits);
			
			if (p2_1digit_score == 0)
				score = PlayerSetting.zero;
			else if (p2_1digit_score == 1)
				score = PlayerSetting.one;
			else if (p2_1digit_score == 2)
				score = PlayerSetting.two;
			else if (p2_1digit_score == 3)
				score = PlayerSetting.three;
			else if (p2_1digit_score == 4)
				score = PlayerSetting.four;
			else if (p2_1digit_score == 5)
				score = PlayerSetting.five;
			else if (p2_1digit_score == 6)
				score = PlayerSetting.six;
			else if (p2_1digit_score == 7)
				score = PlayerSetting.seven;
			else if (p2_1digit_score == 8)
				score = PlayerSetting.eight;
			else if (p2_1digit_score == 9)
				score = PlayerSetting.nine;
			
			p2_1digit.setIcon(score);
			p2_1digit.setBounds(230,70,75,100);
			scoreShowBoard.add(p2_1digit);	
			
			scoreShowBoard.setLayout(null);
			scoreShowBoard.setVisible(true);
		}
		public void showWinner() {
			
		}

	}
