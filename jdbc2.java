import java.sql.*;
import java.util.*;

class Jdbc1 {
    private static final String URL = "jdbc:mysql://localhost:3306/studinfo"; // Update the database URL if necessary
    private static final String USER = "root";
    private static final String PASSWORD = "kunal@1";
    private Connection conn;
    private Scanner sc;

    public Jdbc1() {
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded...");

            // Establish a connection to the database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");

            sc = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void insert() throws SQLException {
        System.out.println("Enter Id:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Name:");
        String name = sc.nextLine();

        System.out.println("Enter Age:");
        int age = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Enter Course:");
        String course = sc.nextLine();

        String query = "INSERT INTO Students (id, name, age, course) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, course);

            if (ps.executeUpdate() > 0) {
                System.out.println("Insert Successful.");
            } else {
                System.out.println("Insert Failed.");
            }
        }
    }

    public void update() throws SQLException {
        System.out.println("Enter Id of the student you want to update:");
        int id = sc.nextInt();
        sc.nextLine();
    
        System.out.println("What do you want to update?");
        System.out.println("1. Name\n2. Age\n3. Course\n4. ID");
        int choice = sc.nextInt();
        sc.nextLine(); 

        String query = "";
        PreparedStatement pst = null;
    
        switch (choice) {
            case 1: 
                System.out.println("Enter New Name:");
                String newName = sc.nextLine();
                query = "UPDATE Students SET name = ? WHERE id = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, newName);
                pst.setInt(2, id);
                break;
    
            case 2:
                System.out.println("Enter New Age:");
                int newAge = sc.nextInt();
                query = "UPDATE Students SET age = ? WHERE id = ?";
                pst = conn.prepareStatement(query);
                pst.setInt(1, newAge);
                pst.setInt(2, id);
                break;
    
            case 3:
                System.out.println("Enter New Course:");
                String newCourse = sc.nextLine();
                query = "UPDATE Students SET course = ? WHERE id = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, newCourse);
                pst.setInt(2, id);
                break;
    
            case 4:
                System.out.println("Enter New ID:");
                int newId = sc.nextInt();
                query = "UPDATE Students SET id = ? WHERE id = ?";
                pst = conn.prepareStatement(query);
                pst.setInt(1, newId);
                pst.setInt(2, id);
                break;
    
            default:
                System.out.println("Invalid choice.");
                return;
        }
    
        int updatedRows = pst.executeUpdate();
        if (updatedRows > 0) {
            System.out.println("Update Successful.");
        } else {
            System.out.println("Update Failed.");
        }
    }

    public void updateAll() throws SQLException {
        System.out.println("Enter Name:");
        String name1 = sc.next();

        System.out.println("Enter Age:");
        int age1 = sc.nextInt();

        System.out.println("Enter Course:");
        String course1 = sc.next();

        System.out.println("Enter Id:");
        int id1 = sc.nextInt();

        String str = "UPDATE Students SET name=?, age=?, course=? WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(str);

        pst.setString(1, name1);
        pst.setInt(2, age1);
        pst.setString(3, course1);
        pst.setInt(4, id1);

        int updatedRows = pst.executeUpdate();
        if (updatedRows > 0) {
            System.out.println("Updated Successfully");
        } else {
            System.out.println("Update Failed");
        }
    }

    public void delete() throws SQLException {
        System.out.println("Enter Id to delete:");
        int id = sc.nextInt();

        String query = "DELETE FROM Students WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);

            if (pst.executeUpdate() > 0) {
                System.out.println("Delete Successful.");
            } else {
                System.out.println("Delete Failed.");
            }
        }
    }

    public void view() throws SQLException {
        String query = "SELECT * FROM Students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Course: " + rs.getString("course"));
                System.out.println("-------------------------");
            }
        }
    }
}

public class jdbc2 {

    public static void main(String[] args) {
        Jdbc1 obj = new Jdbc1();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1 : INSERT");
            System.out.println("2 : UPDATE");
            System.out.println("3 : UPDATEALL");
            System.out.println("4 : DELETE");
            System.out.println("5 : VIEW");
            System.out.println("6 : EXIT");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            try {
                switch (choice) {
                    case 1:
                        obj.insert();
                        break;
                    case 2:
                        obj.update();
                        break;
                    case 3:
                        obj.updateAll();
                        break;
                    case 4:
                        obj.delete();
                        break;
                    case 5:
                        obj.view();
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}
