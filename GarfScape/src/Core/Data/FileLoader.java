package Core.Data;

import java.io.File;



public class FileLoader 
{
    private static final String resourceRoot = "res/";
    
    public static File loadFile(String fileName)
    {
        return new File(resourceRoot + fileName);
    }
}
