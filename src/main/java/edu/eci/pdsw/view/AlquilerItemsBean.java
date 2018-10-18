package edu.eci.pdsw.view;

import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ActionEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alquilerItemsBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private List<Cliente> clientes;
    private long documento;
    private long valor;

    /**
     *
     * @return @throws Exception
     */
    public List<Cliente> getClientes() throws Exception {
        try {
            clientes = serviciosAlquiler.consultarClientes();
            return clientes;
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @param lista
     * @return
     */
    public List<Cliente> setClientes(List<Cliente> lista) {
        return clientes = new ArrayList<>(lista);
    }

    /**
     *
     * @param nombre
     * @param documento
     * @param direccion
     * @param telefono
     * @param email
     * @throws Exception
     */
    public void registrarCliente(String nombre, long documento, String direccion, String telefono, String email) throws Exception {
        try {
            Cliente cliente = new Cliente(nombre, documento, telefono, direccion, email);
            serviciosAlquiler.registrarCliente(cliente);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @return los items que no han sido regresados por el cliente
     * @throws ExcepcionServiciosAlquiler
     */
    public List<Item> itemsNoRegresados() throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarItemsNoRegresados(this.documento);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @param itemid
     * @return un item rentado dado el id del item
     * @throws ExcepcionServiciosAlquiler
     */
    public ItemRentado getItemRentado(int itemid) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarItemRentado(itemid);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @param itemid
     * @return la multa del alquiler de un cliente hasta la fecha actual
     * @throws ExcepcionServiciosAlquiler
     */
    public long multaAlquiler(int itemid) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarMultaAlquiler(itemid, new Date());
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @param codigoItem
     * @param numeroDias
     * @throws ExcepcionServiciosAlquiler
     */
    public void valorAlquiler(int codigoItem, int numeroDias) throws ExcepcionServiciosAlquiler {
        try {
            this.valor = serviciosAlquiler.consultarCostoAlquiler(codigoItem, numeroDias);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }

    /**
     *
     * @param codigoItem
     * @param numeroDias
     * @throws ExcepcionServiciosAlquiler
     */
    public void registarAlquiler(int codigoItem, int numeroDias) throws ExcepcionServiciosAlquiler {
        try {
            Item item = serviciosAlquiler.consultarItem(codigoItem);
            serviciosAlquiler.registrarAlquilerCliente(new Date(), this.documento, item, numeroDias);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }
    
    public void docCliente(ActionEvent event) {
        this.documento = (long) event.getComponent().getAttributes().get("parameter");
    }
    
    /**
     *
     * @return el documento del cliente
     */
    public long getDocumento() {
        return documento;
    }

    /**
     *
     * @param valor
     */
    public void setValor(long valor) {
        this.valor = valor;
    }

    /**
     *
     * @return el valor del alquiler
     */
    public long getValor() {
        return valor;
    }

}
