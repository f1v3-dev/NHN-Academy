package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.common.Page;
import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepositoryV4;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentRepositoryImplV4 implements StudentRepositoryV4 {

    @Override
    public int save(Connection connection, Student student) {

        String sql = "INSERT INTO jdbc_students(id, name, gender, age) VALUES(?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getGender().toString());
            statement.setInt(4, student.getAge());

            int result = statement.executeUpdate();
            log.debug("save = {}", result);

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Student> findById(Connection connection, String id) {
        String sql = "SELECT * FROM jdbc_students WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student(resultSet.getString("id"), resultSet.getString("name"),
                        Student.GENDER.valueOf(resultSet.getString("gender")), resultSet.getInt("age"),
                        resultSet.getTimestamp("created_at").toLocalDateTime());

                return Optional.of(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int update(Connection connection, Student student) {
        String sql = "UPDATE jdbc_students SET name=?, gender=?, age=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getGender().toString());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getId());

            int result = statement.executeUpdate();
            log.debug("update = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(Connection connection, String id) {

        String sql = "DELETE FROM jdbc_students WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);

            int result = statement.executeUpdate();
            log.debug("delete = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteAll(Connection connection) {
        String sql = "DELETE FROM jdbc_students";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            int result = statement.executeUpdate();
            log.debug("result = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long totalCount(Connection connection) {
        //todo#4 totalCount 구현

        String sql = "SELECT count(*) FROM jdbc_students";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0L;
    }

    @Override
    public Page<Student> findAll(Connection connection, int page, int pageSize) {
        //todo#5 findAll 구현

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        String sql = "SELECT * FROM jdbc_students ORDER BY id DESC LIMIT ?, ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, offset);
            psmt.setInt(2, limit);

            List<Student> studentList = new ArrayList<>();
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                studentList.add(new Student(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Student.GENDER.valueOf(resultSet.getString("gender")),
                        resultSet.getInt("age"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                        ));
            }

            return new Page<>(studentList, studentList.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}