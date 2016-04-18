/**
 * Created by danielvolz on 14.04.16.
 */
public class Speaker {
    private String name;
    private Work work;


    public Speaker(String name, Work work) {
        this.name = name;
        this.work = work;
    }

    public String getName() {

        return name;
    }

    public int getNumberOfMonologues() {
        return work.getNumberOfMonologuesBySpeaker(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", work=" + work +
                '}';
    }
}
