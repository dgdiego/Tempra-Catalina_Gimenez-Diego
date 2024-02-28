package dao.implementation;

import dao.BD;
import dao.IDao;
import models.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDAOH2 implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoIDAOH2.class);

    private static final String INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos guardando un odontólogo en H2");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGO);
            psInsert.setInt(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Odontologo guardado correctamente con matrícula número "+odontologo.getNumeroMatricula());
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando todos los odontólogos desde H2");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();
            Statement statementSelect = connection.createStatement();

            ResultSet rs = statementSelect.executeQuery(SELECT_ALL);

            while (rs.next()) {
                Odontologo odontologo = new Odontologo();
                odontologo.setNumeroMatricula(rs.getInt(1));
                odontologo.setNombre(rs.getString(2));
                odontologo.setApellido(rs.getString(3));

                odontologos.add(odontologo);
                LOGGER.info("Agregamos al listado de odontólogos al odontólogo "+odontologo.getNombre()+" "+odontologo.getApellido());
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Lista de odontólogos" + odontologos);
        return odontologos;
    }
}
