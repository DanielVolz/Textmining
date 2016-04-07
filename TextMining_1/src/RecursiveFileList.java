import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
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
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF-16"));
                        String line = null;
                        
                        while((line = reader.readLine()) != null)
                        {
                        	String patternOpenTag = "<([^/>a-z]+)>";
                        	String patternClosedTag = "</([^>]+)>";
                        	
                        	
                        	Pattern r = Pattern.compile(patternOpenTag);
                        	Pattern r2 = Pattern.compile(patternClosedTag);
                        	line = reader.readLine();  
                        	Matcher m = r.matcher(line);
                        	Matcher m2 = r2.matcher(line);
                                                  	 
                            if (m.find()) {
								System.out.println(m2.group(1));
							}
                          
                        }
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }
    }
}