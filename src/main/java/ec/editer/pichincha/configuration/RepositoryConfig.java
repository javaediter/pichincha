/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.editer.pichincha.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Edison Teran
 */
@Configuration
@EnableJpaRepositories(basePackages = {"ec.editer.pichincha.repositories"})
public class RepositoryConfig {
    
}
