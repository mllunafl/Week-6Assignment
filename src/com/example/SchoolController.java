package com.example;

import java.util.Scanner;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class SchoolController {
    public static void main(String[] args) {
        Scanner s = null;
        SchoolService sc = new SchoolService();
        try {
            s = new Scanner(System.in);
            while (true) {
                System.out.println("What do you want to do?\n enter:");
                System.out.println("1: to see all students\n" +
                        "2: to see all classes\n" + "3: to create a new student\n" +
                        "4: to create a new class\n" + "5: see all of a student's classes\n" +
                        "6: see all the student in a class\n" + "7: assign a student to a class\n" + "8: remove a student from a class\n" +
                        "9: to delete a student\n" + "10: to delete a class\n");
                int menu = s.nextInt();
                switch (menu) {
                    case 1:
                        sc.getAllstudents();
                        break;

                    case 2:
                        sc.getAllclasses();
                        break;

                    case 3: {
                        Scanner scan = new Scanner(System.in);
                        sc.createStudent(scan);
                    }
                    case 4: {
                        Scanner scan = new Scanner(System.in);
                        sc.createClas(scan);
                    }
                    case 5: {
                        Scanner scan = new Scanner(System.in);
                        sc.retrieveStudentclasses(scan);
                    }
                    case 6: {
                        Scanner scan = new Scanner(System.in);
                        sc.retrieveClasstudents(scan);
                    }
                    case 7: {
                        Scanner scan = new Scanner(System.in);
                        sc.assignStudent(scan);
                    }
                    case 8: {
                        Scanner scan = new Scanner(System.in);
                        sc.removeStudent(scan);
                    }
                    case 9: {
                        Scanner scan = new Scanner(System.in);
                        sc.deleteStudent(scan);
                    }
                    case 10: {
                        Scanner scan = new Scanner(System.in);
                        sc.deleteClas(scan);
                    }
                    default:
                        System.out.println("Input an option 1-10");
                }
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}


