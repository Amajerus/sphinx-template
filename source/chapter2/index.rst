Add Maven to IntelliJ
=====================
To help build the project we used Maven.  To get more info click on
`Maven <https://maven.apache.org/what-is-maven.html>`_.

`Click here <https://maven.apache.org/download.cgi>`_ to download Maven, if needed.

To add Maven to IntelliJ:
    1.  Right click on your project folder in IntelliJ.
    2.  Click New; Module

        .. image:: NewModule.JPG

    #.  Click Next
    #.  Give your module a name, we chose **maven**

        .. image:: NameModule.JPG

    #.  Click Finish

Once the Maven Module is created a pom.xml file will appear.

.. code-block:: xml

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Maven</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    </project>

This will need to be updated with:

.. image:: dependancy.JPG

Below is the updated pom.xml file.

.. code-block:: xml
    :linenos:
    :emphasize-lines: 15-21

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>

        <groupId>org.example</groupId>
        <artifactId>Maven</artifactId>
        <version>1.0-SNAPSHOT</version>

        <properties>
            <maven.compiler.source>8</maven.compiler.source>
            <maven.compiler.target>8</maven.compiler.target>
        </properties>
        <dependencies>
        <dependency>
            <groupId>com.microsoft.sqlserver</groupId>
            <artifactId>mssql-jdbc</artifactId>
            <version>9.2.0.jre8</version>
        </dependency>
        </dependencies>
    </project>