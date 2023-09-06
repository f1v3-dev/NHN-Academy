import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // Student student = new Student(20184493, "Seungjo", "Computer Engineering");
        // System.out.println(student);

        // Date date = new Date();
        // System.out.println(date);

        Department<Student> department = new Department<>(1, "Computer Engineering");
        department.add(new Student(1, "Seungjo", 25));
        department.add(new Student(4, "Celine", 24));
        department.add(new Student(5, "Rogers", 22));
        department.add(new Student(2, "James", 21));
        department.add(new Student(3, "Jason", 19));

        printStudents(department);

        // Collections.sort(department.getList());
        // 1. Comparator의 subType class
        // 2. 익명 클래스
        // 3. 람다식
        // 4. Method 참조

        Collections.sort(department.getList(), new AgeOrder());

        // Collections.sort(department.getList(), new AgeOrder<Student>() {
        //     @Override
        //     public int compare(Student s1, Student s2) {
        //         return s1.getAge() - s2.getAge();
        //     }
        // });
        
        // Collections.sort(department.getList(), (s1, s2) -> s1.getAge() - s2.getAge());

        // Collections.sort(department.getList(), Comparator.comparingInt(Student::getAge));

        System.out.println();
        printStudents(department);

    }

    public static void printStudents(Department<Student> department) {
        for (Student s : department) {
            System.out.println(s);
        }
    }
}