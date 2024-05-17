package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.backend.test.OdontologoServiceTest.logger;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) throws SQLException {
        try (Connection connection = H2Connection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ODONTOLOGOS (ID, NOMBRE, APELLIDO) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, String.valueOf(odontologo.getId()));
                preparedStatement.setString(2, odontologo.getNombre());
                preparedStatement.setString(3, odontologo.getApellido());
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        return new Odontologo(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido());
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error al registrar el odontólogo: " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }


    @Override
    public List<Odontologo> listarTodos() throws SQLException {
        List<Odontologo> odontologos = new ArrayList<>();

        try (Connection connection = H2Connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getString("ID"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("APELLIDO")
                );
                odontologos.add(odontologo);
            }
        } catch (SQLException e) {
            LOGGER.error("Error al listar los odontólogos: " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return odontologos;
    }
}