/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kai
 */
public class Work {
    private List<Monolog> monologues = new ArrayList<>();
    private Map<Speaker, List<Monolog>> bySpeaker = new HashMap<>();

    public Work() {
    }



    public List<Speaker> getSpeaker() {
        List<Speaker> res = new ArrayList<>();
        res.addAll(bySpeaker.keySet());

        Collections.sort(res,
                (name1, name2) -> bySpeaker.get(name2).size() - bySpeaker.get(name1).size()
        );
        return res;
    }

    public void add(Monolog m) {
        monologues.add(m);
        bySpeaker.putIfAbsent(m.getSprecher(), new ArrayList<>());
        bySpeaker.get(m.getSprecher()).add(m);
    }

    public int getWordsBySpeaker(Speaker speaker) {
        int sum = 0;
        for (Monolog m: bySpeaker.get(speaker)) {
            sum += m.getMonologText().split(" ").length;
        }
        return sum;
    }

    public int getNumberOfMonologuesBySpeaker(Speaker speaker) {
        return bySpeaker.get(speaker).size();
    }

}
