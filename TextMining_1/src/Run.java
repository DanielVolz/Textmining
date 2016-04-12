import java.io.File;

/**
 * Created by danielvolz on 12.04.16.
 */
public class Run {

    public static void main(String[] args) {
        String dirToRecurse = "/Users/danielvolz/Documents/SS16/TextMining/";
        Work work = null;
        RecursiveFileList rfl = new RecursiveFileList();

        //call function to list directory
        work = rfl.fileList(new File(dirToRecurse));

        for (String name: work.getSpeaker()) {
            System.out.println(name + ": " + work.getNumberOfMonologuesBySpeaker(name) + " times, " + work.getWordsBySpeaker(name) + " words, " + work.getWordsBySpeaker(name)/work.getNumberOfMonologuesBySpeaker(name) + " words per monologue.");
        }


    }
}
