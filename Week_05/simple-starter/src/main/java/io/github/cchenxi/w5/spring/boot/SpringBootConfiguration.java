package io.github.cchenxi.w5.spring.boot;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cchenxi.w5.spring.boot.obj.Klass;
import io.github.cchenxi.w5.spring.boot.obj.School;
import io.github.cchenxi.w5.spring.boot.obj.Student;
import io.github.cchenxi.w5.spring.boot.prop.SpringBootPropertiesConfiguration;
import lombok.RequiredArgsConstructor;

/**
 * spring boot starter configuration
 * <p>
 * Date: 2020-11-18
 *
 * @author chenxi
 */
@Configuration
@EnableConfigurationProperties(SpringBootPropertiesConfiguration.class)
@ConditionalOnProperty(prefix = "spring.simple", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SpringBootConfiguration {

    private final SpringBootPropertiesConfiguration props;

    @Bean
    public Student s1() {
        String id = props.getProps().getProperty("klass.student1.id");
        String name = props.getProps().getProperty("klass.student1.name");
        return new Student(Integer.valueOf(id), name);
    }

    @Bean
    public Student student100() {
        String id = props.getProps().getProperty("klass.student100.id");
        String name = props.getProps().getProperty("klass.student100.name");
        return new Student(Integer.valueOf(id), name);
    }

    @Bean
    @ConditionalOnBean(name = {"s1", "student100"})
    @Autowired
    public Klass klass1(Student s1, Student student100) {
        Klass klass = new Klass();
        List<Student> studentList = Arrays.asList(s1, student100);
        klass.setStudents(studentList);
        return klass;
    }

    @Bean
    @ConditionalOnBean(name = {"klass1", "student100"})
    @Autowired
    public School school(Klass klass1, Student student100) {
        School school = new School();
        school.setClass1(klass1);
        school.setStudent100(student100);
        return school;
    }
}
