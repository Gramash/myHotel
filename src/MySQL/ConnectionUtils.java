package MySQL;

import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static final Logger LOG = Logger.getLogger(ConnectionUtils.class);
    private static final String URL = "jdbc:mysql://localhost:3306/myhotel" +
            "?user=root&password=gibsonlp";

//    public static Connection getConnection() throws SQLException {
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        return DriverManager.getConnection(URL);
//    }

    public static void closeCon(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("CANT CLOSE CONNECTION");
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.out.println("cant rollback");
                e.printStackTrace();
            }
        }
    }

    private ConnectionUtils ()  {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // ST4DB - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/ST4DB");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
    private DataSource ds;

    public static Connection getConnection(){
        DataSource ds = null;
        Connection conn = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext
                    .lookup("java:/comp/env");

            ds = (DataSource) envContext
                    .lookup("mysql");

            conn = ds.getConnection();

        } catch (NamingException | SQLException ex) {
            //throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            ex.printStackTrace();
        }
        System.out.println("DSConnection established");
        return conn;
    }


}
