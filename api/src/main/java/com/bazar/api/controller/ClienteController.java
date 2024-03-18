package com.bazar.api.controller;

import com.bazar.api.model.Cliente;
import com.bazar.api.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    ClienteService cliServ;
    
    @PostMapping("/cliente/crear")
    public Cliente crearCliente(@RequestBody Cliente cli){
        cliServ.crearCliente(cli);
        return cli;
    }
    
    @GetMapping("/clientes")
    public List<Cliente> traerClientes(){
        return cliServ.traerClientes();
    }
    
    @GetMapping("/cliente/{idCliente}")
    public Cliente buscarCliente(@PathVariable Long idCliente){
        return cliServ.buscarCliente(idCliente);
    }
    
    @DeleteMapping("/cliente/borrar/{idCliente}")
    public String borrarCliente(@PathVariable Long idCliente){
        return cliServ.borrarCliente(idCliente);
    }
    
    @PutMapping("/cliente/editar/{idCliente}")
    public ResponseEntity<String> editarCliente(@PathVariable Long idCliente,
                                                @RequestParam String nuevoNombre,
                                                @RequestParam String nuevoApellido,
                                                @RequestParam String nuevoDni) {
        cliServ.editarCliente(idCliente, nuevoNombre, nuevoApellido, nuevoDni);
        
        return ResponseEntity.ok("Cliente editado correctamente");
    }
}
