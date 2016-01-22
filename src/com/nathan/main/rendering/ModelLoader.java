package com.nathan.main.rendering;

public class ModelLoader
{
    private List<Vector3f> verticies = new ArrayList<Vector3f>();
    private List<Vector3f> normals = new ArrayList<Vector3f>();
    private List<Vector2f> texverts = new ArrayList<Vector2f>();
    private List<Face> faces = new ArrayList<Face>();
public ModelLoader(String i,VBO iv)
{
  BufferedReader reader = null;
  try
  { 
   reader = new BufferedReader(new FileReader(path));
  }
  catch(FileNotFoundException e) {e.printStackTrace();}
  String line;
   try 
   {
    while((line = reader.readLine())!=null)
    {
       if(line.startsWith("v "))
                   {
                       float x,y,z;
                       x=Float.valueOf(line.split(" ")[1]);
                       y=Float.valueOf(line.split(" ")[2]);
                       z=Float.valueOf(line.split(" ")[3]);
                       verticies.add(new Vector3f(x, y, z));
                   }
                   else  if(line.startsWith("vn "))
                   {
                       float x,y,z;
                       x=Float.valueOf(line.split(" ")[1]);
                       y=Float.valueOf(line.split(" ")[2]);
                       z=Float.valueOf(line.split(" ")[1]);
                       normals.add(new Vector3f(x, y, z));
                   }
                   else  if(line.startsWith("f "))
                   {
                       float x,y,z;
                       x=Float.valueOf(line.split(" ")[1].split("/")[0]);
                       y=Float.valueOf(line.split(" ")[2].split("/")[0]);
                       z=Float.valueOf(line.split(" ")[3].split("/")[0]);
                       Vector3f vertindex = new Vector3f(x, y, z);
                       Vector3f text = null;
                       if(tex!=null)
                       {
 
                       x=Float.valueOf(line.split(" ")[1].split("/")[1]);
 
                       y=Float.valueOf(line.split(" ")[2].split("/")[1]);
 
                       z=Float.valueOf(line.split(" ")[3].split("/")[1]);
 
                      text = new Vector3f(x, y, z);
                       }
                       x=Float.valueOf(line.split(" ")[1].split("/")[2]);
                       y=Float.valueOf(line.split(" ")[2].split("/")[2]);
                       z=Float.valueOf(line.split(" ")[3].split("/")[2]);
                       Vector3f normindex = new Vector3f(x, y, z);
                       if(tex!=null)
                       {
                           Face t = new Face(vertindex,normindex,text);
                           faces.add(t);
                       }
                       else
                       {
                           Face t =new Face(vertindex,normindex);
                           faces.add(t);
                       }
 
                   }
                   else  if(line.startsWith("vt ") && tex!=null)
                   {
                       float x,y;
                       x=Float.valueOf(line.split(" ")[1]);
                       y=Float.valueOf(line.split(" ")[2]);
                       Vector2f texface = new Vector2f(x, -y);
                       texverts.add(texface);
                   }
    }
    }catch (IOException e) {e.printStackTrace();}
    try 
    {
    reader.close();
    } catch (IOException e) {e.printStackTrace();}
    For (Face face : faces) 
    {
        Color c = new Color((float) Math.random(),(float) Math.random(),(float) Math.random());
        Vector3f v1v =  verticies.get((int) (face.vertex.x - 1))
        Vector3f v2v =  verticies.get((int) (face.vertex.y - 1))
        Vector3f v3v =  verticies.get((int) (face.vertex.z - 1))
        Vector2f t1 = texverts.get((int)(face.texture.x-1));
        Vector2f t2 = texverts.get((int)(face.texture.y-1));
        Vector2f t3 = texverts.get((int)(face.texture.z-1));
        Vertex v1 = new Vertex(); v1.setXYZ(v1v.x,v1v.y,v1v.z); v1.setRGB(c.getRed()/255f,c.getGreen()/255f,c.getBlue()/255f);v1.setST(t1.x,t1.y);
        Vertex v2 = new Vertex(); v1.setXYZ(v2v.x,v2v.y,v2v.z); v2.setRGB(c.getRed()/255f,c.getGreen()/255f,c.getBlue()/255f);v2.setST(t2.x,t2.y);
        Vertex v3 = new Vertex(); v1.setXYZ(v3v.x,v3v.y,v3v.z); v3.setRGB(c.getRed()/255f,c.getGreen()/255f,c.getBlue()/255f);v3.setST(t3.x,t3.y);
        iv.addVertex(v1);
        iv.addVertex(v2);
        iv.addVertex(v3);
    }
}

}
