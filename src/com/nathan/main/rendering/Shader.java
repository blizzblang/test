package com.nathan.main.rendering;
public abstract class Shader
{
  private String Frag="";
  private String Vert="";
  private int ShaderId;
  public Shader(String frag,String vert)
  {
  Frag=frag;
  Vert=vert;
  init();
  }
  protected void init()
  {
  
  }
  public abstract void Bind();
  public abstract void unBind();
}
