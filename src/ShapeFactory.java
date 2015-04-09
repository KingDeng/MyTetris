import java.util.Random;


public class ShapeFactory
{
	private int[][][] shapes = new int[][][]{
												{
													{	1,0,0,0,	1,1,1,0,
														0,0,0,0,	0,0,0,0	  },
													{	1,1,0,0,	1,0,0,0,
														1,0,0,0,	0,0,0,0   },
													{	1,1,1,0,	0,0,1,0,
														0,0,0,0,	0,0,0,0   },
													{	0,1,0,0,	0,1,0,0,
														1,1,0,0,	0,0,0,0   }
												},
												
												{
													{	0,1,0,0,	1,1,1,0,
														0,0,0,0,	0,0,0,0	  },
													{	0,1,0,0,	1,1,0,0,
														0,1,0,0,	0,0,0,0   },
													{	1,1,1,0,	0,1,0,0,
														0,0,0,0,	0,0,0,0   },
													{	1,0,0,0,	1,1,0,0,
														1,0,0,0,	0,0,0,0   }
												},
												
												{
													{	1,1,0,0,	1,1,0,0,
														0,0,0,0,	0,0,0,0	  },
												},
												
												{
													{	1,1,0,0,	0,1,1,0,
														0,0,0,0,	0,0,0,0   },
													{	0,1,0,0,	1,1,0,0,
														1,0,0,0,	0,0,0,0   }
												},
												
												{
													{	0,1,1,0,	1,1,0,0,
														0,0,0,0,	0,0,0,0   },
													{	1,0,0,0,	1,1,0,0,
														0,1,0,0,	0,0,0,0   }
												},
												
												{
													{	1,1,1,1,	0,0,0,0,
														0,0,0,0,	0,0,0,0   },
													{	1,0,0,0,	1,0,0,0,
														1,0,0,0,	1,0,0,0   }
												}
	                                       };
	
	//Éú²ú·½¿é
	public Shape getShape(ShapeListener listener)
	{
		System.out.println("get shapes");
		
		Shape shape = new Shape();
		shape.addListener(listener);
		int type = new Random().nextInt(shapes.length);
		shape.setBody(shapes[type]);
		shape.setStatus(new Random().nextInt(shapes[type].length));
		return shape;
	}
}
