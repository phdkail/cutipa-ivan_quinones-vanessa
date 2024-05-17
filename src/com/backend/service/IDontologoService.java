package com.backend.service;

import com.backend.model.Odontologo;

import java.util.List;

public interface IDontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos = null;

    void Odontologo(Odontologo odontologo);

    CharSequence listarOdontologos();
}
