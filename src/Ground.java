import java.awt.Color;
import java.awt.Graphics;

public class Ground
{
	private int[][] obstacles = new int[Global.WIDTH][Global.HEIGH];
	
	//接受下落的方块
	public void accpet(Shape shape)
	{
		System.out.println("accpet shapes");
		
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if(shape.isMenber(x, y, false))
				{
					obstacles[shape.getleft() + x][shape.getTop() + y] = 1;
				}
			}
		}
		deleteFullLine();
	}
	//删除满行
	private void deleteFullLine()
	{
		for (int y = 0; y < Global.HEIGH; y++)
		{
			boolean full = true;
			for (int x = 0; x < Global.WIDTH; x++)
			{
				if(obstacles[x][y] == 0)
				{
					full = false;
				}
			}
			if(full)
			{
				deleteLine(y);
			}
		}
	}
	private void deleteLine(int lineNum)
	{
		for (int y = lineNum; y > 0; y--)
		{
			for (int x = 0; x < Global.WIDTH; x++)
			{
				obstacles[x][y] = obstacles[x][y-1]; 
			}
		}
		for (int x = 0; x < Global.WIDTH; x++)
		{
			obstacles[x][0] = 0;
		}
	}
	//显示障碍物
	public void drawMe(Graphics g)
	{
		System.out.println("drawMe shapes");
		
		g.setColor(Color.gray);
		for (int x = 0; x < Global.WIDTH; x++)
		{
			for (int y = 0; y < Global.HEIGH; y++)
			{
				if(obstacles[x][y] == 1)
				{
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, 
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	//判断是否可以进行某项动作
	public boolean isMoveable(Shape shape,int action)
	{
		int left = shape.getleft();
		int top = shape.getTop();
		switch (action)
		{
			case Shape.LEFT:
				left--;
				break;
			case Shape.RIGHT:
				left++;
				break;
			case Shape.DOWN:
				top++;
				break;
		}
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if(shape.isMenber(x, y, action == Shape.ROTATE))
				{
					if(top + y >= Global.HEIGH ||left + x < 0 || left + x >= Global.WIDTH 
							|| obstacles[left + x][top + y] == 1)
						return false;
				}
			}
		}
		return true;
	}
	//判断是否结束
	public boolean isFull()
	{
		for (int x = 0; x < Global.WIDTH; x++)
		{
			if(obstacles[x][0] == 1)
			{
				return true;
			}
		}
		return false;
	}
}
