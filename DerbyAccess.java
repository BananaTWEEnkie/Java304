package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides connection methods for connecting to embedded and networked
 * databases using the Derby RDBMS. Methods are also provided for disconnecting
 * from embedded Derby databases and shutting down an embedded Derby RDBMS. 
 * @author JAM
 * @version 150307
 */
public class DerbyAccess {

    /**
     * Obtains a connection to an  embedded database (i.e., the RBDMS runs on
     * the client and in the same process as the application).
     * @param dbName the name of the database to be accessed
     * @param dbDirectory the location of the database to be accessed
     * @return a database Connection object
     * @throws SQLException 
     */
    public static Connection getEmbeddedConnection (String dbName,
                                                    String dbDirectory)
            throws SQLException {
        System.setProperty ("derby.system.home", dbDirectory);
        String dbUrl = "jdbc:derby:" + dbName;
        return DriverManager.getConnection(dbUrl);
    }

    /**
     * Obtains a connection to a networked database (i.e., the RBDMS runs in a
     * separate process on a server and can be accessed by multiple client
     * applications).
     * @param dbName the name of the database to be accessed
     * @param dbServer the name of the server that is hosting the RDBMS
     * @param dbPort the port numbert
     * @param username the database access user name
     * @param password the database access password
     * @return a database Connection object
     * @throws SQLException 
     */
    public static Connection getNetworkConnection (String dbName,
                                                   String dbServer,
                                                   String dbPort,
                                                   String username,
                                                   String password)
            throws SQLException {
        String dbUrl = "jdbc:derby:" + dbServer + ":" + dbPort + "/" + dbName;
        return DriverManager.getConnection(dbUrl, username, password);
    }

    /**
     * Disconnects from all embedded databases and shuts down the embedded
     * RDBMS.
     * @return true if the the shutdown is successful.
     */
    public static boolean shutdownEmbeddedDbms () {
        return shutdownEmbeddedDb ("");
    }

    /**
     * Shuts down an embedded database while leaving the embedded RDBMS running.
     * @param dbName the name of the embedded database to be shut down.
     * @return true if the the database shutdown is successful.
     */
    public static boolean shutdownEmbeddedDb (String dbName) {
        try {
            String shutdownURL = "jdbc:derby:" + dbName + ";shutdown=true";
            DriverManager.getConnection(shutdownURL);
        } catch (SQLException e) {
            System.out.println (e.getMessage());
            return true;
        }
        return false;
    }
}
