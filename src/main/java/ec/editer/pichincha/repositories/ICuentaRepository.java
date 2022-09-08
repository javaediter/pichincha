/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.repositories;

import ec.editer.pichincha.entities.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Edison Teran
 */
public interface ICuentaRepository extends CrudRepository<Cuenta, Integer> {

    @Query("select q "
            + "from Cuenta q, Cliente c "
            + "where q.cliente.id = c.id "
            + "and c.id = ?1")
    Iterable<Cuenta> findByCliente(Integer clienteId);
}
