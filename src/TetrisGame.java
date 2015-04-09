import javax.swing.JFrame;
import javax.swing.JPanel;


public class TetrisGame
{

	public static void main(String[] args)
	{
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Controller controller = new Controller(ground, gamePanel, shapeFactory);
		gamePanel.addKeyListener(controller);
		
		JPanel jp = new JPanel();
		jp.setVisible(true);
		
		JFrame jFrame = new JFrame("¶íÂÞË¹·½¿é");
		jFrame.add(gamePanel,"Center");
		jFrame.add(jp,"East");
		jFrame.addKeyListener(controller);
		jFrame.setLocation(400,200);
		jFrame.setSize(gamePanel.getWidth() + 10,gamePanel.getHeight() + 30);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.newGame();
	}
	
}
