import java.util.ArrayList;
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
}
