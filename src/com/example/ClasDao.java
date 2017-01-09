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
public class ClasDao {

    public static int areThereClasses (int clas_id, Connection conn) {
        int areThere = 0;
        try {
            String clas = "select * from student_clas where clas_id = ?";
            PreparedStatement pStmnt = conn.prepareStatement(clas);
            pStmnt.setInt(1,clas_id);
            ResultSet rsult = pStmnt.executeQuery();
            if (!rsult.isBeforeFirst()){
                areThere = 1;
            }
            return areThere;
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return areThere;
    }

    public static void deleteClas(int clas_id, Connection conn) {
        try {
            String deleteC = "DELETE from clas WHERE clas_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(deleteC);
            pStmt.setInt(1,clas_id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static void createClas (String name, Connection conn){
        try{
            String createC = "INSERT into clas (name) VALUES (?)";
            PreparedStatement pStmt = conn.prepareStatement(createC);
            pStmt.setString(1,name);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static List<Clas> clasList (Connection conn) {
        List<Clas> clasList = new ArrayList<>();
        try {
            String getAllclasses = "select * from clas";
            PreparedStatement pStmt = conn.prepareStatement(getAllclasses);
            pStmt.executeQuery();
            ResultSet rsult = pStmt.executeQuery();
            while (rsult.next()){
                Clas clas = new Clas();
                clas.setName(rsult.getString("name"));
                clas.setClas_id(rsult.getInt("clas_id"));
                clasList.add(clas);
            }
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return clasList;
    }

    public static String getClasname (int clas_id, Connection conn) {
        String clasName= null;
        try {
            String getClasname = "select name from clas WHERE clas_id = ?";
            PreparedStatement pStment = conn.prepareStatement(getClasname);
            pStment.setInt(1,clas_id);
            ResultSet rs = pStment.executeQuery();
            if (rs.next()) {
                clasName = rs.getString("name");
                return clasName;
            }
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return clasName;
    }

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseUtils.getInstance().getConnection();
            conn.setAutoCommit(false);
            String name = getClasname(6,conn);
            System.out.println(name);
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
    }
}
