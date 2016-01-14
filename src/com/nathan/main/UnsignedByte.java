package com.nathan.main;

public class UnsignedByte 
{
	private short Data;
	public UnsignedByte()
	{
		this(0);
	}
	public UnsignedByte(int i)
	{
		if(checkData(i))
		{
			setData(i);
		}
	}
	public short getData()
	{
		return (short) (Data & 0xFFFF);
	}
	public void setData(int i)
	{
		Data = (short) (0xFFFF & i);
	}
	public boolean checkData(int i)
	{
		if(i >= Math.pow(2, 8))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public int getIntValue()
	{
		return getData();
	}
	public String getStrValue()
	{
		return Integer.toHexString(getData());
	}

}
