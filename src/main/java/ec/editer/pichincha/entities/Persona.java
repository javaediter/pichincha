/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Edison Teran
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    protected String nombre;
    protected String genero;
    protected Integer edad;
    protected String identificacion;
    protected String direccion;
    protected String telefono;
}
