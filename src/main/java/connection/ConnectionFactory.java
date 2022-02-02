package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa pentru realizarea conexiunii dintre cod si baza de date
 */

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/store";
    private static final String USER = "root";
    private static final String PASS = "tp2021";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructorul fara parametri al clasei ConnectionFactory aceeseaza driver-ul declarat anterior
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda de creare a conexiunii efective cu baza de date
     * @return conexiunea cu baza de date
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Metoda pentru obtinerea conexiunii clasei curente
     * @return conexiunea clasei curente
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Metoda de inchidere a conexiunii cu baza de date
     * @param connection conexiunea care se va inchide
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Metoda pentru inchiderea unui statement catre baza de date
     * @param statement statement-ul ce se va inchide
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Metoda pentru inchiderea setului de rezultate returnat de un acces la baza de date
     * @param resultSet setul de tuple returnate din baza de date
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
