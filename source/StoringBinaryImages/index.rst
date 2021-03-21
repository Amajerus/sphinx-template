.. _Storing Binary Images:

Storing Binary Images
=====================

In this section we will show you how to store image files in a database.

Explanation
-----------
Reasons why would you want to store images in a database:
    * You are storing images that are changing dynamically,
      say invoices and you wanted to get an invoice as it was on 1 Jan 2007?
    * If you want to store documents for the IRS.  Since you supposed to
      documents for 7 years.
    * Images stored in the database might not require a different backup strategy,
      where as images stored on filesystem might.
    * Access to the images can be easily restricted.

Reasons why you might not want to store images in a database:
    * Can have a heavier load on the database server.
    * Requires additional code to access and stream the images.
    * Storage can be more expensive with a database than some types of file systems.


In our example we created a table called ``Pics``, click :ref:`Create Table Intro` to learn how to create
a table. The columns in our table were ``PictureName`` which is a ``nvarchar(100)`` which is the ``PK``,
``FileName`` also a ``nvarchar(100)``, and the ``Data`` stored as ``varbinary(max)``.

We want to use ``varbinary(max)`` as we are storing the images as binary files and we want to allocate the
**maximum** amount of memory to this column we can.

We are also using the *Java InputStream class*. The *Java InputStream class*, ``java.io.InputStream``, represents
an ordered stream of bytes. In other words, you can read data from a *Java InputStream* as an ordered sequence of
bytes. This is useful when reading data from a file, or received over the network.

We are using prepared statements in this code as well. Click :ref:`Prepared Statements` to learn about prepared
statements.

.. _Blob:

Blob
----
You will also notice that we are setting the variable inputstream as a ``Blob``.  A ``Blob`` is a **built-in type**
that stores a ``Binary Large Object`` as a column value in a row of a database table. By default drivers implement
Blob using an SQL locator(BLOB), which means that a Blob object contains a logical pointer to the SQL BLOB data
rather than the data itself.  Methods in the interfaces ``ResultSet``, ``CallableStatement``, and ``PreparedStatement``,
such as ``getBlob`` and ``setBlob`` allow a programmer to access an *SQL BLOB* value. The Blob interface provides
methods for getting the length of an *SQL BLOB (Binary Large Object)* value, for materializing a *BLOB* value on the
client, and for determining the position of a pattern of bytes within a *BLOB* value. In addition, this interface
has methods for updating a *BLOB* value.

Code
----

.. code-block:: java
    :emphasize-lines: 21-23
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
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA;
                                integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                InputStream inputStream = new FileInputStream(new File(filepath));
                // Our example creates a new FileInputStream instance. FileInputStream
                // is a subclass of InputStream so it is safe to assign an instance of
                // FileInputStream to an InputStream variable (the inputstream variable).
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
