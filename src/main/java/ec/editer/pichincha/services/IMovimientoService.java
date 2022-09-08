/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

import ec.editer.pichincha.entities.dtos.MovimientoDTO;
import java.sql.Date;

/**
 *
 * @author Edison Teran
 */
public interface IMovimientoService extends ICrudService<MovimientoDTO>{
    Iterable<MovimientoDTO> getMovimientos(Date fechaInicial, Date fechaFinal, Integer clienteId);
}
