package com.bazar.api.service;

import com.bazar.api.model.Cliente;
import com.bazar.api.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    IClienteRepository cliRepo;
    
    @Override
    public String crearCliente(Cliente cli) {
        cliRepo.save(cli);
        return "Cliente guardado correctamente";
    }

    @Override
    public void editarCliente(Long idCliente, String nuevoNombre, String nuevoApellido, String nuevoDni) {
        
        Cliente cli = this.cliRepo.findById(idCliente).orElse(null);
    
        if (cli != null) {
            cli.setNombre(nuevoNombre);
            cli.setApellido(nuevoApellido);
            cli.setDni(nuevoDni);
        
            this.crearCliente(cli);
        } else {
            System.out.println("El usuario no existe");
        }
}

    @Override
    public String borrarCliente(Long idCliente) {
        cliRepo.deleteById(idCliente);
        return "Cliente borrado correctamente";
    }

    @Override
    public List<Cliente> traerClientes() {
        List<Cliente> listaClientes = cliRepo.findAll();
        return listaClientes;
    }

    @Override
    public Cliente buscarCliente(Long idCliente) {
        Cliente cli = cliRepo.findById(idCliente).orElse(null);
        return cli;
    }

}
