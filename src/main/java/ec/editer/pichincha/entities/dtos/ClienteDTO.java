/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.entities.dtos;

import ec.editer.pichincha.entities.Cliente;
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
public class ClienteDTO extends PersonaDTO {

    private Integer clienteId;
    private String contrasenia;
    private Boolean estado;

    public void parse(Cliente cli) {
        this.setContrasenia(cli.getContrasenia());
        this.setEstado(cli.getEstado());
        this.setNombre(cli.getNombre());
        this.setGenero(cli.getGenero());
        this.setDireccion(cli.getDireccion());
        this.setEdad(cli.getEdad());
        this.setIdentificacion(cli.getIdentificacion());
        this.setTelefono(cli.getTelefono());
        this.setClienteId(cli.getId());
    }

    public Cliente parse(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        if(dto.getClienteId() != null && dto.getClienteId() > 0){
            cliente.setId(dto.getClienteId());
        }
        cliente.setContrasenia(dto.getContrasenia());
        cliente.setEstado(dto.getEstado());
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setDireccion(dto.getDireccion());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}
