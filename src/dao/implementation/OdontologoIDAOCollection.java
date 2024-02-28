package dao.implementation;

import dao.IDao;
import models.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoIDAOCollection implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoIDAOCollection.class);
    private List<Odontologo> listaOdontologos = new ArrayList<>();
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontólogo en memoria");
        listaOdontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando todos los odontólogos desde memoria");
        return listaOdontologos;
    }
}
