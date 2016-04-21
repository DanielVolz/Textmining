package de.textmining.data;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kai
 */
public class Work {

    private String workName = "";
    private List<Act> acts = new ArrayList<>();


    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public List<Act> getAll() {
        return acts;
    }

    public void add(Act act) {
        acts.add(act);
    }

    public void addAll(Work w) {
        acts.addAll(w.getAll());
    }

    public List<Speaker> getAllSpeaker() {
        List<Speaker> res = new ArrayList<>();
        for (Act a : acts) {
            res.addAll(a.getAllSpeaker());
        }
        Collections.sort(res, (speaker1, speaker2)
                -> speaker1.getNumberOfMonologues() - speaker2.getNumberOfMonologues()
        );
        return res;
    }

    public int getWordsBySpeaker(Speaker speaker) {
        int sum = 0;
        ArrayList<Scene> tmp = new ArrayList<>();
        for (Act a : acts) {

            for (Scene s : a.getScene()
                    ) {
                sum += s.getNumberOfMonologuesBySpeaker(speaker);
            }
        }
        return sum;
    }


    //public int getNumberOfMonologuesBySpeaker(Speaker speaker) {
    // return sceneBySpeaker.get(speaker).size();
    // }

}
