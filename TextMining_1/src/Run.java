import java.io.File;

/**
 * Created by danielvolz on 12.04.16.
 */
public class Run {

    public static void main(String[] args) {
        File dir = new File("/Users/danielvolz/Documents/SS16/TextMining/");

        AllWorks allWorks = new AllWorks();
        Parser p = new Parser();

        //call function to list directory
        allWorks = p.readFiles(dir);

        for (Speaker speaker: allWorks.) {
            System.out.println(name + ": " + work.getNumberOfMonologuesBySpeaker(name) + " times, " + work.getWordsBySpeaker(name) + " words, " + work.getWordsBySpeaker(name)/work.getNumberOfMonologuesBySpeaker(name) + " words per monologue.");
        }


    }
}
