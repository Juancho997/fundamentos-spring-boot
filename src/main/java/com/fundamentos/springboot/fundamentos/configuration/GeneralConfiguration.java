package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
// habilitamos el archivo de properties de BD nuevo
@PropertySource("classpath:connection.properties")
// habilitamos la clase UserPojo para ser inyectada como dependencia
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}")
    private String name;

    @Value("${value.lastname}")
    private String lastname;

    @Value("${value.random}")
    private String random;

    //nos traemos los valores desde el archivo application.properties y las guardamos en variables
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImplement(name, lastname);
    }

    //SpringBoot inyectará esta dependencia para así poder conectar con la BD
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        //se las pasamos a la configuración de la BD
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
