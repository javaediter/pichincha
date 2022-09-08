/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.entities.dtos;

import ec.editer.pichincha.entities.Movimiento;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edison Teran
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    private Integer movimientoId;
    private String fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
    private CuentaDTO cuentaDTO;
    
    public void parse(Movimiento entity){
        this.movimientoId = entity.getMovimientoId();
        this.fecha = entity.getFecha().toString();
        this.tipo = entity.getTipo();
        this.valor = entity.getValor();
        this.saldo = entity.getSaldo();
    }
    
    public Movimiento parse(MovimientoDTO dto){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        
        try{
            date = format.parse(dto.getFecha());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        Date f = new Date(date.getTime());
        Movimiento entity = new Movimiento();
        if(dto.getMovimientoId() != null && dto.getMovimientoId() > 0){
            entity.setMovimientoId(dto.getMovimientoId());
        }
        entity.setFecha(f);
        entity.setTipo(dto.getTipo());
        entity.setValor(dto.getValor());
        entity.setSaldo(dto.getSaldo());
        return entity;
    }
}
