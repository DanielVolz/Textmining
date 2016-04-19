/**
 * Created by danielvolz on 11.04.16.
 */
public class Monolog {


    private Speaker sprecher;
    private String monologText;
    private String actName;
    private String sceneName;
    private int actNumber;
    private int sceneNumber;

    public int getActNumber() {
        return actNumber;
    }

    public void setActNumber(int actNumber) {
        this.actNumber = actNumber;
    }

    public int getSceneNumber() {
        return sceneNumber;
    }

    public void setSceneNumber(int sceneNumber) {
        this.sceneNumber = sceneNumber;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getMonologText() {
        return monologText;
    }

    public void setMonologText(String monologText) {
        this.monologText = monologText;
    }

    public Speaker getSprecher() {
        return sprecher;
    }

    public void setSprecher(Speaker sprecher) {
        this.sprecher = sprecher;
    }

    @Override
    public String toString() {
        return "Monolog " +
                ", actName='" + actName + '\'' +
                ", sceneName='" + sceneName + '\'' +
                "sprecher='" + sprecher + '\'' +
                ", monologText=\n" + monologText + '\'' +
                "}\n";
    }
}
