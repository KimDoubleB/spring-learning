package com.cloud.loadbalancer.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GreetingServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;
    private final TargetServerProperty serverProperty;

    public GreetingServiceInstanceListSuppler(String serviceId, TargetServerProperty serverProperty) {
        this.serviceId = serviceId;
        this.serverProperty = serverProperty;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        var index = new AtomicInteger();
        var serviceInstances = serverProperty.getServers().stream()
                .map(server -> toServiceInstance(index, server))
                .toList();
        return Flux.just(serviceInstances);
    }

    private ServiceInstance toServiceInstance(AtomicInteger index, TargetServerProperty.Server server) {
        return new DefaultServiceInstance(this.serviceId + index.getAndIncrement(),
                this.serviceId,
                server.getHost(),
                server.getPort(),
                false);
    }

}
