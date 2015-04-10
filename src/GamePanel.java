import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = -4470840062027189058L;
	private Ground ground;
	private Shape shape;
	//显示面板
	public void disPlay(Ground ground,Shape shape)
	{
		System.out.println("disPlay GamePanel");
		this.ground = ground;
		this.shape = shape;
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g)
	{
		// TODO Auto-generated method stub
		// 重新显示
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGH);
		if(shape != null && ground != null)
		{
			//g.setColor(Color.blue);
			ground.drawMe(g);
			shape.drawme(g);
		}
	}
	
	public GamePanel()
	{
		this.setSize(Global.CELL_SIZE * Global.WIDTH,Global.CELL_SIZE * Global.HEIGH);
	}
}
