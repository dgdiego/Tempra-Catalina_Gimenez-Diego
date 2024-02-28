package test;

import dao.BD;
import dao.implementation.OdontologoIDAOCollection;
import dao.implementation.OdontologoIDAOH2;
import models.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import services.OdontologoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OdontologosTest {
    private static final Logger LOGGER = Logger.getLogger(OdontologosTest.class);

    @Test
    public void pruebaCasoInsercionH2(){
        BD.createTable();
        LOGGER.info("Inicializando el caso de prueba Inserción H2");
        Odontologo odontologo1 = new Odontologo(1234,"Catalina","Tempra");
        Odontologo odontologo2 = new Odontologo(3456,"Diego","Gimenez");
        Odontologo odontologo3 = new Odontologo(6789,"Vanina","Godoy");

        LOGGER.info("Prueba H2");
        OdontologoService odontologoService = new OdontologoService(new OdontologoIDAOH2());
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.guardar(odontologo3);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() == 3);
    }

    @Test
    public void pruebaCasoInsercionMemoria(){
        LOGGER.info("Inicializando el caso de prueba Inserción memoria");
        Odontologo odontologo1 = new Odontologo(1234,"Catalina","Tempra");
        Odontologo odontologo2 = new Odontologo(3456,"Diego","Gimenez");
        Odontologo odontologo3 = new Odontologo(6789,"Vanina","Godoy");

        LOGGER.info("Prueba Memoria");
        OdontologoService odontologoService = new OdontologoService(new OdontologoIDAOCollection());
        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.guardar(odontologo3);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() == 3);
    }
}
