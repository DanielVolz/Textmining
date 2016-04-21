package de.textmining.data;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 * @author kai
 */
public class Scene {

    private String sceneName = "";
    private int sceneNumber = 0;
    private List<Monolog> monologues = new ArrayList<>();
    private Map<Speaker, List<Monolog>> monologsBySpeaker = new HashMap<>();


    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public int getSceneNumber() {
        return sceneNumber;
    }

    public void setSceneNumber(int sceneNumber) {
        this.sceneNumber = sceneNumber;
    }

    public List<Speaker> getSpeaker() {
        List<Speaker> res = new ArrayList<>();
        res.addAll(monologsBySpeaker.keySet());

        Collections.sort(res,
                (name1, name2) -> monologsBySpeaker.get(name2).size() - monologsBySpeaker.get(name1).size()
        );
        return res;
    }

    public void add(Monolog m) {
        monologues.add(m);
        monologsBySpeaker.putIfAbsent(m.getSprecher(), new ArrayList<>());
        monologsBySpeaker.get(m.getSprecher()).add(m);
    }

    public int getWordsBySpeaker(Speaker speaker) {
        int sum = 0;
        for (Monolog m : monologsBySpeaker.get(speaker)) {
            sum += m.getMonologText().split(" ").length;
        }
        return sum;
    }

    public int getNumberOfMonologuesBySpeaker(Speaker speaker) {
        return monologsBySpeaker.get(speaker).size();
    }

    @Override
    public String toString() {
        return "de.textmining.data.Work{" +
                "workName='" + sceneName + '\'' +
                '}';
    }


}
