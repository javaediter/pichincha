/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

import ec.editer.pichincha.entities.Cliente;
import ec.editer.pichincha.entities.dtos.ClienteDTO;
import ec.editer.pichincha.repositories.IClienteRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edison Teran
 */
@RequiredArgsConstructor
@Service
@Transactional
public class ClienteService implements IClienteService {

    private final IClienteRepository repository;

    @Override
    public Iterable<ClienteDTO> todos() {
        List<ClienteDTO> todos = new ArrayList<>();
        Iterable<Cliente> clientes = repository.findAll();
        clientes.forEach(cli -> {
            ClienteDTO dto = new ClienteDTO();
            dto.parse(cli);
            todos.add(dto);
        });
        return todos;
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cli = dto.parse(dto);
        repository.save(cli);
        dto.setClienteId(cli.getId());
        return dto;
    }

    @Override
    public ClienteDTO actualizar(ClienteDTO dto) {
        repository.save(repository.save(dto.parse(dto)));
        return dto;
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

}
