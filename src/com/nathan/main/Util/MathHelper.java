package com.nathan.main.Util;

public class MathHelper {
	float[] Tan;
	int Depth=1;
	public MathHelper(int depth)
	{
		Depth=depth+0;
		System.out.println("Making "+Depth*90*(10*Depth)+" Data Sets");
		initTan();
	}
	public void initTan()
	{
		Tan = new float[Depth*90*(10*Depth)];
		for(float i=0;i<90;i+=1/(float)Depth)
		{
			Tan[(int) (i*Depth)] =  (float) Math.tan(Math.toRadians(i));
		}
	}
	public double mathTan(float in)
	{
		return Tan[(int) (round(in)*Math.pow(10, Depth))%Tan.length];
	}
	private float round(float i)
	{
		int mod = (int) Math.pow(10, Depth);
		float a = (float) Math.round((double)i*(mod))/(float)(mod);
		return a;
	}

}
