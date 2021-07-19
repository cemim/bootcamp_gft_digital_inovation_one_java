package com.fcemim.core;

import com.fcemim.utils.operacao.Calculadora;

public class Runner {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();

        System.out.println(calculadora.sum(1,2));
    }
}
