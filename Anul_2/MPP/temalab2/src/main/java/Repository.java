import java.util.HashMap;
import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Repository {

    private static final Logger logger = LogManager.getLogger();

    public void add(Student s){
        logger.traceEntry();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Razvan/Desktop/Facultate/Anul_2/MPP/temalab2/Studentsdb.db");
            String cmd = "INSERT INTO Student(nrMatricol, Nume, Prenume, Varsta, CNP) VALUES("+s.getNrMatricol()+",'"
                +s.getNume()+"','"+s.getPrenume()+"',"+s.getVarsta()+",'"+s.getCNP()+"');";
            System.out.println(cmd);
            try (Statement stmt  = conn.createStatement()){
                int rs = stmt.executeUpdate(cmd);
                System.out.println("A fost adaugat studentul");
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        logger.traceExit();
    }

    public void update(Student s){
        logger.traceEntry();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Razvan/Desktop/Facultate/Anul_2/MPP/temalab2/Studentsdb.db");
            String cmd = "UPDATE Student SET Nume='"+s.getNume()+"', Prenume='"+s.getPrenume()+"', Varsta="+
                    s.getVarsta()+", CNP='"+s.getCNP()+"' WHERE nrMatricol="+s.getNrMatricol();
            try (Statement stmt  = conn.createStatement()){
                int rs = stmt.executeUpdate(cmd);
                System.out.println("A fost modificat studentul");
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        logger.traceExit();
    }

    public void delete(Integer id){
        logger.traceEntry();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Razvan/Desktop/Facultate/Anul_2/MPP/temalab2/Studentsdb.db");
            String cmd = "DELETE FROM Student WHERE nrMatricol="+id;
            try (Statement stmt  = conn.createStatement()){
                int rs  = stmt.executeUpdate(cmd);
                System.out.println("A fost sters studentul");
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        logger.traceExit();
    }

    public void printAll(){
        logger.traceEntry();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Razvan/Desktop/Facultate/Anul_2/MPP/temalab2/Studentsdb.db");
            String cmd = "SELECT * FROM Student;";
            try (Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(cmd)){
                while(rs.next()){
                    System.out.println(rs.getInt("nrMatricol") + '\t' +
                            rs.getString("Nume") + '\t' +
                            rs.getString("Prenume") + '\t' +
                            rs.getInt("Varsta") + '\t' +
                            rs.getString("CNP") + '\t');
                }
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        logger.traceExit();
    }

}
