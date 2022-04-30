import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {


    public static final String location = Accueil.class.getResource("maBDD.db").toExternalForm();


    public static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }

    public static Connection connect(String location) {
        checkDrivers();
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " + location);
            return null;
        }
        return connection;
    }

    public static ArrayList<OffreStage> getAllStage() {
        ArrayList<OffreStage> laListe = new ArrayList<>();

        String query = "SELECT * FROM mesStages ORDER BY nomStructure ASC";
        try (Connection connection = connect(location)) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                laListe.add(new OffreStage(
                        rs.getInt("id"),
                        rs.getString("nomStructure"),
                        rs.getString("sujetStage"),
                        rs.getInt("duree"),
                        rs.getString("debutStage"),
                        rs.getInt("promo")
                ));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return laListe;
    }


}
