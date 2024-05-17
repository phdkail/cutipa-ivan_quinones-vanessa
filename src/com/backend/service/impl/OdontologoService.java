package com.backend.service.impl;

import com.backend.dao.IDao;
import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.dbconnection.H2Connection;
import com.backend.model.Odontologo;
import com.backend.service.IDontologoService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoService implements IDontologoService {

    private final IDao<Odontologo> odontologoIDao;


    private static final Logger logger= Logger.getLogger(OdontologoDaoH2.class);

    private static Integer contadorId = 1;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
        // Inicializa la lista H2Connection.listarTodos si es null
        if (H2Connection.listarTodos == null) {
            H2Connection.listarTodos = new ArrayList<>();
        }
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        odontologo.setId(contadorId);
        contadorId++;
        H2Connection.listarTodos.add(odontologo);
        logger.info("Odontologo guardado con exito en la lista");
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void Odontologo(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> listaOdontologos() {
        return H2Connection.listarTodos;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        odontologo.setId(contadorId);
        contadorId++;
        H2Connection.listarTodos.add(odontologo);
        logger.info("Odontologo guardado con exito en la lista");
        return odontologo;
    }

    @Override
    public Odontologo buscarPorID(Integer id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Lista de odontologos obtenida con exito");
        return H2Connection.listarTodos;
    }
}
