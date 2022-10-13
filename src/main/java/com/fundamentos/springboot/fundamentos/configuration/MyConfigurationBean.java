package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// le indicamos a Spring que esto será una configuración adicional de nuestros Beans
@Configuration
public class MyConfigurationBean {

    //mis beans propios

    @Bean
    public MyBean beanOperation(){
        //return new MyBeanImplement();
        //en vez de llamar al @Qualifier, retornamos la implementación aquí
        return new MyBean2Implement();
    };


    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    };

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
