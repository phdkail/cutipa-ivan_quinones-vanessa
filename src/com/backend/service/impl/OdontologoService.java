package com.backend.service.impl;

import com.backend.dao.IDao;
import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.model.Odontologo;
import com.backend.service.IDontologoService;

public class OdontologoService implements IDontologoService {

    private IDao<Odontologo> OdontologoIDao;

    public OdontologoService(OdontologoDaoH2 odontologoDaoH2) {
        this.OdontologoIDao = odontologoDaoH2;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologo;
    }

    @Override
    public void Odontologo(Odontologo odontologo) {

    }

    @Override
    public CharSequence listarOdontologos() {
        return null;
    }

    public Odontologo buscarPorId(int id) {
        return OdontologoIDao.buscarPorId(id);
    }
}
