package GUI;

import java.io.File;
import javax.swing.filechooser.*;

public class XMLFilter extends FileFilter 
{
    final static String xml = "xml";

    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) 
        {
            String extension = s.substring(i+1).toLowerCase();
            if (xml.equals(extension)) return true;
            else return false; 
        }

        return false;
    }
    
    public String getDescription() 
    {
        return "Archivos XML";
    }
}
