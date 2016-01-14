package com.nathan.main;

public class Main {
	public static void main(String[] a)
	{
		Timer timeout = new Timer();
		timeout.start();
		System.out.println("Hello World, my favorite color is green");
		System.out.println("Do I look like I know what a jpeg is?");
		UnsignedByte four_bits = new UnsignedByte(169);
		System.out.println("Byte Value "+four_bits.getIntValue()+" or hex "+four_bits.getStrValue());
		timeout.stop();
		System.out.println("Time: "+timeout.getDifference()+" : "+timeout.getSecondsDifference());
	}
}
