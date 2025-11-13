// com.politecnicllevant.firstweb.util.ConnectionManager
package com.politecnicllevant.firstweb.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final JdbcConnector jdbc = new JdbcConnector();
    private static EntityManagerFactory emf;

    public static Connection getConnection() throws SQLException {
        return jdbc.connect();
    }

    public static EntityManager getEntityManager() {
        if (emf == null) {
            synchronized (ConnectionManager.class) {
                if (emf == null) {
                    emf = Persistence.createEntityManagerFactory("moviesMysql");
                }
            }
        }
        return emf.createEntityManager();
    }
}
