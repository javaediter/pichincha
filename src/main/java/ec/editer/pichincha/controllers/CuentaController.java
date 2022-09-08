/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.controllers;

import ec.editer.pichincha.entities.dtos.CuentaDTO;
import ec.editer.pichincha.responses.HttpResponse;
import ec.editer.pichincha.services.ICuentaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edison Teran
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

    @GetMapping
    ResponseEntity<Iterable<CuentaDTO>> getCuentasPorCliente(@RequestParam(value = "cliente_id", required = true) Integer clienteId) {
        return ResponseEntity.ok(cuentaService.buscarPorCliente(clienteId));
    }
    
    @PostMapping
    ResponseEntity<HttpResponse> postCuenta(@RequestBody CuentaDTO cuentaDTO){
        HttpResponse response = new HttpResponse();
        try{
            cuentaService.crear(cuentaDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Cuenta registrada exitosamente");
            response.setTipo("exitoso");
        }catch(Exception ex){
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }
    
    @PutMapping
    ResponseEntity<HttpResponse> putCuenta(@RequestBody CuentaDTO cuentaDTO){
        HttpResponse response = new HttpResponse();
        try{
            cuentaService.actualizar(cuentaDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Cuenta actualizada exitosamente");
            response.setTipo("exitoso");
        }catch(Exception ex){
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    ResponseEntity deleteCuenta(@PathVariable("id") Integer id){
        cuentaService.eliminar(id);
        return ResponseEntity.ok(id);
    }
}
