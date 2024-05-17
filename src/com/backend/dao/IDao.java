package com.backend.dao;

import com.backend.model.Odontologo;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T>{

    T registrar(T t) throws SQLException;
    T buscarPorId(int id);
    List<T> listarTodos() throws SQLException;
}
