import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Work readFile(File file) {
        String ignore = ".DS_Store";

        Work work = new Work();
        StringBuilder builder = new StringBuilder();
        Map<String, Speaker> speakers = new HashMap<>();

        if (!file.getAbsolutePath().contains(ignore)) {
            try {
                System.out.println(file);
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
        String actNameFound="";
        String sceneNameFound="";
        String sprecherTagFound="";
        for (String line : ganzerText.split("\n")) {

            String actName = "<(\\bACT\\b.+)>";
            String sceneName = "<(\\bSCENE\\b.+)>";
            String tagName = "<(/?)([A-Z.\\s\\d]+)>";
            //String sprechertext = "(\\t.[A-Za-z].+)";

            Pattern actNamePattern = Pattern.compile(actName);
            Pattern sceneNamePattern = Pattern.compile(sceneName);
            Pattern tagNamePattern = Pattern.compile(tagName);
            //Pattern sprecherTextPattern = Pattern.compile(sprechertext);
            Pattern r = Pattern.compile("^<(/?)([^/>a-z]+)>");

            Matcher m8 = r.matcher(line);
            Matcher m6 = actNamePattern.matcher(line);
            Matcher m7 = sceneNamePattern.matcher(line);
            Matcher m4 = tagNamePattern.matcher(line);
            // Matcher m5 = sprecherTextPattern.matcher(line);

            //suche nach offenen tags
            if (m8.find() && !m8.group(1).contains("/")) {


                //wenn acttag gefunden, speicher ihn ab
                if (m6.find()) {
                    actNameFound = m6.group(1);

                    //wenn scenetag gefunden speicher ihn ab
                } else if (m7.find()) {
                    sceneNameFound = m7.group(1);

                    //Wenn sprecher tag gefunden speicher ihn ab
                } else if (m4.find()) {
                    sprecherTagFound = m4.group(2);
                }


                //suche nach tabulatoren am anfang des textes, wenn gedunfen speicher sie im stringbuffer
            } else if(line.startsWith("\t")) {
                tmp.append(line.replaceFirst("\t", "")).append("\n");

                //wenn close tag gefunden, dann dialog zu ende. speicher alle infos der szene in Monolog
            } else if (m4.find() && m4.group(1).contains("/") && !m4.group(0).contains("ACT") && !m4.group(0).contains("SCENE")) {
                // System.out.println("act= "+ actNameFound+" scene="+sceneNameFound+" sprecher="+sprecherTagFound+" text =" +tmp);
                Monolog mon = new Monolog();

                speakers.putIfAbsent(sprecherTagFound, new Speaker(sprecherTagFound, work));
                mon.setSprecher(speakers.get(sprecherTagFound));

                mon.setSceneName(sceneNameFound);
                mon.setActName(actNameFound);
                mon.setMonologText(tmp.toString());
                //col.add(mon);
                work.add(mon);
                tmp.setLength(0);


            }

        }

    return work;
}

    public AllWorks readFiles(File dir) {
    	String ignore = ".DS_Store";
        AllWorks allwork = new AllWorks();

        //Get list of all files and folders in directory
        File[] files = dir.listFiles();

        //For all files and folders in directory
        for (File file : files) {
            //Check if directory
            if (file.isDirectory())
                //Recursively call file list function on the new directory

                //WIE FUNTIONIERT DAS???????????????????????????????????????

                allwork.addAll(readFiles(file));
            else {
                allwork.add(readFile(file));
            }
        }
       return allwork;
    }

}
