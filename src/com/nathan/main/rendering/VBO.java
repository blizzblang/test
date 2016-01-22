package com.nathan.main.rendering;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;



public class VBO
{
  private ArrayList<Vertex> Verts = new ArrayList<Vertex>();
  private EntityShader shad = new EntityShader();
  private int vaoId=0;
  private int vboId=0;
  private int vboiId=0;
  private int TexId=0;
  private int indicesCount=0;
  public VBO()
  {
  vaoId = GL30.glGenVertexArrays();
  vboId = GL15.glGenBuffers();
  vboiId = GL15.glGenBuffers();
  }
  public void addVertex(Vertex  i)
  {
  Verts.add(i);
  }
  public void finalize()
  {
   FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(Verts.size() * Vertex.elementCount);
        for (Vertex i : Verts) 
        {
            verticesBuffer.put(i.getElements());
        }
        indicesCount = Verts.size();
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        for (int i=0;i<indicesCount;i++) 
        {
        	
        	indicesBuffer.put((byte) i);
        }
        indicesBuffer.flip();
        verticesBuffer.flip();  
         GL30.glBindVertexArray(vaoId);
         GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
         GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0,Vertex.positionElementCount, GL11.GL_FLOAT, false, Vertex.stride, Vertex.positionByteOffset);
        GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT,false, Vertex.stride, Vertex.colorByteOffset);
        GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT,  false, Vertex.stride, Vertex.textureByteOffset);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
         
  }
  public void render()
  {
    shad.Bind();
     GL13.glActiveTexture(GL13.GL_TEXTURE0);
     GL11.glBindTexture(GL11.GL_TEXTURE_2D, TexId);
     GL30.glBindVertexArray(vaoId);
     GL20.glEnableVertexAttribArray(0);
     GL20.glEnableVertexAttribArray(1);
     GL20.glEnableVertexAttribArray(2);
     GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
     GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);
     GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
     GL20.glDisableVertexAttribArray(0);
     GL20.glDisableVertexAttribArray(1);
     GL20.glDisableVertexAttribArray(2);
     GL30.glBindVertexArray(0);
  shad.unBind();
  }
	public void addTexture(int i) 
	{
		TexId=i;
		
	}
}
