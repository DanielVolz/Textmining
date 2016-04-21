package de.textmining.data;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author kai
 */
public class Act {

    private String actName = "";
    private int actNumber = 0;


    private ArrayList<Scene> scene = new ArrayList<>();

    public Act() {
    }

    public Act(String actName, Scene s) {
        this.actName = actName;
        this.scene.add(s);
    }

    public ArrayList<Scene> getScene() {
        return scene;
    }

    public void setScene(ArrayList<Scene> scene) {
        this.scene = scene;
    }

    public void add(Scene s) {
        scene.add(s);
    }


    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public int getActNumber() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public ArrayList<Speaker> getAllSpeaker() {
        ArrayList<Speaker> res = new ArrayList<>();
        for (Scene s : scene
                ) {
            res.addAll(s.getSpeaker());
        }
        Collections.sort(res, (speaker1, speaker2) -> speaker1.getNumberOfMonologues() - speaker2.getNumberOfMonologues());
        return res;
    }

}
