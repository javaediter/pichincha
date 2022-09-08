/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

import ec.editer.pichincha.entities.Cuenta;
import ec.editer.pichincha.entities.Movimiento;
import ec.editer.pichincha.entities.dtos.ClienteDTO;
import ec.editer.pichincha.entities.dtos.CuentaDTO;
import ec.editer.pichincha.entities.dtos.MovimientoDTO;
import ec.editer.pichincha.exceptions.MovimientoException;
import ec.editer.pichincha.repositories.ICuentaRepository;
import ec.editer.pichincha.repositories.IMovimientoRepository;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
public class MovimientoService implements IMovimientoService{
    
    private final BigDecimal CUPO_DIARIO = new BigDecimal(1000);
    
    @Autowired
    private IMovimientoRepository movimientoRepository;
    
    @Autowired
    private ICuentaRepository cuentaRepository;
    
    @Override
    public Iterable<MovimientoDTO> getMovimientos(Date fechaInicial, Date fechaFinal, Integer clienteId) {
        List<MovimientoDTO> movimientos = new ArrayList<>();
        Iterable<Movimiento> result = movimientoRepository.getMovimientos(fechaInicial, fechaFinal, clienteId);
        result.forEach(mov -> {            
            MovimientoDTO dto = new MovimientoDTO();
            dto.parse(mov);
            Cuenta q = cuentaRepository.findById(mov.getCuenta().getCuentaId()).get();
            CuentaDTO qDTO = new CuentaDTO();
            qDTO.parse(q);
            ClienteDTO cliDTO = new ClienteDTO();
            cliDTO.parse(q.getCliente());
            qDTO.setClienteDTO(cliDTO);
            dto.setCuentaDTO(qDTO);
            movimientos.add(dto);
        });
        return movimientos;
    }

    @Override
    public MovimientoDTO crear(MovimientoDTO dto) throws MovimientoException{
        Cuenta q = cuentaRepository.findById(dto.getCuentaDTO().getCuentaId()).get();
        BigDecimal saldoCuenta = q.getSaldoInicial();
        
        List<BigDecimal> saldos = movimientoRepository.getSaldos(q.getCliente().getId(), q.getCuentaId());
        if(saldos != null && !saldos.isEmpty()){
            saldoCuenta = saldos.get(0);
        }
        
        if("deposito".equalsIgnoreCase(dto.getTipo())){
            saldoCuenta = saldoCuenta.add(dto.getValor());
        }else{
            java.util.Date hoy = Calendar.getInstance().getTime();
            Date fecha = new Date(hoy.getTime());
            
            BigDecimal totalDiario = movimientoRepository.getTotalDiario(fecha, q.getCliente().getId(), q.getCuentaId(), "retiro");
            if(totalDiario==null){
                totalDiario = new BigDecimal(0);
            }
            BigDecimal estimacion = new BigDecimal(0);
            estimacion = estimacion.add(totalDiario).add(dto.getValor()).add(CUPO_DIARIO);
            
            if(estimacion!=null && estimacion.doubleValue() < 0){
                throw new MovimientoException("Cupo diario Excedido");
            }else{
                saldoCuenta = saldoCuenta.add(dto.getValor());
            }            
        }
        
        if(saldoCuenta.doubleValue() < 0){
            throw new MovimientoException("Saldo no disponible");
        }else{
            dto.setSaldo(saldoCuenta);
        }
        
        Movimiento entity = dto.parse(dto);
        entity.setCuenta(q);
        movimientoRepository.save(entity);
        dto.setMovimientoId(entity.getMovimientoId());
        return dto;
    }

    @Override
    public MovimientoDTO actualizar(MovimientoDTO dto) throws MovimientoException{
        Cuenta q = cuentaRepository.findById(dto.getCuentaDTO().getCuentaId()).get();
        BigDecimal saldoCuenta = q.getSaldoInicial();
        
        if("deposito".equalsIgnoreCase(dto.getTipo())){
            saldoCuenta = saldoCuenta.add(dto.getValor());
        }else{
            java.util.Date hoy = Calendar.getInstance().getTime();
            Date fecha = new Date(hoy.getTime());
            
            BigDecimal totalDiario = movimientoRepository.getTotalDiario(fecha, q.getCliente().getId(), q.getCuentaId(), "retiro");
            if(totalDiario==null){
                totalDiario = new BigDecimal(0);
            }
            BigDecimal estimacion = new BigDecimal(0);
            estimacion = estimacion.add(totalDiario).add(dto.getValor()).add(CUPO_DIARIO);
            
            if(estimacion!=null && estimacion.doubleValue() < 0){
                throw new MovimientoException("Cupo diario Excedido");
            }else{
                saldoCuenta = saldoCuenta.add(dto.getValor());
            }            
        }
        
        if(saldoCuenta.doubleValue() < 0){
            throw new MovimientoException("Saldo no disponible");
        }else{
            dto.setSaldo(saldoCuenta);
        }
        
        Movimiento entity = dto.parse(dto);
        entity.setCuenta(q);
        movimientoRepository.save(entity);
        return dto;
    }

    @Override
    public void eliminar(Integer id) {
        movimientoRepository.deleteById(id);
    }
    
}
