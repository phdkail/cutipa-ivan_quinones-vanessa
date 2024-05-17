package com.backend.test;

import static org.junit.jupiter.api.Assertions.*;

import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OdontologoServiceTest {

    private OdontologoDaoH2 odontologoDaoH2;
    private OdontologoService odontologoService;

    @BeforeEach
    public void setUp() {
        H2Connection.ejecutarScriptInicial();
        odontologoDaoH2 = new OdontologoDaoH2();
        odontologoService = new OdontologoService(odontologoDaoH2);
    }

    @Test
    public void registrarOdontologo_DeberiaRegistrarCorrectamente() {
        Odontologo odontologo = new Odontologo(1, "Juan", "Perez", 12345);

        Odontologo registrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(registrado);
        assertEquals(odontologo.getId(), registrado.getId());
        assertEquals(odontologo.getNombre(), registrado.getNombre());
        assertEquals(odontologo.getApellido(), registrado.getApellido());
        assertEquals(odontologo.getNumeroMatricula(), registrado.getNumeroMatricula());
    }
}