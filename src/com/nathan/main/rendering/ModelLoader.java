package com.nathan.main.rendering;

public class ModelLoader
{
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
      
    }
   }catch (IOException e) {e.printStackTrace();}
 
}

}
