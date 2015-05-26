import java.sql.*;

/**
 * Created by Andrew on 22/05/2015.
 */
public class DatabaseWorker {

    private String dbName;

    public DatabaseWorker(String dbName){
        this.dbName = dbName;
        createTable();
    }

    /**
     * Inserts the movie record inside the database.
     * @param m
     */
    public void insertIntoDB(Movie m){
        Connection c = null;
        try {
            //Insert here your database name.
            c=connectToDB();

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

            statement.close();
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
     * @return Connection To the database.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection connectToDB() throws ClassNotFoundException, SQLException {
        if(!dbName.endsWith(".db"))
            dbName = dbName.concat(".db");
        Connection c;
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:"+dbName);
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");
        return c;
    }

    private void createTable(){
        Connection c = null;
        Statement stmt = null;
        try{
            c = connectToDB();
            stmt = c.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS movies (ID STRING PRIMARY KEY, Title TEXT NOT NULL, Actors TEXT, Rating TEXT, Year TEXT, Plot TEXT, Genre TEXT,Poster TEXT);");
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
