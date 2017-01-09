package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class StudentClasDao {

    public static void assignStudent (int student_id, int clas_id, Connection conn){
        try{
            String assignS = "INSERT into student_clas (clas_id, student_id) VALUES (?,?)";
            PreparedStatement pStmt = conn.prepareStatement(assignS);
            pStmt.setInt(1,clas_id);
            pStmt.setInt(2,student_id);
            pStmt.execute();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static void removeStudent (int student_id, int clas_id, Connection conn){
        try{
            String assignS = "DELETE from student_clas WHERE student_id = ? and clas_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(assignS);
            pStmt.setInt(1,student_id);
            pStmt.setInt(2,clas_id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static List<Clas> retrieveClas(int student_id, Connection conn) throws SQLException {
        ResultSet rsult=null;
            String listStudclas = "select c.name from clas c, student s, student_clas sc WHERE s.student_id = sc.student_id and c.clas_id = sc.clas_id and s.student_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(listStudclas);
            String id = Integer.toString(student_id);
            pStmt.setString(1, id);
            rsult = pStmt.executeQuery();
            List<Clas> clasList = new ArrayList<>();
            while (rsult.next()){
                Clas clas = new Clas();
                clas.setName(rsult.getString("name"));
                clasList.add(clas);
            }
            return clasList;
    }

    public static List<Student> retrieveStudents(int clas_id, Connection conn) throws SQLException {
        ResultSet rsult=null;
        String listClasstudents = "select s.name from student s, clas c, student_clas sc WHERE s.student_id = sc.student_id and c.clas_id = sc.clas_id and c.clas_id = ?";
        PreparedStatement pStmt = conn.prepareStatement(listClasstudents);
        pStmt.setInt(1, clas_id);
        rsult = pStmt.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rsult.next()){
            Student student = new Student();
            student.setName(rsult.getString("s.name"));
            students.add(student);
        }
        return students;
    }

    public static void main(String[] args) throws SQLException {

    }
}
