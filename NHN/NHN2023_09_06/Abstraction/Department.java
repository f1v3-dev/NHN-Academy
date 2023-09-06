import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Department<E extends Person> implements Iterable<E> {

    private int departmentNo;
    private String departmentName;

    private List<E> list = new LinkedList<E>();

    public Department(int departmentNo, String departmentName) {
        this.departmentNo = departmentNo;
        this.departmentName = departmentName;
    }

    public void add(E e) {
        list.add(e);
    }

    public int getDepartmentNo() {
        return departmentNo;
    }

    public void sort() {
        Collections.sort(this.list);
    }

    public void sort(Comparator<E> compare) {
        Collections.sort(this.list, compare);
    }

    public String getDepartmentName() {
        return departmentName;
    }
    
    public List<E> getList() {
        return list;
    }
    
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

}