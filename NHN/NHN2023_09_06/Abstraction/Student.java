public class Student implements Comparable<Student>{
    private int studentNo;
    private String name;
    private int age;

    public Student(int studentNo, String name, int age ){
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public int getAge() {
        return this.age;
    }
    public int setStudentNo(int studentNo) {
        return this.studentNo = studentNo;
    }

    @Override
    public String toString() {
        return studentNo + ", " + name + ", " + age;
    }

    @Override
    public int compareTo(Student o) {
        return this.getStudentNo() - o.getStudentNo();
    }

}