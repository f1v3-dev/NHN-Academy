package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatementStudentRepository implements StudentRepository {

    @Override
    public int save(Student student) {

        String sql = String.format("INSERT INTO jdbc_students(id, name, gender, age) VALUES('%s', '%s', '%s', %d)",
                student.getId(), student.getName(), student.getGender(), student.getAge());

        log.info("save = {}", sql);

        try (Connection connection = DbUtils.getConnection(); Statement statement = connection.createStatement()) {

            int result = statement.executeUpdate(sql);
            log.debug("save = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findById(String id) {

        String sql = String.format("SELECT * FROM jdbc_students WHERE id = '%s'", id);

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            log.info("findById = {}", sql);
            if (rs.next()) {
                Student student = new Student(rs.getString("id"), rs.getString("name"),
                        Student.GENDER.valueOf(rs.getString("gender")), rs.getInt("age"),
                        rs.getTimestamp("created_at").toLocalDateTime());

                return Optional.of(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int update(Student student) {
        String sql = String.format("UPDATE jdbc_students SET name = '%s', gender = '%s', age = %d WHERE id = '%s'",
                student.getName(), student.getGender(), student.getAge(), student.getId());


        try (Connection connection = DbUtils.getConnection(); Statement statement = connection.createStatement()) {

            log.info("update = {}", sql);
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(String id) {

        String sql = String.format("DELETE FROM jdbc_students WHERE id = '%s'", id);

        log.info("deleteById = {}", sql);
        try (Connection connection = DbUtils.getConnection(); Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(sql);
            log.debug("deleteById = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
