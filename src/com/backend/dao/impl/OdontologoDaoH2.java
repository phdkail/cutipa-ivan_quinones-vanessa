package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) throws SQLException {
        try (Connection connection = H2Connection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, NUMEROMATRICULA) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, odontologo.getNombre());
                preparedStatement.setString(2, odontologo.getApellido());
                preparedStatement.setInt(3, odontologo.getNumeroMatricula());
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        return new Odontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getNumeroMatricula());
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error al registrar el odontólogo: " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        // Implementación pendiente
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
                        resultSet.getInt("ID"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("APELLIDO"),
                        resultSet.getInt("NUMEROMATRICULA")
                );
                odontologos.add(odontologo);
            }
        } catch (SQLException e) {
            LOGGER.error("Error al listar los odontólogos: " + e.getMessage());
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return odontologos;
    }
}