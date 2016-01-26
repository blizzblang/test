package com.nathan.rendering.shaders;

public class ShadowShader extends Shader
{
  public void ShadowShader()
  {
   super("/GameFiles/Shaders/ShadowMapping/ShadowMapping.vert,"/GameFiles/Shaders/ShadowMapping/ShadowRender.frag)
   init();
  }
  public void bind(){}
  public void unbind(){}
}
