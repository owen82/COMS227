package lab9;
import java.io.File;
import java.util.ArrayList;

public class findFiles
{
   private static ArrayList<String> findFiles(File file, ArrayList<String> list) 
   {
      if (!file.isDirectory()) 
      {
         if(file.isFile()) 
         {
            list.add(file.getName());
         }
         return list;
      }
      else
      {
         for (File f : file.listFiles())
         {
            findFiles(f, list);
         }
         
         return list;
      }
   }
}