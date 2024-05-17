package com.backend.service;

import com.backend.model.Odontologo;

import java.util.List;

public interface IDontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    Odontologo buscarPorId(Integer id);

    void Odontologo(Odontologo odontologo);

    List<Odontologo> listaOdontologos();

    Odontologo registrar(Odontologo odontologo);

    Odontologo buscarPorID(Integer id);

    void actualizar(Odontologo odontologo);

    void eliminar(Integer id);

    List<Odontologo> buscarTodos();
}
