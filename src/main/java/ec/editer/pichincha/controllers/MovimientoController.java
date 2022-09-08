/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.controllers;

import ec.editer.pichincha.entities.dtos.MovimientoDTO;
import ec.editer.pichincha.responses.HttpResponse;
import ec.editer.pichincha.services.IMovimientoService;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @GetMapping("/reportes")
    ResponseEntity<Iterable<MovimientoDTO>> getMovimientos(@RequestParam(value = "fecha_inicial", required = true) String fechaInicial,
            @RequestParam(value = "fecha_final", required = true) String fechaFin,
            @RequestParam(value = "cliente_id", required = true) Integer clienteId) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaI = new Date(0);
        Date fechaF = new Date(0);
        try {
            java.util.Date fi = format.parse(fechaInicial);
            java.util.Date ff = format.parse(fechaFin);
            fechaI = new Date(fi.getTime());
            fechaF = new Date(ff.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.ok(movimientoService.getMovimientos(fechaI, fechaF, clienteId));
    }

    @PostMapping
    ResponseEntity<HttpResponse> postMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        HttpResponse response = new HttpResponse();
        BigDecimal valor;

        // Se valida el signo del valor
        if ("deposito".equalsIgnoreCase(movimientoDTO.getTipo())) {
            if (movimientoDTO.getValor().doubleValue() < 0) {
                valor = movimientoDTO.getValor();
                valor = valor.multiply(new BigDecimal(-1));
                movimientoDTO.setValor(valor);
            }
        } else {
            if (movimientoDTO.getValor().doubleValue() > 0) {
                valor = movimientoDTO.getValor();
                valor = valor.multiply(new BigDecimal(-1));
                movimientoDTO.setValor(valor);
            }
        }

        try {
            movimientoService.crear(movimientoDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Movimiento registrado exitosamente");
            response.setTipo("exitoso");
        } catch (Exception ex) {
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping
    ResponseEntity<HttpResponse> putMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        HttpResponse response = new HttpResponse();
        BigDecimal valor;

        // Se valida el signo del valor
        if ("deposito".equalsIgnoreCase(movimientoDTO.getTipo())) {
            if (movimientoDTO.getValor().doubleValue() < 0) {
                valor = movimientoDTO.getValor();
                valor = valor.multiply(new BigDecimal(-1));
                movimientoDTO.setValor(valor);
            }
        } else {
            if (movimientoDTO.getValor().doubleValue() > 0) {
                valor = movimientoDTO.getValor();
                valor = valor.multiply(new BigDecimal(-1));
                movimientoDTO.setValor(valor);
            }
        }

        try {
            movimientoService.actualizar(movimientoDTO);
            response.setEstado(HttpStatus.OK.value());
            response.setMensaje("Movimiento actualizado exitosamente");
            response.setTipo("exitoso");
        } catch (Exception ex) {
            response.setEstado(HttpStatus.EXPECTATION_FAILED.value());
            response.setMensaje(ex.getMessage());
            response.setTipo("error");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteMovimiento(@PathVariable("id") Integer id) {
        movimientoService.eliminar(id);
        return ResponseEntity.ok(id);
    }
}
