import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecursiveFileList {

    public static void main(String[] args) {
        String dirToRecurse = "/Users/danielvolz/Documents/SS16/TextMining/";

        RecursiveFileList rfl = new RecursiveFileList();

        //call function to list directory
        rfl.fileList(new File(dirToRecurse));
    }

    private void fileList(File dir) {
    	String ignore = ".DS_Store";

        StringBuilder builder = new StringBuilder();

        //Get list of all files and folders in directory
        File[] files = dir.listFiles();

        //For all files and folders in directory
        assert files != null;
        for (File file : files) {
            //Check if directory
            if (file.isDirectory())
                //Recursively call file list function on the new directory
                fileList(file);
            else {
                //If not directory, print the file path
                System.out.println(file.getAbsolutePath());

                if (!file.getAbsolutePath().contains(ignore)) {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                String ganzerText = builder.toString();

                String stageDir = "<STAGE DIR>[\\n\\w\\W\\s]*?</STAGE DIR>";
                Pattern stagedirPattern = Pattern.compile(stageDir);
                Matcher m3 = stagedirPattern.matcher(ganzerText);
                ganzerText = m3.replaceAll("\t");

                StringBuffer tmp = new StringBuffer();
                Monolog mon = new Monolog();
                ArrayList<Monolog> col = new ArrayList<>();

                for (String line : ganzerText.split("\n")) {
                    String actName = "<(\\bACT\\b.+)>";
                    String sceneName = "<(\\bSCENE\\b.+)>";
                    String tagName = "<(/?)([A-Z.\\s\\d]+)>";
                    String sprechertext = "(\\t.[A-Za-z].+)";



                    Pattern actNamePattern = Pattern.compile(actName);
                    Pattern sceneNamePattern = Pattern.compile(sceneName);
                    Pattern tagNamePattern = Pattern.compile(tagName);
                    Pattern sprecherTextPattern = Pattern.compile(sprechertext);
                    Pattern r = Pattern.compile("^<(/?)([^/>a-z]+)>");

                    Matcher m8 = r.matcher(line);
                    Matcher m6 = actNamePattern.matcher(line);
                    Matcher m7 = sceneNamePattern.matcher(line);
                    Matcher m4 = tagNamePattern.matcher(line);
                    Matcher m5 = sprecherTextPattern.matcher(line);

                    if (m8.find() && !m8.group(1).contains("/")) {
                        //System.out.println(m8.group(0));



                        if (m6.find()) {
                            String actNameFound = m6.group(1);
                            System.out.println("Actname gefunden " + actNameFound);
                        } else if (m7.find()) {
                            String sceneNameFound = m7.group(1);
                            System.out.println("Scenenname gefunden " + sceneNameFound);

                        } else if (m4.find()) {
                            String sprecherTagFound = m4.group(2);
                            System.out.println("Sprecher gefunden " + sprecherTagFound);
                        }



                    } else if(line.startsWith("\t")) {
                        tmp.append(line.replaceFirst("\t", "")).append("\n");

                    } else if (m4.find() && m4.group(1).contains("/")) {
                            System.out.println(tmp);
                            tmp.setLength(0);


                    }

                }

            }
        }
    }
}
