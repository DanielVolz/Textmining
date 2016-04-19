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

        String actName = null;
        String sceneName = null;
        String sprecherTag = null;
        String workName = null;

        int scene = 0;
        int act = 0;

        if (!file.getAbsolutePath().contains(ignore)) {
            try {
                //System.out.println(file);
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

        for (String line : ganzerText.split("\n")) {

            String workNameReg = "<\\s[A-z]+\\s--\\s([A-Z\\s]+)>";
            String actNameReg = "<(\\bACT\\b.+)>";
            String sceneNameReg = "<((\\bSCENE\\b)\\s(.+))>";
            String tagNameReg = "<(/?)([A-Z.\\s\\d]+)>";
            //String sprechertext = "(\\t.[A-Za-z].+)";

            Pattern workNamePattern = Pattern.compile(workNameReg);
            Pattern actNamePattern = Pattern.compile(actNameReg);
            Pattern sceneNamePattern = Pattern.compile(sceneNameReg);
            Pattern tagNamePattern = Pattern.compile(tagNameReg);
            //Pattern sprecherTextPattern = Pattern.compile(sprechertext);
            Pattern r = Pattern.compile("^<(/?)([^/>a-z]+)>");

            Matcher m9 = workNamePattern.matcher(line);
            Matcher m8 = r.matcher(line);
            Matcher m6 = actNamePattern.matcher(line);
            Matcher m7 = sceneNamePattern.matcher(line);
            Matcher m4 = tagNamePattern.matcher(line);
            // Matcher m5 = sprecherTextPattern.matcher(line);

            if (m9.find()) {
                workName = m9.group(1);
            }
            //suche nach offenen tags
            if (m8.find() && !m8.group(1).contains("/")) {


                //wenn acttag gefunden, speicher ihn ab
                if (m6.find()) {
                    actName = m6.group(1);
                    act++;

                    //wenn scenetag gefunden speicher ihn ab
                } else if (m7.find()) {
                    sceneName = m7.group(1);
                    scene = Integer.parseInt(m7.group(3));

                    //Wenn sprecher tag gefunden speicher ihn ab
                } else if (m4.find()) {
                    sprecherTag = m4.group(2);
                }


                //suche nach tabulatoren am anfang des textes = text des sprechers. wenn gefunden speicher sie im stringbuffer
            } else if (line.startsWith("\t")) {
                tmp.append(line.replaceFirst("\t", "")).append("\n");

                //wenn close tag gefunden und es kein act oder szene endtag ist, dann dialog zu ende. speicher alle infos der szene in Monolog
            } else if (m4.find() && m4.group(1).contains("/") && !m4.group(0).contains("ACT") && !m4.group(0).contains("SCENE")) {

                Monolog mon = new Monolog();

                speakers.putIfAbsent(sprecherTag, new Speaker(sprecherTag, work));
                mon.setSprecher(speakers.get(sprecherTag));

                mon.setSceneName(sceneName);
                mon.setSceneNumber(scene);
                mon.setActName(actName);
                mon.setActNumber(act);
                mon.setMonologText(tmp.toString());
                work.setWorkName(workName);
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
            if (file.isDirectory()) {

                //Recursively call file list function on the new directory

                //WIE FUNTIONIERT DAS???????????????????????????????????????
                System.out.printf(file.getName());


                //ignoriere test ordner
                if (!file.getName().contains("test")) {
                    allwork.addAll(readFiles(file));
                }

            }
            else {
                allwork.add(readFile(file));
            }
        }
        return allwork;
    }

}
