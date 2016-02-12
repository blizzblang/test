#version 150 core

attribute vec4 in_Position;
attribute vec4 in_Color;
attribute vec2 in_TextureCoord;


uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

varying vec2 vTexCoord0;
varying vec4 vColor;

void main() {
  vColor = in_Color;
  vTexCoord0 = in_TextureCoord;
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * in_Position;
}