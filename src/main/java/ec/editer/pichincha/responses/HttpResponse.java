/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.responses;

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
public class HttpResponse {
    private Integer estado;
    private String tipo;
    private String mensaje;
}
