package com.cloud.loadbalancer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "target")
@Getter @Setter
public class TargetServerProperty {

    private List<Server> servers;


    @Getter @Setter
    public static class Server {

        private String host;
        private Integer port;

    }

}
