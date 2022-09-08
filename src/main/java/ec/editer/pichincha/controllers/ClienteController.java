/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.controllers;

import ec.editer.pichincha.entities.dtos.ClienteDTO;
import ec.editer.pichincha.responses.HttpResponse;
import ec.editer.pichincha.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edison Teran
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;
    
    @GetMapping
    ResponseEntity<Iterable<ClienteDTO>> getClientes(){
        return ResponseEntity.ok(clienteService.todos());
    }
    
    @PostMapping
    ResponseEntity<HttpResponse> postCliente(@RequestBody ClienteDTO clienteDTO){
        HttpResponse response = new HttpResponse();
        try{
            clienteService.crear(clienteDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Cliente registrado exitosamente");
            response.setTipo("exitoso");
        }catch(Exception ex){
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }
    
    @PutMapping
    ResponseEntity<HttpResponse> putCliente(@RequestBody ClienteDTO clienteDTO){
        HttpResponse response = new HttpResponse();
        try{
            clienteService.actualizar(clienteDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Cliente actualizado exitosamente");
            response.setTipo("exitoso");
        }catch(Exception ex){
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    ResponseEntity deleteCliente(@PathVariable("id") Integer id){
        clienteService.eliminar(id);
        return ResponseEntity.ok(id);
    }
}
