package br.com.fcemim.paradigmaFuncional;

public class ExemploRecursividade {
    public static void main(String[] args) {
        // 5 * 4 * 3 * 2 * 1 = 120
        System.out.println(fatorial(5));
    }

    public static int fatorial (int value){
        if (value == 1) {
            return value;
        } else {
            return value * fatorial((value - 1));
        }
    }
}
