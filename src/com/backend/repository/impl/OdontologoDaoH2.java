package com.backend.repository.impl;

import com.backend.repository.IDao;
import com.backend.repository.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class OdontologoDaoH2 implements IDao<Odontologo>{

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        String insert = "INSERT INTO ODONTOLOGOS(NOMBRE, APELLIDO, MATRICULA) VALUES(?, ?, ?)";

        Connection connection = null;
        Odontologo odontologoRegistrado = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.execute();

            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();


            while(resultSet.next()){
                odontologoRegistrado = new Odontologo(resultSet.getLong("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("matricula"));
            }

            LOGGER.info("Odontologo guardado: " + odontologoRegistrado);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.error("Tuvimos un problema. " + e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologoRegistrado;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;

        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                odontologoEncontrado = new Odontologo(resultSet.getLong(1), resultSet.getString("nombre"),resultSet.getString("apellido"),resultSet.getString("matricula"));
            }


        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }catch (Exception ex){
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        if (odontologoEncontrado != null)
            LOGGER.info("Se ha encontrado al odontologo " + odontologoEncontrado);

        else  LOGGER.error("No se encontro el odontologo con id " + id);

        return odontologoEncontrado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return List.of();
    }

}