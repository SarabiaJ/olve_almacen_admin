package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.ClienteDAO;
import com.olvealmacen.tienda.modelo.Cliente;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> obtenerClientes() {
        return clienteDAO.listar();
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerPorId(id);
    }

    public boolean agregarCliente(Cliente cliente) {
        return clienteDAO.agregar(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizar(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminar(id);
    }
}
