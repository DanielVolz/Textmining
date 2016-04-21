package de.textmining.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by danielvolz on 14.04.16.
 */
public class AllWorks {
    private List<Work> works = new ArrayList<>();



    public List<Work> getAll() {
        return works;
    }

    public void add(Work work) {
        works.add(work);
    }

    public void addAll(AllWorks aw) {
        works.addAll(aw.getAll());
    }

    public List<Speaker> getAllSpeakers() {
        List<Speaker> res = new ArrayList<>();
        for (Work w: works) {
            res.addAll(w.getAllSpeaker());
        }
        Collections.sort(res, (speaker1, speaker2)
                -> speaker1.getNumberOfMonologues() - speaker2.getNumberOfMonologues()
        );
        return res;
    }
}
