import java.sql.*;
import java.util.*;

public class Jdbcconnection
{
    public static void main(String[] args) throws Exception {
        
    
    String url = "jdbc:mysql://localhost:3306/studinfo"; 
        String user = "root";  
        String password = "kunal@1"; 
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studinfo", "root", "kunal@1");
        while(true)
        {   System.out.println(" 1 : VIEW");
            System.out.println(" 2 : INSERT");
            System.out.println(" 3 : UPDATE");
            System.out.println(" 4 : DELETE");
            System.out.println(" 5 : EXIT ");

        Scanner sc = new Scanner ( System.in );
        System.out.print("enter your choice");
        int ch = sc.nextInt();
          try{
              switch(ch)
              {
                case 1: 
                System.out.println(" Database connected!");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students");
                 while (rs.next()) {
                System.out.println("id" + rs.getInt("id") + " - " + rs.getString("name")+ " - " + rs.getInt("age") + " - " + rs.getString("course"));
             
                 }
                 break;
          case 2: 
          
                   String sql = "insert into students (id ,name ,age ,course) VALUES (?,?,?,?)";
                    PreparedStatement st=conn.prepareStatement(sql);
                      st.setInt(1,14);
                      st.setString(2,"SSA");
                      st.setInt(3, 22);
                      st.setString(4, "sql");

                     if(st.executeUpdate()>0)
                {
                  System.out.println("Data inserted successfully!");

                }
                    else
                      System.out.println("Loss");
                    break;
          case 3:
                   String query = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";
                   PreparedStatement pstmt = conn.prepareStatement(query);
                   pstmt.setString(1, "KUNAL");
                   pstmt.setInt(2, 24);
                   pstmt.setString(3,"c++" );
                   pstmt.setInt(4, 1 );
               
                   int updatedview = pstmt.executeUpdate();
                   if (updatedview > 0) {
                       System.out.println(" Student updated successfully!");
                   } else {
                       System.out.println(" Student not found.");
                   }
                   break;
           case 4:
               
                    String sqlquery = "delete from students where age  = ?  ";
                    PreparedStatement stmt1 = conn.prepareStatement(sqlquery);
                     stmt1.setString(1, "24");
                     int deletedrec= stmt1.executeUpdate();
                    if (deletedrec > 0) {
                       System.out.println(" Student deleted successfully!");
                    } else {
                      System.out.println(" Student not found.");
                    }
                    break;
           case 5:     
                    System.out.println("exited");
                     return ;
          }            
     
  } 
        catch(SQLException e)
        {
             System.out.println(e);
        }
}
}
}