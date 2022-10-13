package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    // inyecto la implementación de la interfaz MyOperation => MyOperationImplement
    private final MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {

        LOGGER.info("We have entered to the printWithDependency method in MyBeanWithDependencyImplement class");
        int numero = 1;
        LOGGER.debug("The number sent as a parameter to the operation dependency its : " + numero);
        System.out.println(myOperation.sum(numero));

        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
