import java.util.Comparator;

public class AgeOrder implements Comparator<Person>{

    @Override
    public int compare(Person s1, Person s2) {
        return s1.getAge() - s2.getAge();
    }
}