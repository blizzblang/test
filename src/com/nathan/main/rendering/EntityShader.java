package com.nathan.main.rendering;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class EntityShader extends Shader
{

	public EntityShader() 
	{
		super("GameFiles/Shaders/Basic/EntityShader.vert","GameFiles/Shaders/Basic/EntityShader.frag");
		init();
	}
	@Override
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
	        // Color information will be attribute 1
	        GL20.glBindAttribLocation(ShaderId, 1, "in_Color");
	        // Textute information will be attribute 2
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
	}
	@Override
	public void Bind() 
	{
		 ARBShaderObjects.glUseProgramObjectARB(ShaderId);
		
	}

	@Override
	public void unBind() 
	{
		 ARBShaderObjects.glUseProgramObjectARB(0);
		
	}

}
