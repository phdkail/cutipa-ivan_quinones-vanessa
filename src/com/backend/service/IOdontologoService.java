package com.backend.service;

import com.backend.model.Odontologo;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Odontologo buscarOdontologoPorId(Long id);
}
