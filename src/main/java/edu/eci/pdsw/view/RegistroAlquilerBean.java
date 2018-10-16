package edu.eci.pdsw.view;

import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import java.util.Date;

@SuppressWarnings("deprecation")
@ManagedBean(name = "alquileresBean")
@RequestScoped
public class RegistroAlquilerBean extends BasePageBean {

    @ManagedProperty(value = "#{param.documento}")    
    private long documento;
    
    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    
    private long valor;
    
    /**
     * 
     * @return los items que no han sido regresados por el cliente
     * @throws ExcepcionServiciosAlquiler 
     */
    public List<Item> itemsNoRegresados() throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarItemsNoRegresados(documento);
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
            System.out.println("valor:"+this.valor);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }
    
    public void registarAlquiler(int codigoItem, int numeroDias) throws ExcepcionServiciosAlquiler {
        try {
            Item item = serviciosAlquiler.consultarItem(codigoItem);
            serviciosAlquiler.registrarAlquilerCliente(new Date(), documento, item, numeroDias);
        } catch (ExcepcionServiciosAlquiler ex) {
            throw ex;
        }
    }
    
    /**
     * Almacena el documento del cliente
     * @param documento 
     */
    public void setDocumento(long documento) {
        this.documento = documento;
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
     * @return 
     */
    public long getValor() {
        return valor;
    }

}