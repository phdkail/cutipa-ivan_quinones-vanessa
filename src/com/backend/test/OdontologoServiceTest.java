package com.backend.test;

import static org.junit.jupiter.api.Assertions.*;

import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import com.backend.service.impl.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


    public class OdontologoServiceTest {
        public static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
        private OdontologoService odontologoService;

        @BeforeEach
        public void setUp() {
            OdontologoDaoH2 odontologoDaoH2 = new OdontologoDaoH2();
            odontologoService = new OdontologoService(odontologoDaoH2);
        }

        @Test
        public void testOdontologosEnMemoria() {
            logger.info("Haciendo test de odontologos en memoria");

            Odontologo odontologo = new Odontologo("SD3435", "JOSE", "PEREZ");
            odontologoService.registrarOdontologo(odontologo);

            List<Odontologo> listaOdontologos = (List<Odontologo>) odontologoService.listaOdontologos();
            assertFalse(listaOdontologos.isEmpty(), "La lista de odontólogos no debería estar vacía");
            assertEquals(1, listaOdontologos.size(), "La lista de odontólogos debería tener un elemento");
            assertEquals(odontologo, listaOdontologos.get(0), "El odontólogo registrado debería ser igual al esperado");
        }

        @Test
        public void testOdontologosEnH2() {
            logger.info("Haciendo test de odontologos en H2");

            Odontologo odontologo = new Odontologo("SDF3435", "LUISA", "TORO");
            odontologoService.registrarOdontologo(odontologo);

            List<Odontologo> listaOdontologos = odontologoService.listaOdontologos();
            assertFalse(listaOdontologos.isEmpty(), "La lista de odontólogos no debería estar vacía");


            boolean containsOdontologo = listaOdontologos.stream()
                    .anyMatch(o -> o.getId().equals(odontologo.getId()));

            assertTrue(containsOdontologo, "La lista de odontólogos debería contener el odontólogo registrado");
        }
    }