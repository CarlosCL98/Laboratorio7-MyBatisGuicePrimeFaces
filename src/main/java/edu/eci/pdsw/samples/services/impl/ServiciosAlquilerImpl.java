package edu.eci.pdsw.samples.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

    private final static long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

    @Inject
    private ItemDAO itemDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ItemRentadoDAO itemRentadoDAO;

    @Override
    public int valorTarifaAlquilerxDia(int itemId) {
        Item item;
        int valor = 0;
        try {
            item = this.consultarItem(itemId);
            valor = (int) item.getTarifaxDia();
        } catch (ExcepcionServiciosAlquiler e) {
            System.out.println(e.getMessage());
        }
        return valor;
    }

    @Override
    public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
        return this.valorTarifaAlquilerxDia(itemId);
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.load((int) docu);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        Cliente cliente = this.consultarCliente(idcliente);
        return cliente.getRentados();
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        return clienteDAO.load();
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() {
        return itemDAO.LoadItemsDisponibles();
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        int multa = 0;
        ItemRentado itemRentado = itemRentadoDAO.load(iditem);
        Date fechaFinRenta = itemRentado.getFechafinrenta();
        long diffInMillies = fechaFinRenta.getTime() - fechaDevolucion.getTime();
        if (diffInMillies < 0) {
            multa = (int) (((-1 * diffInMillies) / MILLISECONDS_IN_DAY) * this.valorMultaRetrasoxDia(iditem));
        }
        return multa;
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        Item item = this.consultarItem(id);
        return item.getTipo();
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        List<Item> items = itemDAO.load();
        List<TipoItem> tiposItem = new ArrayList();
        for (Item i : items) {
            tiposItem.add(consultarTipoItem(i.getId()));
        }
        return tiposItem;
    }

    @Override
    public List<Item> consultarItemsNoRegresados(long documento) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.loadItemsNoRegresados(documento);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }
    
    @Override
    public ItemRentado consultarItemRentado(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemRentadoDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numdias);
        Date finDate = cal.getTime();
        ItemRentado itemRentado = new ItemRentado(0, item, date, finDate);
        clienteDAO.saveItemRentado(itemRentado, docu);
    }

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.save(c);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            return (long) (valorTarifaAlquilerxDia(iditem) * numdias);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.saveTarifaItem(id, tarifa);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler(ex.getMessage(), ex);
        }
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            itemDAO.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registrar el item " + i.getId(), ex);
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try {
            clienteDAO.vetarCliente(docu, estado);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al vetar al cliente " + docu, ex);
        }
    }
}
