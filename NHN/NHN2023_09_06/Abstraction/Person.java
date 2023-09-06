public class Person implements Comparable<Person>{
    private String name;
    private int registerNumber;
    private int age;

    public Person(int registerNumber, String name, int age) {
        this.registerNumber = registerNumber;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getRegisterNumber() {
        return registerNumber;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return  name + ", " + registerNumber + ", " + age;
    }

    @Override
    public int compareTo(Person m) {
        return this.getAge() - m.getAge();
    }
}
