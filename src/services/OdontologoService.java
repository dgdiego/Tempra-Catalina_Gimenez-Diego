package services;

import dao.IDao;
import models.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return odontologoIDao.listarTodos();
    }
}
