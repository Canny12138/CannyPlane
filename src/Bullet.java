package 课程设计_是男人就坚持20秒;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Bullet
{
	private static final int BULLET_D = 10;
	public int bullet_x;
	public int bullet_y;
	public int bullet_move_x;
	public int bullet_move_y;
	public int chooseLine;
	// public int bullet_speed;//有空做
	public static Random generator = new Random();

	public Bullet()
	{
		chooseLine = Math.abs(generator.nextInt()) % 4 + 1;
		if (chooseLine == 1)// 左
		{
			bullet_x = 0;
			bullet_y = Math.abs(generator.nextInt()) % 790;
			bullet_move_x = generator.nextInt(5) + 1;
			bullet_move_y = generator.nextInt(10) - 5;

		}
		if (chooseLine == 2)// 右
		{
			bullet_x = 790;
			bullet_y = Math.abs(generator.nextInt()) % 790;
			bullet_move_x = -(generator.nextInt(5)) - 1;
			bullet_move_y = generator.nextInt(10) - 5;

		}
		if (chooseLine == 3)// 上
		{
			bullet_y = 0;
			bullet_x = Math.abs(generator.nextInt()) % 790;
			bullet_move_y = generator.nextInt(5) + 1;
			bullet_move_x = generator.nextInt(10) - 5;

		}
		if (chooseLine == 4)// 下
		{
			bullet_y = 790;
			bullet_x = Math.abs(generator.nextInt()) % 790;
			bullet_move_y = -(generator.nextInt(5)) - 1;
			bullet_move_x = generator.nextInt(10) - 5;

		}
	}

	public void draw(Graphics page)
	{
		page.setColor(Color.red);
		page.fillOval(bullet_x, bullet_y, BULLET_D, BULLET_D);
	}
}
