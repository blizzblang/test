package com.nathan.main.rendering;

public class ModelLoader
{
    private List<Vector3f> verticies = new ArrayList<Vector3f>();
    private List<Vector3f> normals = new ArrayList<Vector3f>();
    private List<Vector2f> texverts = new ArrayList<Vector2f>();
    private List<Face> faces = new ArrayList<Face>();
public ModelLoader(String i)
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
 
}

}
