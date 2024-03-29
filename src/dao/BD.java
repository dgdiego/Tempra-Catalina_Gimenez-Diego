package dao;

import dao.implementation.OdontologoIDAOH2;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BD {

    private static final Logger LOGGER = Logger.getLogger(BD.class);
    private static final String SQL_TABLE_CREATE = readSQLFile();


    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/MiBase",
                "sa", "");
    }

    public static void createTable() {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();
            statement.execute(SQL_TABLE_CREATE);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static String readSQLFile(){
        String data = null;
        try {
            File myObj = new File(System.getProperty("user.dir")+"/src/dao/script.sql");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Ha ocurrido un error en la lectura del archivo SQL", e);
            e.printStackTrace();
        }

        return data;
    }

}
