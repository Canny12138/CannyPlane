package �γ����_�����˾ͼ��20��;

import java.awt.Color;
import java.awt.Graphics;

public class Plane
{
	public int x;
	public int y;
	
	public Plane()
	{
		x = 380;
		y = 380;
	}

	public void draw(Graphics page)
	{
		page.setColor(Color.cyan);
		page.drawRect(x, y, 30, 35);
	}
}