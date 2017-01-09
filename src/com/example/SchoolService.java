package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class SchoolService {
    private static StudentDao studentDao = new StudentDao();
    private static ClasDao clasDao = new ClasDao();
    private static StudentClasDao studentClasDao = new StudentClasDao();
    private static Connection conn;
    private static List<Clas> clasList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    private static Scanner s;


    public static void deleteClas (Scanner s) {
        try {
            System.out.println("Input class id: ");
            int clas_id = s.nextInt();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            if (clasDao.areThereClasses(clas_id,conn)==1){
                clasDao.deleteClas (clas_id,conn);
                //System.out.println("commit");
                conn.commit();
            } else {
                System.out.println("Need to remove students from this class first");
            }
        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

    public static void deleteStudent (Scanner s) {
        try {
            System.out.println("Input student id:");
            int student_id = s.nextInt();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            if (studentDao.areThereStudets(student_id, conn)==1){
                studentDao.deleteStudent(student_id,conn);
                //System.out.println("commit");
                conn.commit();
            } else {
                System.out.println("Need to remove student from classes first");
            }

        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

    public static void assignStudent (Scanner s) {
        try {
            int student_id = 0;
            int clas_id = 0;
            System.out.println("Input student id number:");
            while (s.hasNext()){
               student_id = s.nextInt();
                System.out.println("Input class id number:");
               clas_id = s.nextInt();
               if (clas_id > 0){
                   break;
               }
            }
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            studentClasDao.assignStudent(student_id,clas_id,conn);
            //System.out.println("commit");
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

    public static void removeStudent (Scanner s) {
        try {
            int student_id = 0;
            int clas_id = 0;
            System.out.println("input student id: ");
            while (s.hasNext()){
                student_id = s.nextInt();
                System.out.println("input class id: ");
                clas_id = s.nextInt();
                if (clas_id > 0){
                    break;
                }
            }
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            studentClasDao.removeStudent(student_id,clas_id,conn);
            System.out.println("commit");
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

    public static void getAllstudents (){
        try {
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            Student student = new Student();
            studentList = studentDao.students(conn);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < studentList.size(); i++){
                student = studentList.get(i);
                sb.append("Student name= " + student.getName() + " ");
                sb.append("Student id= " + student.getStudent_Id() + "\n");
            }
            System.out.println(sb.toString());
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static void getAllclasses (){
        try {
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            Clas clas = new Clas();
            clasList = clasDao.clasList(conn);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < clasList.size(); i++){
                clas = clasList.get(i);
                sb.append("Class name= " + clas.getName() + " ");
                sb.append("Class id = " + clas.getClas_id() + "\n");
            }
            System.out.println(sb.toString());
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static void retrieveClasstudents (Scanner s){
        try {
            System.out.println("Input the class id: ");
            int clas_id = s.nextInt();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            studentList = studentClasDao.retrieveStudents(clas_id,conn);
            String name = clasDao.getClasname(clas_id,conn);
            StringBuilder sb = new StringBuilder();
            sb.append("Class= " + name + "\n");
            for(int i = 0; i < studentList.size(); i++){
                Student student = studentList.get(i);
                sb.append("Student= " + student.getName() + ",");
            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }


    public static void retrieveStudentclasses (Scanner s){
        try {
            System.out.println("Input student's id number:");
            int student_id = s.nextInt();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            clasList = studentClasDao.retrieveClas(student_id,conn);
            String name = studentDao.getStudentName(student_id,conn);
            StringBuilder sb = new StringBuilder();
            sb.append("Student= " + name + "\n");
            for(int i = 0; i < clasList.size(); i++){
               Clas clas = clasList.get(i);
               sb.append("Class= " + clas.getName() + ",");
            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static void createStudent (Scanner s) {
        try {
            System.out.println("Input Student Name:");
            String name = s.nextLine();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            studentDao.createStudent(name,conn);
            System.out.println("commit");
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

    public static void createClas (Scanner s) {
        try {
            System.out.println("Input Class Name:");
            String name = s.nextLine();
            conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            clasDao.createClas(name,conn);
            System.out.println("commit");
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                System.out.println("rollback");
                try {
                    conn.rollback();
                }catch (SQLException e1){
                    DatabaseUtils.printSQLException(e1);
                }
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    DatabaseUtils.printSQLException(e1);
                }
            }
        }
    }

}
