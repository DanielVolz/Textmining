package de.textmining.data;

/**
 * Created by danielvolz on 14.04.16.
 */
public class Speaker {

    //TODO: anstatt scene muss der speaker ein work bekommen, damit es funkioniert (glaube ich mal)
    private String name;
    private Scene scene;


    public Speaker(String name, Scene scene) {
        this.name = name;
        this.scene = scene;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfMonologues() {
        return scene.getNumberOfMonologuesBySpeaker(this);
    }

    public int getNurmberOfWords() {
        return scene.getWordsBySpeaker(this);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", scene=" + scene +
                '}';
    }
}
