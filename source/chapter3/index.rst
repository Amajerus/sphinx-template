Connect Java to SQL Server
==========================

The code below is just a demonstration of one way to connect to SQL Server.

.. code-block:: java

    package net.codejava.jdbc;

    import java.sql.Connection;
    import java.sql.DatabaseMetaData;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class JavaToSql {
        public static void main(String[] args) {
            Connection conn = null;

            try {
                String dbURL = "jdbc:sqlserver://localhost;databaseName=main; integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                if (conn != null) {
                    DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                    System.out.println("Driver name: " + dm.getDriverName());
                    System.out.println("Driver version: " + dm.getDriverVersion());
                    System.out.println("Product name: " + dm.getDatabaseProductName());
                    System.out.println("Product version: " + dm.getDatabaseProductVersion());
                    System.out.println("Connected to SQl Server");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }





