package com.bb.app.importselector;

import com.bb.common.Payload.Type;
import com.bb.common.config.JsonCommunicationConfig;
import com.bb.common.config.ProtobufCommunicationConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Objects;

public class CommunicationIConfigSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributesMap = importingClassMetadata.getAnnotationAttributes(
                EnableCommunicationUsingSelector.class.getName(), false);
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(attributesMap);

        Type type = Objects.requireNonNull(attributes).getEnum("type");
        return switch (type) {
            case JSON -> new String[]{JsonCommunicationConfig.class.getName()};
            case PROTOBUF -> new String[]{ProtobufCommunicationConfig.class.getName()};
        };
    }

}
