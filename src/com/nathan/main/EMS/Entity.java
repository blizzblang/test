package com.nathan.main.EMS;

import java.util.ArrayList;

public abstract class Entity
{
  private float[] Pos;
  private float[] Dim;
  enum EntityType
  {
    PlayerEntity(0),NonPlayerEntity(1),Prop(2);
    private int Type;
    private EntityType(int i)
    {
      Type=i;
    }
  }
  protected EntityType EntType; 
  public Entity(EntityType i)
  {
  EntType = i;
  }
  public float getX(){return Pos[0];}
  public float getY(){return Pos[1];}
  public float getZ(){return Pos[2];}
  public float[] getXYZ(){return Pos;}
  public void setX(float i){Pos[0]=0+i;}
  public void setY(float i){Pos[1]=0+i;}
  public void setZ(float i){Pos[2]=0+i;}
  public void setXYZ(float[] i){setXYZ(i[0],i[1],i[2]);}
  public void setXYZ(float x,float y,float z){Pos = new float[]{x,y,z};}
  public abstract void Tick(ArrayList<Entity> i);
  public abstract void Render();
}
