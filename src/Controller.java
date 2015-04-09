import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Controller extends KeyAdapter implements ShapeListener
{
	private Shape shape;
	private Ground ground;
	private ShapeFactory shapeFactory;
	private GamePanel gamePanel;
	
	public Controller(Ground ground, GamePanel gamePanel, ShapeFactory shapeFactory)
	{
		this.ground = ground;
		this.gamePanel = gamePanel;
		this.shapeFactory = shapeFactory;
	}
	//键盘操作
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_UP:
				if(ground.isMoveable(shape, Shape.ROTATE))
					shape.rotate();
				break;
			case KeyEvent.VK_DOWN:
				if(isShapeMoveDownable(shape))
					shape.down();
				break;
			case KeyEvent.VK_LEFT:
				if(ground.isMoveable(shape, Shape.LEFT))
					shape.left();
				break;
			case KeyEvent.VK_RIGHT:
				if(ground.isMoveable(shape, Shape.RIGHT))
					shape.right();
				break;
		}
		gamePanel.disPlay(ground, shape);
	}
	public void newGame()
	{
		shape = shapeFactory.getShape(this);
	}
	@Override
	public void shapeDown(Shape shape)
	{
		gamePanel.disPlay(ground, shape);
	}
	//多个线程用到,要求同步
	@Override
	public synchronized boolean isShapeMoveDownable(Shape shape)
	{
		if(this.shape != shape)
		{
			return false;
		}
		if(ground.isMoveable(shape, Shape.DOWN))
			return true;
		ground.accpet(this.shape);
		if(!ground.isFull())
		{
			this.shape = shapeFactory.getShape(this);
		}
		return false;
	}
		
}
