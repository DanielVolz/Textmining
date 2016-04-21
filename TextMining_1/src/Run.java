import de.textmining.data.AllWorks;
import de.textmining.data.Speaker;

import java.io.File;

/**
 * Created by danielvolz on 12.04.16.
 */
public class Run {

    public static void main(String[] args) {
        File dir = new File("/Users/danielvolz/Documents/SS16/TextMining/");

        AllWorks allWorks;
        Parser p = new Parser();

        //call function to list directory
        allWorks = p.readFiles(dir);

        for (Speaker speaker : allWorks.getAllSpeakers()) {
            System.out.println(speaker.getName() + ": " + speaker.getNumberOfMonologues() + " times, " + speaker.getNurmberOfWords() + " words, " + speaker.getNurmberOfWords() / speaker.getNumberOfMonologues() + " words per monologue.");
        }

//        for (de.textmining.data.Work w : allWorks.getAll()) {
//            System.out.println(w.getMonologuesByScene(1, 1) + "\n");
//        }


    }
}
