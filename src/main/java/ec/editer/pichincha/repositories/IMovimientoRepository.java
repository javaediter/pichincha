/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.repositories;

import ec.editer.pichincha.entities.Movimiento;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Edison Teran
 */
public interface IMovimientoRepository extends CrudRepository<Movimiento, Integer> {

    @Query("select m "
            + "from Movimiento m, Cuenta q, Cliente c "
            + "where m.cuenta.cuentaId = q.cuentaId "
            + "and q.cliente.id = c.id "
            + "and m.fecha >= ?1 "
            + "and m.fecha <= ?2 "
            + "and c.id = ?3"
    )
    Iterable<Movimiento> getMovimientos(Date fechaInicial, Date fechaFinal, Integer clienteId);
    
    @Query("select sum(m.valor) "
            + "from Movimiento m, Cuenta q, Cliente c "
            + "where m.cuenta.cuentaId = q.cuentaId "
            + "and q.cliente.id = c.id "
            + "and m.fecha = ?1 "
            + "and c.id = ?2 "
            + "and q.cuentaId = ?3 "
            + "and m.tipo = ?4"
    )
    BigDecimal getTotalDiario(Date fecha, Integer clienteId, Integer cuentaId, String tipoMovimiento);
    
    @Query("select m.saldo "
            + "from Movimiento m, Cuenta q, Cliente c "
            + "where m.cuenta.cuentaId = q.cuentaId "
            + "and q.cliente.id = c.id "
            + "and c.id = ?1 "
            + "and q.cuentaId = ?2 "
            + "order by m.movimientoId desc"
    )
    List<BigDecimal> getSaldos(Integer clienteId, Integer cuentaId);
}
