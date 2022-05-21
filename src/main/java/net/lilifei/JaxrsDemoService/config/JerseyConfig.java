package net.lilifei.JaxrsDemoService.config;

import net.lilifei.JaxrsDemoService.controller.PingController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(PingController.class);
    }
}
