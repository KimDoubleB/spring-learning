package com.bb.app.importBeanDefinitionRegistrar;

import com.bb.common.Payload;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

public class CommunicationConfigRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(
                EnableCommunicationUsingBeanRegistrar.class.getName(), false);
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(attributesMap);

        Payload.Type type = Objects.requireNonNull(attributes).getEnum("type");
        BeanDefinition beanDefinition = switch (type) {
            case JSON -> new RootBeanDefinition("com.bb.common.config.JsonCommunicationConfig");
            case PROTOBUF -> new RootBeanDefinition("com.bb.common.config.ProtobufCommunicationConfig");
        };

        registry.registerBeanDefinition("communicationPayload", beanDefinition);
    }

}
