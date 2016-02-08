package com.nathan.main.rendering;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.nathan.main.Main;
import com.nathan.main.EMS.Entity;

public class DefaultShader extends Shader
{
  private FloatBuffer matrix44Buffer = BufferUtils.createFloatBuffer(16);
  private int projectionMatrixLocation;
  private int viewMatrixLocation;
  private int modelMatrixLocation;  
  public DefaultShader(String vert,String frag)
  {
    super(vert,frag);
    init();
  }
  	protected void init()
	{
		 int vertShader = 0, fragShader = 0;
	       
	       try 
	       {
	           vertShader = createShader(Vert,ARBVertexShader.GL_VERTEX_SHADER_ARB);
	           fragShader = createShader(Frag,ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
	       }
	       catch(Exception exc) 
	       {
	           exc.printStackTrace();
	           return;
	       }
	       finally 
	       {
	           if(vertShader == 0 || fragShader == 0)
	               return;
	       }
	        
	       ShaderId = ARBShaderObjects.glCreateProgramObjectARB();
	        
	       if(ShaderId == 0)return;
	       ARBShaderObjects.glAttachObjectARB(ShaderId, vertShader);
	       ARBShaderObjects.glAttachObjectARB(ShaderId, fragShader);
	       GL20.glBindAttribLocation(ShaderId, 0, "in_Position");
	       GL20.glBindAttribLocation(ShaderId, 1, "in_Color");
	       GL20.glBindAttribLocation(ShaderId, 2, "in_TextureCoord");
	       ARBShaderObjects.glLinkProgramARB(ShaderId);
	       if (ARBShaderObjects.glGetObjectParameteriARB(ShaderId, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) 
	       {
	           System.err.println(getLogInfo(ShaderId));
	           return;
	       }
	        
	       ARBShaderObjects.glValidateProgramARB(ShaderId);
	       if (ARBShaderObjects.glGetObjectParameteriARB(ShaderId, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) 
	       {
	           System.err.println(getLogInfo(ShaderId));
	           return;
	       }
	        projectionMatrixLocation = GL20.glGetUniformLocation( ShaderId,"projectionMatrix");
	        viewMatrixLocation = GL20.glGetUniformLocation( ShaderId, "viewMatrix");
	        modelMatrixLocation = GL20.glGetUniformLocation( ShaderId, "modelMatrix");
	}
	public void Bind(Matrix4f i)
	{
			 ARBShaderObjects.glUseProgramObjectARB(ShaderId);

	         
		 	Main.cam.getProjectionMatrix().store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
	        
	        Main.cam.getViewMatrix().store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
	        
	        i.store(matrix44Buffer); matrix44Buffer.flip();
	        
	        GL20.glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);
	}
	public void Bind(Matrix4f p,Matrix4f v,Matrix4f m)
	{
		 ARBShaderObjects.glUseProgramObjectARB(ShaderId);

        if(p == null)
        {
	 	Main.cam.getProjectionMatrix().store(matrix44Buffer); matrix44Buffer.flip();
        }
        else
        {
	 	p.store(matrix44Buffer); matrix44Buffer.flip();
        }
       GL20.glUniformMatrix4(projectionMatrixLocation, false, matrix44Buffer);
       
       if(v==null)
       {
       Main.cam.getViewMatrix().store(matrix44Buffer); matrix44Buffer.flip();
       }
       else
       {
    	v.store(matrix44Buffer); matrix44Buffer.flip();
       }
       GL20.glUniformMatrix4(viewMatrixLocation, false, matrix44Buffer);
       if(m!=null)
       {
       m.store(matrix44Buffer); matrix44Buffer.flip();
       }
       else
       {
    	   Matrix4f t = new Matrix4f();
    	
       t.store(matrix44Buffer);matrix44Buffer.flip();
       }
       
       GL20.glUniformMatrix4(modelMatrixLocation, false, matrix44Buffer);
	}
	public void unBind()
	{
	   ARBShaderObjects.glUseProgramObjectARB(0);
	}
	@Override
	public void Bind() {
		Matrix4f out = new Matrix4f();
		Bind(out);
		
	}
}
