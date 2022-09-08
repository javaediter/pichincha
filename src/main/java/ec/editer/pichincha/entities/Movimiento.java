/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edison Teran
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movimientos")
public class Movimiento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movimientoId;
    private Date fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
    
    @ManyToOne
    @JoinColumn(name = "cuentaId")
    private Cuenta cuenta;
}
