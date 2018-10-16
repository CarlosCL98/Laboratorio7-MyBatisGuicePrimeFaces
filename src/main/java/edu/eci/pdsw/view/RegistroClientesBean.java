package edu.eci.pdsw.view;

import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name = "clientesBean")
@SessionScoped
public class RegistroClientesBean extends BasePageBean {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private List<Cliente> clientes;

    public List<Cliente> getClientes() throws Exception {
        try {
            clientes = serviciosAlquiler.consultarClientes();
            return clientes;
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    public List<Cliente> setClientes(List<Cliente> lista) {
        return clientes = new ArrayList<>(lista);
    }

    public void registrarCliente(String nombre, long documento, String direccion, String telefono, String email) throws Exception {
        try {
            Cliente cliente = new Cliente(nombre, documento, telefono, direccion, email);
            serviciosAlquiler.registrarCliente(cliente);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

}
