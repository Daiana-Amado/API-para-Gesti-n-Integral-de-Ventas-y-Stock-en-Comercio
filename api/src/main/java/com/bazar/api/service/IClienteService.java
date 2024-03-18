package com.bazar.api.service;

import com.bazar.api.model.Cliente;
import java.util.List;

public interface IClienteService {
        
    public String crearCliente(Cliente cli);
    
    public void editarCliente(Long idCliente, String nuevoNombre, String nuevoApellido, String nuevoDni);
    
    public String borrarCliente(Long idCliente);
    
    public List<Cliente> traerClientes();
    
    public Cliente buscarCliente(Long idCliente);
    
}
