   package com.nathan.main.rendering;
import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
public class FBO
{
  private int fboId;
  private int fbotexId;
  private int fboDepthId;
  private int[] Dim=new int[2];
  private quad Quad = new quad();
  public  DefaultShader shader;
  public FBO(String frag,String vert)
  {
  this(512,512,frag,vert);
  }
  public FBO(int i,String v,String f) 
  {
  this(i,i,v,f);
  }
  public FBO(int x,int y,String vert,String frag) 
  {
	Dim=new int[]{x,y};
	shader = new DefaultShader(vert,frag);
	init();
  }
  private void init()
  {
	  fboId = glGenFramebuffersEXT();                                         // create a new framebuffer
	  fbotexId = glGenTextures();                                               // and a new texture used as a color buffer
	  fboDepthId = glGenRenderbuffersEXT();                                  // And finally a new depthbuffer
	  glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboId);  
	  
	  // initialize color texture
	  glBindTexture(GL_TEXTURE_2D, fbotexId);                                   // Bind the colorbuffer texture
	  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);               // make it linear filterd
	  glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, Dim[0], Dim[1], 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);  // Create the texture data
	  glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, fbotexId, 0); // attach it to the framebuffer
	     
	     
	  // initialize depth renderbuffer
	  glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, fboDepthId);                // bind the depth renderbuffer
	  glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL14.GL_DEPTH_COMPONENT24, Dim[0], Dim[1]); // get the data space for it
	  glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, fboDepthId);
	  glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);     
  }
  
public void bindFBO()
  {
                                         // set The Current Viewport to the fbo size
        glBindTexture(GL_TEXTURE_2D, 0);                                // unlink textures because if we dont it all is gonna fail
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboId);  
        glViewport (0, 0, Dim[0], Dim[1]);   // switch to rendering on our FBO
        glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
        //GL11.glClearColor(0.5f,0.5f,0.5f,0f);
  
  }

  public void unbindFBO()
  {
    glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0); 
  }
  public void bindFboTexture()
  {
    glBindTexture(GL_TEXTURE_2D, fbotexId);
  }
    public void inbindFboTexture()
  {
   glBindTexture(GL_TEXTURE_2D, 0);
  }
	public int getTextureId() {
		return fbotexId;
	}
	public int getWidth() {
		return Dim[0];
	}
	public int getHeight() {
		return Dim[1];
	}
	public void render(){render(true);}
	public void render(boolean shd)
	{
		if(shd)
		{
		shader.Bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbotexId);
		shader.setTex("texture_diffuse", 0);
		}
		Quad.render();
		if(shd)
		shader.unBind();
	}
}
class quad
{
	private int vaoId = 0;
    private int vboId = 0;
    private int vboiId = 0;
    private int indicesCount = 0;
	public quad()
	{
		// We'll define our quad using 4 vertices of the custom 'Vertex' class
        float w = (float)OpenGL.Width/(float)OpenGL.Height;w=0.5767f;
        float h = (float)OpenGL.Height/(float)OpenGL.Width;h=0.5769f;
        Vertex v0 = new Vertex(); 
        v0.setXYZ(-w, h, 0); v0.setRGB(1, 0, 0); v0.setST(0, 0);
        Vertex v1 = new Vertex(); 
        v1.setXYZ(-w, -h, 0); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        Vertex v2 = new Vertex(); 
        v2.setXYZ(w, -h, 0); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        Vertex v3 = new Vertex(); 
        v3.setXYZ(w,h, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);
         
        Vertex[] vertices = new Vertex[] {v0, v1, v2, v3};
        // Put each 'Vertex' in one FloatBuffer
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length *
                Vertex.elementCount);
        for (int i = 0; i < vertices.length; i++) {
            // Add position, color and texture floats to the buffer
            verticesBuffer.put(vertices[i].getElements());
        }
        verticesBuffer.flip();  
        // OpenGL expects to draw vertices in counter clockwise order by default
        byte[] indices = {
                0, 1, 2,
                2, 3, 0
        };
        indicesCount = indices.length;
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
         
        // Create a new Vertex Array Object in memory and select it (bind)
        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
         
        // Create a new Vertex Buffer Object in memory and select it (bind)
        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
         
        // Put the position coordinates in attribute list 0
        GL20.glVertexAttribPointer(0, Vertex.positionElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.positionByteOffset);
        // Put the color components in attribute list 1
        GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.colorByteOffset);
        // Put the texture coordinates in attribute list 2
        GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.textureByteOffset);
         
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
         
        // Deselect (bind to 0) the VAO
        GL30.glBindVertexArray(0);
         
        // Create a new VBO for the indices and select it (bind) - INDICES
        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	public void render(int w,int h)
	{
		
	}
	public void render()
	{

         
        // Bind to the VAO that has all the information about the vertices
        GL30.glBindVertexArray(vaoId);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
         
        // Bind to the index VBO that has all the information about the order of the vertices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
         
        // Draw the vertices
        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);
         
        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
         
	}
}
