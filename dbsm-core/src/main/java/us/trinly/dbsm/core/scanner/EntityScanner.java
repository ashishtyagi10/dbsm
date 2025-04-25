package us.trinly.dbsm.core.scanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import javax.persistence.Entity;

public class EntityScanner {
    public List<Class<?>> scanEntities(String basePackage) throws IOException, ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = 
            new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        
        List<Class<?>> entities = new ArrayList<>();
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            entities.add(Class.forName(bd.getBeanClassName()));
        }
        return entities;
    }
}