package com.backend.test;

import com.backend.model.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaInsertarUnOdontologoYRetornarElIdEnH2(){

        //arrange
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoAPersistir = new Odontologo("JOSUE", "ROSALES", "S45GD4");
        //act
        Odontologo odontologoPersistido = odontologoService.registrarOdontologo(odontologoAPersistir);
        //assert
        assertNotNull(odontologoPersistido.getId());
    }


}