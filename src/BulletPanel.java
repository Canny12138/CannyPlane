package �γ����_�����˾ͼ��20��;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Formatter;

import javax.swing.*;

public class BulletPanel extends JPanel
{
	Timer timer;
	public double currentTime;
	public int delay = 30;

	public Bullet bullet[];
	public Plane plane;
	JFrame setting;
	SettingPanel settingPanel;
	public ImageIcon image;
	public ImageIcon menuImage_plane;
	public ImageIcon menuImage_boom;
	public boolean U = false;
	public boolean D = false;
	public boolean L = false;
	public boolean R = false;
	public boolean seeAble_plane = false;

	public boolean over = false;
	public boolean win = false;
	public boolean isMenu = true;

	DecimalFormat df = new DecimalFormat("#0.00");

	public BulletPanel()
	{
		this.addKeyListener(new MoveListener());
		setFocusable(true);
		timer = new Timer(delay, new TimechangeListener());
		settingPanel = new SettingPanel();
		setBackground(Color.black);
		bullet = new Bullet[20];
		setting = new JFrame("Setting");
	}

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		if (isMenu)// �˵�����
		{
			timer.stop();
			page.setColor(Color.WHITE);
			page.setFont(new Font(null, Font.BOLD, 80));
			page.drawString("������", 120, 200);
			page.drawString("�ͼ��20��", 220, 300);
			page.setFont(new Font(null, Font.BOLD, 25));
			page.drawString("1.�س���ʼ��Ϸ...", 170, 450);
			page.drawString("2.G�����Ѷ�����", 170, 500);
			menuImage_plane = new ImageIcon(
					GameGO.class.getResource("/image/plane_rotating.png"));
			menuImage_plane.setImage(menuImage_plane.getImage()
					.getScaledInstance(80, 100, Image.SCALE_DEFAULT));
			menuImage_plane.paintIcon(this, page, 400, 120);
			menuImage_boom = new ImageIcon(
					GameGO.class.getResource("/image/boom.png"));
			menuImage_boom.setImage(menuImage_boom.getImage()
					.getScaledInstance(80, 100, Image.SCALE_DEFAULT));
			menuImage_boom.paintIcon(this, page, 120, 240);
		} else// ��Ϸ����
		{
			// ��ӡ���е��ӵ���
			for (int i = 0; i < 20; i++)
			{
				bullet[i].draw(page);
			}
			if (seeAble_plane)
				plane.draw(page);

			// ��ӡ��ǰʱ��
			page.setColor(Color.WHITE);
			page.setFont(new Font(null, Font.BOLD, 30));
			page.drawString("Time:" + df.format(currentTime), 20, 35);

			if (!over)// ��Ϸ�������н���
			{
				if (L)
					image = new ImageIcon(
							GameGO.class.getResource("/image/plane_left.png"));
				else if (R)
					image = new ImageIcon(
							GameGO.class.getResource("/image/plane_right.png"));
				else
					image = new ImageIcon(
							GameGO.class.getResource("/image/plane.png"));
			} else// ��Ϸʧ�ܽ���
			{
				image = new ImageIcon(
						GameGO.class.getResource("/image/boom.png"));
				page.setColor(Color.RED);
				page.setFont(new Font(null, Font.BOLD, 100));
				page.drawString("GAME OVER!", 80, 400);
				page.setFont(new Font(null, Font.BOLD, 30));
				page.drawString("Press Enter to continue", 230, 450);
				page.setColor(Color.WHITE);
				page.setFont(new Font(null, Font.BOLD, 20));
				page.drawString("Press Esc return to menu.", 20, 750);
				currentTime = 0;
			}
			image.setImage(image.getImage().getScaledInstance(30, 35,
					Image.SCALE_DEFAULT));
			image.paintIcon(this, page, plane.x, plane.y);
			if (win)// ��Ϸ�ɹ�����
			{
				page.setColor(Color.RED);
				page.setFont(new Font(null, Font.BOLD, 100));
				page.drawString("YOU WIN!", 170, 400);
				page.setFont(new Font(null, Font.BOLD, 30));
				page.drawString("Press Enter to continue", 230, 450);
				page.setColor(Color.WHITE);
				page.setFont(new Font(null, Font.BOLD, 20));
				page.drawString("Press Esc return to menu.", 20, 750);
			}
		}
	}

	private class TimechangeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			for (int i = 0; i < 20; i++)
			{
				bullet[i].bullet_x += bullet[i].bullet_move_x;
				bullet[i].bullet_y += bullet[i].bullet_move_y;

				if (!seeAble_plane && bullet[i].bullet_x > plane.x
						&& bullet[i].bullet_x < (plane.x + 30)
						&& bullet[i].bullet_y > plane.y
						&& bullet[i].bullet_y < (plane.y + 35))
				{
					timer.stop();
					over = true;
				}

				if (bullet[i].bullet_x < -10 || bullet[i].bullet_x > 800
						|| bullet[i].bullet_y < -10 || bullet[i].bullet_y > 800)
					bullet[i] = new Bullet();
			}
			if (U == true)
				plane.y -= 2;
			if (L == true)
				plane.x -= 2;
			if (D == true)
				plane.y += 2;
			if (R == true)
				plane.x += 2;

			currentTime += timer.getDelay() / 1000.0;
			if (currentTime >= 20)
			{
				win = true;
				timer.stop();
				currentTime = 20;
			}
			repaint();
		}
	}

	private class MoveListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			switch (event.getKeyCode())
			{
			case KeyEvent.VK_W:
				U = true;
				break;
			case KeyEvent.VK_A:
				L = true;
				break;
			case KeyEvent.VK_S:
				D = true;
				break;
			case KeyEvent.VK_D:
				R = true;
				break;
			case KeyEvent.VK_L:
				seeAble_plane = true;
				break;
			default:
			}
			if (isMenu && event.getKeyCode() == KeyEvent.VK_G)
			{
				settingPanel.setting
						.setText(Integer.toString(settingPanel.difficult));
				setting.getContentPane().add(settingPanel);
				setting.setSize(300, 100);
				setting.setLocation(750, 450);
				setting.setResizable(false);
				setting.setVisible(true);
			}
			if (event.getKeyCode() == KeyEvent.VK_ENTER
					&& (over || isMenu || win))
			{
				for (int i = 0; i < 20; i++)
				{
					bullet[i] = new Bullet();
				}
				plane = new Plane();
				over = false;
				isMenu = false;
				win = false;
				currentTime = 0;
				delay = (6 - settingPanel.difficult) * 10;
				timer = new Timer(delay, new TimechangeListener());
				timer.start();
			}
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE && (over || win))
			{
				isMenu = true;
				timer.start();
			}
		}

		public void keyTyped(KeyEvent event)
		{
		}

		public void keyReleased(KeyEvent event)
		{
			switch (event.getKeyCode())
			{
			case KeyEvent.VK_W:
				U = false;
				break;
			case KeyEvent.VK_A:
				L = false;
				break;
			case KeyEvent.VK_S:
				D = false;
				break;
			case KeyEvent.VK_D:
				R = false;
				break;
			case KeyEvent.VK_L:
				seeAble_plane = false;
				break;
			default:
			}
		}
	}
}
