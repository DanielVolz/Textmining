/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kai
 */
public class Work {
    private List<Monolog> monologues = new ArrayList<>();
    private Map<String, List<Monolog>> bySpeaker = new HashMap<>();

    public Work() {
    }

    public List<String> getSpeaker() {
        List<String> res = new ArrayList<>();
        for (String name: bySpeaker.keySet()) {
            res.add(name);
        }
        Collections.sort(res, new Comparator<String>(){
            @Override
            public int compare(String name1, String name2) {
                return bySpeaker.get(name2).size() - bySpeaker.get(name1).size();
            }
        });
        return res;
    }

    public void add(Monolog m) {
        monologues.add(m);
        bySpeaker.putIfAbsent(m.getSprecher(), new ArrayList<>());
        bySpeaker.get(m.getSprecher()).add(m);
    }

    public int getWordsBySpeaker(String name) {
        int sum = 0;
        for (Monolog m: bySpeaker.get(name)) {
            sum += m.getMonologText().split(" ").length;
        }
        return sum;
    }

    public int getNumberOfMonologuesBySpeaker(String name) {
        return bySpeaker.get(name).size();
    }

}