Binary Images
=============

In this section we will show you how to store image files in a database.

Explanation
-----------





Code
----

.. code-block:: java
    :linenos:
    package net.codejava.jdbc;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.InputStream;
    import java.sql.*;

    public class BinaryImage {
        public static void main(String[] args) throws FileNotFoundException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String filepath = "Location of file";

            try {
                ;
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                InputStream inputStream = new FileInputStream(new File(filepath));
                String sql = "Begin Try " +
                        "Begin Transaction " +
                        "Insert into Pics (PictureName, Filename, Data) " +
                        "Values(?,?,?)"+
                        "Commit Transaction " +
                        "End Try " +
                        "Begin Catch "+
                        "Rollback " +
                        "DECLARE @ErrorMessage NVARCHAR(4000); \n" +
                        "DECLARE @ErrorSeverity INT; \n" +
                        "DECLARE @ErrorState INT; \n" +
                        "SELECT \n" +
                        "@ErrorMessage = ERROR_MESSAGE(), \n" +
                        "@ErrorSeverity = ERROR_SEVERITY(), \n" +
                        "@ErrorState = ERROR_STATE();  \n" +
                        // Use RAISERROR inside the CATCH block to return error
                        // information about the original error that caused
                        // execution to jump to the CATCH block.
                        "RAISERROR (@ErrorMessage, -- Message text. \n" +
                        "@ErrorSeverity, -- Severity. \n" +
                        "@ErrorState -- State0. \n" +
                        ");  \n" +
                        "END Catch";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "ISU2");
                stmt.setString(2, "ISU.jpg");
                stmt.setBlob(3,inputStream);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error Code: " + ex.getErrorCode());
                System.out.println("Error Message:  " + ex.getMessage() );
            }
        }
    }
