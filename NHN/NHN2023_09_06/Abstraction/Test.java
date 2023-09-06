import java.util.Comparator;

public class Test {

    public static void main(String[] args) {
        // Student student = new Student(20184493, "Seungjo", "Computer Engineering");
        // System.out.println(student);

        // Date date = new Date();
        // System.out.println(date);

        Department<Person> department = new Department<>(1, "Computer Engineering");
        department.add(new Student(1, "Seungjo", 25));
        department.add(new Student(2, "James", 21));
        department.add(new Student(3, "Jason", 19));
        department.add(new Student(4, "Celine", 24));
        department.add(new Student(5, "Rogers", 22));

        department.add(new Professor(1, "Jeniffer", 48));
        department.add(new Professor(2, "Heisenberg", 48));
        department.add(new Professor(3, "Hooft", 48));

        printStudents(department);


        /*
         * Person의 하위 타입 : Student, Professor
         * Comparable이 동작하도록 구현 -> 람다식을 사용하였습니다.
         */

        // department.sort();

        // department.sort(new Comparator<Person>() {
        //     @Override
        //     public int compare(Person p1, Person p2) {
        //         return p1.getAge() - p2.getAge();
        //     }

        // });

        department.sort((p1, p2) -> p1.getAge() - p2.getAge());
        System.out.println();
        printStudents(department);

    }

    public static void printStudents(Department<Person> department) {
        for (Person m : department) {
            System.out.println(m);
        }
    }
}