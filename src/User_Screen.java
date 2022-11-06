import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class User_Screen extends JFrame{
	private static JLabel user;
	
	public User_Screen(){
		setDisplay();
	}
	

	public void setDisplay() {		
		setTitle("주문");
		setVisible(true);
		setSize(700, 900);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
