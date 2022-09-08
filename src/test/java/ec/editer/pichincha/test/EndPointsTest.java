/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.test;

import ec.editer.pichincha.controllers.ClienteController;
import ec.editer.pichincha.entities.dtos.ClienteDTO;
import ec.editer.pichincha.responses.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Edison Teran
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ClienteController.class)
public class EndPointsTest {
    
    @Autowired
    private MockMvc mvc;
    
    //@MockBean
    //private ClienteService service;
    
    @Autowired
    private JacksonTester<ClienteDTO> jsonRequestCliente;
    
    @Autowired
    private JacksonTester<HttpResponse> jsonResponseCliente;
    
    @Test
    public void testPostCliente() throws Exception{
        ClienteDTO cliente = new ClienteDTO();
        cliente.setContrasenia("123");
        cliente.setEstado(true);
        cliente.setNombre("Tester");
        cliente.setGenero("hombre");
        cliente.setDireccion("Quito");
        cliente.setEdad(30);
        cliente.setIdentificacion("17...");
        cliente.setTelefono("101");
        
        HttpResponse expectedResponse = new HttpResponse();
        expectedResponse.setEstado(HttpStatus.OK.value());
        expectedResponse.setMensaje("Cliente registrado exitosamente");
        expectedResponse.setTipo("exitoso");
        
        //BDDMockito.given(service.crear(cliente)).willReturn(cliente);
        
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequestCliente.write(cliente).getJson()))
                .andReturn().getResponse();
        
        BDDMockito.then(response.getStatus()).equals(HttpStatus.OK.value());
        
        BDDMockito.then(response.getContentAsString())
                .equals(jsonResponseCliente.write(expectedResponse).getJson());
                
    }
}
