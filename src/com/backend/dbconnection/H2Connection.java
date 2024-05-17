package com.backend.dbconnection;

import com.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Connection {
    public static final Logger logger= Logger.getLogger(H2Connection.class);

    private static final String SQL_PRUEBA= "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES ('ABC123','Juan','Lopez'), ('DEF456','Roberto','Perez');";

    private static final String SQL_DROP_CREATE_ODONTOLOGOS="DROP TABLE IF EXISTS ODONTOLOGOS; " +
            "CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NUMERO_MATRICULA VARCHAR(100)  NOT NULL, NOMBRE VARCHAR(100)  NOT NULL, APELLIDO VARCHAR(100)  NOT NULL)";

    public static void crearTablas(){
        Connection connection= null;
        try{
            connection= getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE_ODONTOLOGOS);
            logger.info("Tabla odontologo creada con exito");
            statement.execute(SQL_PRUEBA);
            logger.info("tablas creadas con exito");

        }catch (Exception e){
            logger.warn(e.getMessage());
        }

    }

    public static List<Odontologo> listarTodos = new ArrayList<Odontologo>();

    public static void agregarOdontologosLista() {
        listarTodos.add(new Odontologo("ad4345","MARIA","PEREZ"));
        listarTodos.add(new Odontologo("sdfgsdf","LUIS","RODRIGUEZ"));
        logger.info("Odontologos agregados a la lista con exito");
    }

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/Odontologos","sa","");
    }
}
