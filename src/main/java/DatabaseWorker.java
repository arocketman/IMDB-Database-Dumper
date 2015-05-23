import java.sql.*;

/**
 * Created by Andrew on 22/05/2015.
 */
public class DatabaseWorker {

    /**
     * Inserts the movie record inside the database.
     * @param m
     */
    public void insertIntoDB(Movie m){
        Connection c = null;
        Statement stmt = null;
        try {
            //Insert here your database name.
            c=connectToDB("test");
            stmt = c.createStatement();
            PreparedStatement statement = c.prepareStatement("INSERT INTO movies (ID,Title,Actors,Rating,Year,plot,Genre,Poster) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1,m.getId());
            statement.setString(2,m.getTitle());
            statement.setString(3,m.getActors());
            statement.setString(4,m.getRating());
            statement.setString(5,m.getYear());
            statement.setString(6,m.getPlot());
            statement.setString(7,m.getGenre());
            statement.setString(8,m.getPoster());
            statement.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to the database.
     * @param dbname database name.
     * @return Connection To the database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection connectToDB(String dbname) throws ClassNotFoundException, SQLException {
        if(!dbname.endsWith(".db"))
            dbname = dbname.concat(".db");
        Connection c;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:"+dbname);
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        return c;
    }



}
