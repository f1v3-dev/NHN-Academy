package com.nhnacademy.jdbc.club.repository.impl;

import com.nhnacademy.jdbc.club.domain.ClubStudent;
import com.nhnacademy.jdbc.club.repository.ClubRegistrationRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClubRegistrationRepositoryImpl implements ClubRegistrationRepository {

    @Override
    public int save(Connection connection, String studentId, String clubId) {

        String sql = "INSERT INTO jdbc_club_registrations VALUES(?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, studentId);
            psmt.setString(2, clubId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByStudentIdAndClubId(Connection connection, String studentId, String clubId) {

        String sql = "DELETE FROM jdbc_club_registrations WHERE student_id = ? AND club_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, studentId);
            psmt.setString(2, clubId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubStudent> findClubStudentsByStudentId(Connection connection, String studentId) {


        /**
         * SELECT a.id AS student_id, a.name AS student_name, c.club_id AS club_id, c.club_name AS club_name
         * FROM jdbc_students AS a INNER JOIN jdbc_club_registrations AS B ON a.id = b.student_id
         *                         INNER JOIN jdbc_club AS C ON b.club_id = c.club_id
         * WHERE a.id = ?
         */

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id AS club_id, c.club_name AS club_name ")
                .append("FROM jdbc_students AS a INNER JOIN jdbc_club_registrations AS B ON a.id = b.student_id ")
                .append("INNER JOIN jdbc_club AS C ON b.club_id = c.club_id ")
                .append("WHERE a.id = ?");


        log.debug("sql = {}", sql);


        try (PreparedStatement psmt = connection.prepareStatement(sql.toString())) {

            psmt.setString(1, studentId);
            ResultSet resultSet = psmt.executeQuery();
            List<ClubStudent> clubStudents = new LinkedList<>();

            while (resultSet.next()) {

                ClubStudent student = new ClubStudent(
                        resultSet.getString("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("club_id"),
                        resultSet.getString("club_name")
                );

                clubStudents.add(student);
            }
            return clubStudents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubStudent> findClubStudents(Connection connection) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id AS club_id, c.club_name AS club_name ")
                .append("FROM jdbc_students AS a INNER JOIN jdbc_club_registrations AS B ON a.id = b.student_id ")
                .append("INNER JOIN jdbc_club AS C ON b.club_id = c.club_id ")
                .append("ORDER BY student_id ASC, club_id ASC;");

        try (PreparedStatement psmt = connection.prepareStatement(sql.toString())) {

            ResultSet resultSet = psmt.executeQuery();
            List<ClubStudent> students = new LinkedList<>();
            while (resultSet.next()) {
                students.add(new ClubStudent(
                        resultSet.getString("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("club_id"),
                        resultSet.getString("club_name")
                ));
            }
            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubStudent> findClubStudents_left_join(Connection connection) {
        /**
         * SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         *      LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * ORDER BY student_id ASC, c.club_id ASC;
         */

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("ORDER BY student_id ASC, c.club_id ASC");

        return getClubStudents(connection, sql);
    }

    @Override
    public List<ClubStudent> findClubStudents_right_join(Connection connection) {
        /**
         * SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         *      RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * ORDER BY c.club_id ASC, student_id ASC;
         */

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("ORDER BY c.club_id ASC, student_id ASC");

        return getClubStudents(connection, sql);
    }

    @Override
    public List<ClubStudent> findClubStudents_full_join(Connection connection) {


        /**
         * (SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         * 	    LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id)
         * UNION
         * (SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         * 	    RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id);
         */

        StringBuilder sql = new StringBuilder();
        sql.append("(SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id)")
                .append("UNION")
                .append("(SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id)");

        return getClubStudents(connection, sql);
    }

    @Override
    public List<ClubStudent> findClubStudents_left_excluding_join(Connection connection) {

        /**
         * SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         *  	LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * WHERE c.club_id IS NULL
         * ORDER BY student_id ASC;
         */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("WHERE c.club_id IS NULL ")
                .append("ORDER BY student_id ASC");

        return getClubStudents(connection, sql);
    }

    @Override
    public List<ClubStudent> findClubStudents_right_excluding_join(Connection connection) {
        /**
         * SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         *      RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * WHERE a.id IS NULL
         * ORDER BY c.club_id ASC;
         */

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("WHERE a.id IS NULL ")
                .append("ORDER BY c.club_id ASC");

        return getClubStudents(connection, sql);
    }

    @Override
    public List<ClubStudent> findClubStudents_outher_excluding_join(Connection connection) {
        /**
         * (SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         * 	    LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         * 	    LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * WHERE c.club_id IS NULL)
         *
         * UNION
         *
         * (SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name
         * FROM jdbc_students AS a
         * 	    RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id
         *      RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id
         * WHERE a.id IS NULL)
         */

        StringBuilder sql = new StringBuilder();

        sql.append("(SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("LEFT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("LEFT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("WHERE c.club_id IS NULL) ")
                .append("UNION ")
                .append("(SELECT a.id AS student_id, a.name AS student_name, c.club_id, c.club_name ")
                .append("FROM jdbc_students AS a ")
                .append("RIGHT JOIN jdbc_club_registrations AS b ON a.id = b.student_id ")
                .append("RIGHT JOIN jdbc_club AS c ON b.club_id = c.club_id ")
                .append("WHERE a.id IS NULL)");

        return getClubStudents(connection, sql);
    }

    private static List<ClubStudent> getClubStudents(Connection connection, StringBuilder sql) {
        try (PreparedStatement psmt = connection.prepareStatement(sql.toString())) {

                List<ClubStudent> students = new LinkedList<>();
                ResultSet resultSet = psmt.executeQuery();
                while (resultSet.next()) {
                    students.add(new ClubStudent(
                            resultSet.getString("student_id"),
                            resultSet.getString("student_name"),
                            resultSet.getString("club_id"),
                            resultSet.getString("club_name")
                    ));
                }
            return students;
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

}