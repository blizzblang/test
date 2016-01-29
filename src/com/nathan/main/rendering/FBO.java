package com.nathan.main.rendering;

public class FBO
{
  private int fboId;
  private int fbotexId;
  private int fboDepthId;
  DefaultShader bound;
  public FBO(DefaultShader in)
  {
  bound = in;
  fboId = glGenFramebuffersEXT();                                         // create a new framebuffer
  fbotexId = glGenTextures();                                               // and a new texture used as a color buffer
  fboDepthId = glGenRenderbuffersEXT();                                  // And finally a new depthbuffer
  glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboId);  
  
  // initialize color texture
  glBindTexture(GL_TEXTURE_2D, fbotexId);                                   // Bind the colorbuffer texture
  glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);               // make it linear filterd
  glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, 512, 512, 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);  // Create the texture data
  glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, fbotexId, 0); // attach it to the framebuffer
     
     
  // initialize depth renderbuffer
  glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, fboDepthId);                // bind the depth renderbuffer
  glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL14.GL_DEPTH_COMPONENT24, 512, 512); // get the data space for it
  glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, fboDepthId);
  glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);                 
  }
  public void bindFBO()
  {
        glViewport (0, 0, 512, 512);                                    // set The Current Viewport to the fbo size
        glBindTexture(GL_TEXTURE_2D, 0);                                // unlink textures because if we dont it all is gonna fail
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fboId);        // switch to rendering on our FBO
        glClearColor (1.0f, 0.0f, 0.0f, 0.5f);
        glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);            // Clear Screen And Depth Buffer on the fbo to red
        glLoadIdentity ();   
  }
  public void unbindFBO()
  {
    glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0); 
  }
  public void bindFboTexture()
  {
    glBindTexture(GL_TEXTURE_2D, colorTextureID);
  }
    public void inbindFboTexture()
  {
   glBindTexture(GL_TEXTURE_2D, 0);
  }
}
