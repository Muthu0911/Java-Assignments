import java.util.*;

class Student<T> {
    T id;
    String name;

    Student(T id, String name) {
        this.id = id;
        this.name = name;
    }

    void display() {
        System.out.println("ID: " + id + " Name: " + name);
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {

        ArrayList<Student<Integer>> list = new ArrayList<>();

        list.add(new Student<Integer>(101, "Arun"));
        list.add(new Student<Integer>(102, "Priya"));
        list.add(new Student<Integer>(103, "Kavin"));

        Iterator<Student<Integer>> itr = list.iterator();

        System.out.println("Student Records:");

        while (itr.hasNext()) {
            Student<Integer> s = itr.next();
            s.display();
        }
    }
}