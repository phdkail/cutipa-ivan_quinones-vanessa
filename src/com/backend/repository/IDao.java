package com.backend.repository;

import com.backend.model.Odontologo;

import java.util.List;

public interface IDao<T>{

    T registrar(T t);

    Odontologo buscarPorId(Long id);

    List<T> listarTodos();
}
