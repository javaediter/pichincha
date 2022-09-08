/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.entities.dtos;

import ec.editer.pichincha.entities.Cuenta;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edison Teran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

    private Integer cuentaId;
    private String numero;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private ClienteDTO clienteDTO;

    public void parse(Cuenta q) {
        this.setCuentaId(q.getCuentaId());
        this.setNumero(q.getNumero());
        this.setTipo(q.getTipo());
        this.setSaldoInicial(q.getSaldoInicial());
        this.setEstado(q.getEstado());
    }
    
    public Cuenta parse(CuentaDTO dto){
        Cuenta q = new Cuenta();
        if(dto.getCuentaId() != null && dto.getCuentaId() > 0){
            q.setCuentaId(dto.getCuentaId());
        }
        q.setEstado(dto.getEstado());
        q.setNumero(dto.getNumero());
        q.setTipo(dto.getTipo());
        q.setSaldoInicial(dto.getSaldoInicial());
        return q;
    }
}
