package 课程设计_是男人就坚持20秒;

import javax.swing.*;

public class GameGO
{
	final static int WIDTH = 820;
	final static int HEIGHT = 820;

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("是男人就坚持20秒");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BulletPanel());
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(500, 100);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}