/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

import ec.editer.pichincha.entities.Cliente;
import ec.editer.pichincha.entities.Cuenta;
import ec.editer.pichincha.entities.dtos.ClienteDTO;
import ec.editer.pichincha.entities.dtos.CuentaDTO;
import ec.editer.pichincha.repositories.IClienteRepository;
import ec.editer.pichincha.repositories.ICuentaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edison Teran
 */
@Service
@Transactional
public class CuentaService implements ICuentaService{
    
    @Autowired
    private ICuentaRepository cuentaRepository;
    
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public Iterable<CuentaDTO> buscarPorCliente(Integer clienteId) {
        List<CuentaDTO> cuentas = new ArrayList<>();
        Iterable<Cuenta> resultado = cuentaRepository.findByCliente(clienteId);
        resultado.forEach(q -> {
            ClienteDTO cliDTO = new ClienteDTO();
            cliDTO.parse(q.getCliente());
            CuentaDTO dto = new CuentaDTO();
            dto.setClienteDTO(cliDTO);
            dto.parse(q);
            cuentas.add(dto);
        });
        return cuentas;
    }

    @Override
    public CuentaDTO crear(CuentaDTO dto) {
        Cuenta q = dto.parse(dto);
        Cliente cli = clienteRepository.findById(dto.getClienteDTO().getClienteId()).get();
        q.setCliente(cli);
        cuentaRepository.save(q);
        dto.setCuentaId(q.getCuentaId());
        return dto;
    }

    @Override
    public CuentaDTO actualizar(CuentaDTO dto) {
        Cuenta q = dto.parse(dto);
        Cliente cli = clienteRepository.findById(dto.getClienteDTO().getClienteId()).get();
        q.setCliente(cli);
        cuentaRepository.save(q);
        return dto;
    }

    @Override
    public void eliminar(Integer id) {
        cuentaRepository.deleteById(id);
    }
    
}
