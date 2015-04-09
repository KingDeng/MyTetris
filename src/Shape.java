import java.awt.Color;
import java.awt.Graphics;


public class Shape
{
	private int[][] body;//ͼ�ε�����״̬
	private int status;//��ǰ״̬
	private int topdistence = -1;//ͼ�ε������ľ���
	private int leftdistence = 6;//ͼ�ε���߽�ľ���
	private int getY;
	
	private ShapeListener listener;
	
	public static final int ROTATE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT =2;
	public static final int DOWN = 3;
	
	//���������ƶ�
	public void left()
	{
		System.out.println("Shape's left");
		leftdistence--;
	}
	//���������ƶ�
	public void right()
	{
		System.out.println("Shape's right");
		leftdistence++;
	}
	//���������ƶ�
	public void down()
	{
		System.out.println("Shape's down");
		topdistence++;
	}
	//������ת
	public void rotate()
	{
		System.out.println("Shape's rotate");
		status = (status + 1) % body.length;
	}
	//��ʾ����
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
	//�������Զ����������
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
	//����������
	public void addListener(ShapeListener l)
	{
		if(l != null)
			this.listener = l;
	}
	//���õ�ǰͼ�ε�����״̬ 
	public void setBody(int body[][])
	{
		this.body = body;
	}
	//����ͼ�εĵ�ǰ״̬
	public void setStatus(int status)
	{
		this.status = status;
	}
	public Shape()
	{
		new Thread(new Driver()).start();
	}
	//�õ�ͼ�εĵ�ǰλ��
	public int getTop()
	{
		return topdistence;
	}
	public int getleft()
	{
		return leftdistence;
	}
	
}
