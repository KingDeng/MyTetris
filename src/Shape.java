import java.awt.Color;
import java.awt.Graphics;


public class Shape
{
	private int[][] body;//图形的所有状态
	private int status;//当前状态
	private int topdistence = -1;//图形到顶部的距离
	private int leftdistence = 6;//图形到左边界的距离
	private int getY;
	
	private ShapeListener listener;
	
	public static final int ROTATE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT =2;
	public static final int DOWN = 3;
	
	//方块向左移动
	public void left()
	{
		System.out.println("Shape's left");
		leftdistence--;
	}
	//方块向右移动
	public void right()
	{
		System.out.println("Shape's right");
		leftdistence++;
	}
	//方块向下移动
	public void down()
	{
		System.out.println("Shape's down");
		topdistence++;
	}
	//方块旋转
	public void rotate()
	{
		System.out.println("Shape's rotate");
		status = (status + 1) % body.length;
	}
	//显示方块
	public void drawme(Graphics g)
	{
		System.out.println("Shape's disPlay");
		
		g.setColor(Color.blue);
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				if(getFlagByPoint(x, y))
				{
					g.fill3DRect((leftdistence + x) * Global.CELL_SIZE, 
							(topdistence + y) * Global.CELL_SIZE, 
							Global.CELL_SIZE, Global.CELL_SIZE, true);
					getY = y;
				}
			}
		}
	}
	private boolean getFlagByPoint(int x, int y)
	{
		return body[status][y * 4 + x] == 1;
	}
	
	public boolean isMenber(int x, int y, boolean rotate)
	{
		int tempStatus = status;
		if(rotate)
		{
			tempStatus = (status + 1) % body.length;
		}
		return body[tempStatus][y * 4 +x] == 1;
	}
	//方块有自动下落的特性
	private class Driver implements Runnable
	{
		public void run()
		{
			while (listener.isShapeMoveDownable(Shape.this))
			{
				System.out.println((topdistence + getY));
				System.out.println(getY);
				down();
				listener.shapeDown(Shape.this);
				//listener.shapeDown(Shape.this);
				try
				{
					Thread.sleep(700);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//监听器方法
	public void addListener(ShapeListener l)
	{
		if(l != null)
			this.listener = l;
	}
	//设置当前图形的所有状态 
	public void setBody(int body[][])
	{
		this.body = body;
	}
	//设置图形的当前状态
	public void setStatus(int status)
	{
		this.status = status;
	}
	public Shape()
	{
		new Thread(new Driver()).start();
	}
	//得到图形的当前位置
	public int getTop()
	{
		return topdistence;
	}
	public int getleft()
	{
		return leftdistence;
	}
	
}
