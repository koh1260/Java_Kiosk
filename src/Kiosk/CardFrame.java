package Kiosk;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CardFrame extends JFrame {
	Timer t;
	private Font f1;
	private Font f2;
	private Font f3;
	private Font f4;
	private JLabel lbl1, lbl2, lbl3, lbl4;

	public CardFrame() {
		ex.stan_number++;
		setVisible(true);
		setSize(700, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container c = getContentPane();
		c.setLayout(null);

		JPanel pay_north = new JPanel();
		pay_north.setLayout(null);
		pay_north.setBackground(new Color(107, 167, 240));
		pay_north.setBounds(0, 0, 700, 150);
		add(pay_north);

		JLabel title_label = new JLabel("현금 결제");
		title_label.setBounds(210, 35, 410, 60);
		title_label.setForeground(Color.white);
		title_label.setFont(new Font("맑은 고딕", Font.BOLD, 60));
		pay_north.add(title_label);

		JPanel pa1 = new JPanel();
		JPanel pa2 = new JPanel();
		JPanel pa3 = new JPanel();

		f1 = new Font("맑은 고딕", Font.BOLD, 40);
		f2 = new Font("맑은 고딕", Font.BOLD, 40);
		f3 = new Font("맑은 고딕", Font.BOLD, 30);
		f4 = new Font("맑은 고딕", Font.BOLD, 30);

		String[] text = { "카드를 넣어주세요. .", "카드를 넣어주세요. . .", "결제 중입니다.", "결제 중입니다. .", "결제 중입니다. . .", "결제가 완료되었습니다.",
				"결제가 완료되었습니다.", "결제가 완료되었습니다." };


		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("card.png"));
		Image image = img.getImage();
		Image chimage = image.getScaledInstance(500, 450, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(chimage));

		lbl2 = new JLabel("카드를 넣어주세요.", JLabel.CENTER);

		lbl2.setFont(f2);

		c.setBackground(Color.WHITE);
		c.add(pa1);
		pa1.setSize(700, 150);
		pa1.setBackground(new Color(107, 167, 240));
		pa2.setLayout(null);
		c.add(imageLabel);
		imageLabel.setBounds(70,200, 500, 450);
		add(lbl2);
		lbl2.setBounds(0, 650, 700, 200);
		lbl2.setOpaque(true);
		lbl2.setBackground(new Color(245, 245, 245));


		class setTx implements ActionListener {
			int n = -1;
			
			public void actionPerformed(ActionEvent e) {
				n++;
				if (n == 6) {
					try {
						Thread.sleep(800);
					} catch (InterruptedException e1) {

					}
					new OrderCom_Screen();
					t.stop();
					setVisible(false);
				}
				lbl2.setText(text[n]);
			}
		}

		ActionListener listener = new setTx();
		t = new Timer(800, listener);
		t.start();
	}
}