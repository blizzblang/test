package com.nathan.main;

import com.nathan.main.Util.MathHelper;
import com.nathan.main.Util.UnsignedByte;
import com.nathan.main.time.Timer;

public class Main {
	public static void main(String[] a)
	{
		Timer timeout = new Timer();
		timeout.start();
		int Depth =3;
		MathHelper Tangent = new MathHelper(Depth);
		
		System.out.println("Hello World, my favorite color is green");
		System.out.println("Do I look like I know what a jpeg is?");
		UnsignedByte four_bits = new UnsignedByte(169);
		System.out.println("Byte Value "+four_bits.getIntValue()+" or hex "+four_bits.getStrValue());
		timeout.stop();
		System.out.println("Time: "+timeout.getDifference()+" : "+timeout.getSecondsDifference());
		Timer Hard = new Timer();
		Timer Cheat = new Timer();
		int Stress = (int) 1E8;
		Hard.start();
		for(float i=0;i<(float)Stress/(float)Math.pow(10, Depth);i+=1/(float)(Math.pow(10, Depth)))
		{
			Math.tan(Math.toRadians(i%90));
		}
		Hard.stop();
		System.out.println("Hard Time: "+Hard.getDifference()+" : "+Hard.getSecondsDifference());
		Cheat.start();
		for(float i=0;i<(float)Stress/(float)Math.pow(10, Depth);i+=1/(float)(Math.pow(10, Depth)))
		{
			Tangent.mathTan(i%90);
		}
		Cheat.stop();
		System.out.println("Cheat Time: "+Cheat.getDifference()+" : "+Cheat.getSecondsDifference());
	}
}
