/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author kai
 */
public class Work {

    private String workName = "";
    private List<Monolog> monologues = new ArrayList<>();
    private Map<Speaker, List<Monolog>> bySpeaker = new HashMap<>();

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public List<Monolog> getMonologuesByScene(int act, int scene) {
        List<Monolog> res = new ArrayList<>();
        for (Monolog m : monologues
                ) {
            if (m.getActNumber() == act && m.getSceneNumber() == scene)
                res.add(m);
        }

        return res;
    }


    public List<Monolog> getMonologuesByScene(String scene) {
        List<Monolog> res = new ArrayList<>();
        for (Monolog m : monologues
                ) {
            if (m.getSceneName().equals(scene))
                res.add(m);
        }

        return res;
    }

    public List<Monolog> getMonologuesByScene(int scene) {
        List<Monolog> res = new ArrayList<>();
        for (Monolog m : monologues
                ) {
            if (m.getSceneNumber() == scene)
                res.add(m);
        }

        return res;
    }


    public List<Monolog> getMonologuesByAct(String act) {
        List<Monolog> res = new ArrayList<>();
        for (Monolog m : monologues
                ) {
            if (m.getActName().equals(act))
                res.add(m);
        }

        return res;
    }

    public List<Monolog> getMonologuesByAct(int act) {
        List<Monolog> res = new ArrayList<>();
        for (Monolog m : monologues
                ) {
            if (m.getActNumber() == act)
                res.add(m);
        }

        return res;
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

    @Override
    public String toString() {
        return "Work{" +
                "workName='" + workName + '\'' +
                '}';
    }


}
