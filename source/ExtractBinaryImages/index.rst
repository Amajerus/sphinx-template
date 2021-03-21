Extracting Binary Images
========================

We have shown how to store an image or file into SQL Server. Click :ref:`Storing Binary Images` to go back
and review.  In this example will show a simple way to extract the image from SQL Server.  This will prompt you
to input the location you want to save the image/file in, the name of the file, and the extension of the file.

We will be calling the ``Blob`` value.  Click :ref:`Blob` to go back and learn about ``Blobs``.  We will also
calling the ``getBinaryStream`` method, get more info `here`_.

.. _here: https://docs.microsoft.com/en-us/sql/connect/jdbc/reference/getbinarystream-method-java-lang-string?view=sql-server-ver15

.. note::
    **This method can be used only with SQL Server data types of binary, varbinary, varbinary(max), and image.**
    **Trying to use it with other data types will cause an exception to be thrown.**


We are also using this

.. code-block:: java

    int bytesRead = -1;
    byte[] buffer = new byte[BUFFER_SIZE];
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
    }

We are telling the program to read the to number of bytes in the ``InputStream``.  We are starting of
``bytesRead`` at -1 which is considered the end of the stream.  You will need to determine the **BUFFER_SIZE**
or the total number of bytes read into the buffer.  This `Link`_ talks about BufferSize.  It states:

.. _Link: http://java-performance.info/java-io-bufferedinputstream-and-java-util-zip-gzipinputstream/#:~:text=Buffer%20size%20is%2C%20actually%2C%20an,a%20number%20of%20disk%20reads.


"Buffer size is, actually, an average size of block to be read from the
input device. That’s why it often worth to explicitly increase it at least to 64K (65536), and in case of
very large input files – to something between 512K and 2M in order to further reduce a number of disk reads.
Many experts also advise to set it equal to multiplier of 4096 – one of common disk sector sizes, so,
don’t set buffer size to, for example, 125000. Set it to 131072 (128K) instead."

In the while loop we are saying ``(bytesRead = inputStream.read(buffer)`` does not equal -1, then write to the
``outputStream``.  The ``outputStream`` is using the parameters byte[] buffer, start offset in the data, and the number
of bytes to write.  In this case we are saying to write the buffer, with no offset, with all the bytesRead.

| Oracle has more information about InputStreams and OutputStreams.
| Click on `InputStreams`_ to learn more about InputStreams.
| Click on `OutputStreams`_ to learn more about OutputStreams.

.. _InputStreams: https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html
.. _OutputStreams: https://docs.oracle.com/javase/7/docs/api/java/io/OutputStream.html

Code Example
------------

.. code-block:: java
    :emphasize-lines: 23-24
    :linenos:

    package net.codejava.jdbc;
    import java.io.*;
    import java.sql.*;
    import java.util.Scanner;

    public class GetBinaryImage {
        public static void main(String[] args) throws FileNotFoundException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                Scanner console = new Scanner(System.in);
                System.out.print("Enter Save Location ");
                String filepath = console.next();
                File fpath = new File(filepath);
                System.out.print("Enter Saved File Name ");
                String picname = console.next();
                System.out.print("Enter Saved File Name Extension ");
                String picnameext = console.next();
                String dbURL = "jdbc:sqlserver://localhost;databaseName=GAFA; " +
                               "integratedSecurity=true";
                conn = DriverManager.getConnection(dbURL);
                //This is a simple SELECT statement from the database Pics. We are wanting to get all data
                //from column PictureName that matches the name of the picture we inputted.
                String sql =  "SELECT * " +
                              "From dbo.Pics "+
                              "Where PictureName = '" + picname + "' ; ";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    Blob blob = rs.getBlob("Data");
                    InputStream inputStream = blob.getBinaryStream();
                    OutputStream outputStream = new FileOutputStream(fpath +"\\" + picname +"." +  picnameext);
                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    inputStream.close();
                    outputStream.close();
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }