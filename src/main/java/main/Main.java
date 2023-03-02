package main;

import lombok.SneakyThrows;
import main.dao.StudentDao;
import main.entities.Student;
import main.view.ViewStudents;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private StudentDao studentDao;
    private ViewStudents viewStudents;
    private Scanner scanner;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    @SneakyThrows
    private void run() {
        Properties props = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))) {
            props.load(reader);
            Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
            studentDao = new StudentDao(connection);
            viewStudents = new ViewStudents();
        }
        scanner = new Scanner(System.in);
        int m;
        while ((m = menu()) != 0) {
            switch (m) {
                case 1 -> {
                    showAll();
                }
                case 2 -> {
                    addStudent();
                }
            }
        }
    }

    private void showAll() {
        List<Student> students = studentDao.findAll();
        viewStudents.showList(students);
    }

    private void addStudent() {
        Student s = viewStudents.readStudent(scanner);
        studentDao.add(s);
    }

    private int menu() {
        System.out.println("""
                1. Show All
                2. Add Student
                0. Exit
                """
        );
        return Integer.parseInt(scanner.nextLine());
    }
}
