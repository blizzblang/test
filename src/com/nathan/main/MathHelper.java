package com.nathan.main;

public class MathHelper {
	float[] Tan;
	int Depth=1;
	public MathHelper(int depth)
	{
		Depth=depth+0;
		initTan();
	}
	public void initTan()
	{
		Tan = new float[Depth*90];
		for(float i=0;i<90;i+=1/(float)Depth)
		{
			Tan[(int) (i*Depth)] =  (float) Math.tan(Math.toRadians(i));
		}
	}
	public double mathTan(float in)
	{
		return Tan[(int) (round(in)*Depth)];
	}
	private float round(float i)
	{
		return (float) Math.floor((double)i*Depth)/Depth;
	}

}
