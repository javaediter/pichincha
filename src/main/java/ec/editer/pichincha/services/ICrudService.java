/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.services;

/**
 *
 * @author Edison Teran
 * @param <T>
 */
public interface ICrudService<T> {
    T crear(T dto) throws Exception;
    T actualizar(T dto) throws Exception;
    void eliminar(Integer id);
}
