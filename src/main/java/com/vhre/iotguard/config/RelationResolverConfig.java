package com.vhre.iotguard.config;

import com.vhre.base.core.base.relation.JpaRelationResolver;
import com.vhre.base.core.base.relation.RelationResolver;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registra el {@link RelationResolver} que usan los servicios para materializar
 * los {@code @RelationId} de los DTOs en referencias JPA gestionadas.
 *
 * <p>Se declara de forma explicita en lugar de depender de la auto-configuracion del
 * starter porque este proyecto usa Spring Boot 4: la auto-configuracion del starter
 * (compilada contra Boot 3) se evalua antes de que el {@code EntityManagerFactory} este
 * registrado, por lo que su condicion {@code @ConditionalOnBean} falla y el bean no se
 * crea. Al declararlo aqui (las configuraciones de usuario se procesan primero), el bean
 * siempre existe y el {@code @ConditionalOnMissingBean} del starter hace que su
 * auto-configuracion se retire sin duplicar nada.</p>
 */
@Configuration
public class RelationResolverConfig {

    @Bean
    public RelationResolver relationResolver(EntityManager entityManager) {
        return new JpaRelationResolver(entityManager);
    }
}
