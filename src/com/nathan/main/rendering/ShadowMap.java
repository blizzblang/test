package com.nathan.main.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.nathan.main.Main;

public class ShadowMap 
{
	public FBO OcclusionMap;
	FBO ShadowMapLookupTexture;
	int Width=0;
	int Height=0;
	public ShadowMap(int Depth)
	{
		Depth = (int) Math.pow(2, Depth);
		Width=Depth+0;
		Height=Depth+0;
		OcclusionMap = new FBO(Depth,"GameFiles/Shaders/Shadow/vert.vert", "GameFiles/Shaders/Shadow/mapper.frag" );
		ShadowMapLookupTexture = new FBO(Width,1,"GameFiles/Shaders/Shadow/vert.vert", "GameFiles/Shaders/Shadow/ray.frag" );
	}
	public void renderToOcclusionMap()
	{
		OcclusionMap.bindFBO();
	}
	public void finishOcclusionMap()
	{
		OcclusionMap.unbindFBO();
		UpdateShadowTex();
	}
	private void UpdateShadowTex()
	{
		Matrix4f pos = new Matrix4f();
		pos.translate(new Vector3f(0,0,-0.999f));
		
		ShadowMapLookupTexture.bindFBO();
		OcclusionMap.shader.Bind(null,new Matrix4f(),pos);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getOcclusionTex());
		OcclusionMap.shader.setUniformf("resolution", Width, Height);
		OcclusionMap.shader.setTex("u_texture", 0);
		OcclusionMap.render(false);
		OcclusionMap.shader.unBind();
		ShadowMapLookupTexture.unbindFBO();
	}
	public void render()
	{
		Matrix4f pos = new Matrix4f();
		pos.translate(new Vector3f(0,0,-1));
		ShadowMapLookupTexture.shader.Bind(null,new Matrix4f(),pos);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getShadowTex());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getOcclusionTex());
		ShadowMapLookupTexture.shader.setUniformf("resolution", Width, Height);
		ShadowMapLookupTexture.shader.setTex("u_texture", 0);
		ShadowMapLookupTexture.shader.setTex("original", 1);
		ShadowMapLookupTexture.render(false);
		ShadowMapLookupTexture.shader.unBind();
	}
	public int getOcclusionTex()
	{
		return OcclusionMap.getTextureId();
	}
	public int getShadowTex()
	{
		return ShadowMapLookupTexture.getTextureId();
	}
}
