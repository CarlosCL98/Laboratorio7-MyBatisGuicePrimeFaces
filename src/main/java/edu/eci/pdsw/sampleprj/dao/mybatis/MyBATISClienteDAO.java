package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente cli) throws PersistenceException {
        try {
            clienteMapper.insertarCliente(cli);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al registrar el cliente " + cli.toString(), e);
        }
    }

    @Override
    public Cliente load(int id) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }
    }

    @Override
    public List<Cliente> load() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes item", e);
        }
    }

    @Override
    public void saveItemRentado(ItemRentado ir, long idcli) throws PersistenceException {
        try {
            clienteMapper.agregarItemRentadoACliente(idcli, ir.getItem().getId(), ir.getFechainiciorenta(), ir.getFechafinrenta());
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al agregar el item rentado al cliente", e);
        }
    }

    @Override
    public void vetarCliente(long idcli, boolean estado) throws PersistenceException {
        try {
            clienteMapper.vetarCliente(idcli, estado);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al vetar al cliente " + idcli, e);
        }
    }

    @Override
    public void eliminarCliente(Cliente cliente) throws PersistenceException {
        try {
            clienteMapper.eliminarCliente(cliente);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al eliminar al cliente " + cliente.toString(), e);
        }
    }
}