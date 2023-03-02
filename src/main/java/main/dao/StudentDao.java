package main.dao;

import lombok.AllArgsConstructor;
import main.entities.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StudentDao {
    private Connection connection;

    public List<Student> findAll() {
        ArrayList<Student> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from student")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double rating = rs.getDouble("rating");
                list.add(new Student(id, name, age, rating));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void add(Student student) {
        try (PreparedStatement ps = connection.prepareStatement("insert into student (name, rating, age) VALUES (?,?,?)")) {
            ps.setString(1, student.getName());
            ps.setDouble(2, student.getRating());
            ps.setInt(3, student.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
