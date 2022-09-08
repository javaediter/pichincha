/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

import ec.editer.pichincha.entities.dtos.CuentaDTO;

/**
 *
 * @author Edison Teran
 */
public interface ICuentaService extends ICrudService<CuentaDTO>{
    Iterable<CuentaDTO> buscarPorCliente(Integer clienteId);
}
