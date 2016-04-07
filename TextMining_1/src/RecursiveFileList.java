import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
public class RecursiveFileList {
 
    /**
     * Java tutorial to list all files in a directory and all sub-directories
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) {
        String dirToRecurse = "/Users/danielvolz/Documents/SS16/TextMining/";
        
        RecursiveFileList rfl = new RecursiveFileList();
        
        //call function to list directory
        rfl.fileList(new File(dirToRecurse));
    }
    
    private void fileList(File dir) {
    	String ignore = ".DS_Store";
    	
        //Get list of all files and folders in directory
        File[] files = dir.listFiles();
        
        //For all files and folders in directory
        for(int i=0; i<files.length; i++){
            //Check if directory
            if(files[i].isDirectory())
                //Recursively call file list function on the new directory
                fileList(files[i]);
            else{
                //If not directory, print the file path
                System.out.println(files[i].getAbsolutePath());
                BufferedReader reader = null;
                if (!files[i].getAbsolutePath().contains(ignore)) {
                	try {
                        reader = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
                        String line = null;

                        while(true)
                        {
                            line = reader.readLine();
                            if(line == null)
                                break;

                            System.out.println(line);
                        }
                    }catch(Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(reader != null)
                        {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                
            }
        }
    }
}