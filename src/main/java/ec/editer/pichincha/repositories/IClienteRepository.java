/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.repositories;

import ec.editer.pichincha.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Edison Teran
 */
public interface IClienteRepository extends CrudRepository<Cliente, Integer>{
    
}
