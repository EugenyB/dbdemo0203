package main.view;

import main.entities.Student;

import java.util.List;
import java.util.Scanner;

public class ViewStudents {
    public void showList(List<Student> students) {
        System.out.println("--- All Students ---");
        students.forEach(System.out::println);
        System.out.println("--------------------");
    }

    public Student readStudent(Scanner scanner) {
        System.out.print("Name:");
        String name = scanner.nextLine();
        System.out.print("Age:");
        int age = scanner.nextInt();
        System.out.print("Rating:");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        return new Student(0, name, age, rating);
    }
}
