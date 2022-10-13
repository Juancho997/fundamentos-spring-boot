package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    // inyecto la implementación de la interfaz MyOperation => MyOperationImplement
    private final MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {

        int numero = 1;

        System.out.println(myOperation.sum(numero));

        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
