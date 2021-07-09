package javaDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Exercicio 1
 *
 * - Descubra o timeLnMillis do dia que você nasceu;
 * - Converta em um objeto Date;
 * - Verifique se ela é anterior ou posterior a 15 de maio de 2010.
 *
 * Epoch 1563385317992
 *
 * Ponto de atenção: como você está manipulando um numero do tipo long, dependendo da maneira que você estiver
 *                   manipulando essa informação, você precisa colocar a letra l no final do numero
 */

public class Exercicio001 {


    public static void main(String[] args) throws ParseException {

        Exercicio001 e = new Exercicio001();

        DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dataAniversario = data.parse ("1989-10-07 00:00");

        Long dataAniversarioL = e.dateToLong(dataAniversario);

        e.checaLong(dataAniversarioL);

    }

    public Date checaLong(long epoch) throws ParseException {

        Date dataAniversario = new Date(epoch);

        DateFormat dataNova = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date dataComparacao = dataNova.parse ("2010-05-15 00:00");


        boolean isBefore = dataAniversario.before(dataComparacao);

        // Se for anterior
        if (isBefore){
            System.out.println("O aniverssario " + dataAniversario.toInstant() + "  é anterior a data comparada " + dataComparacao.toInstant());
        } else {
            System.out.println("O aniverssario " + dataAniversario.toInstant() + "  é Posterior a data comparada " + dataComparacao.toInstant());
        }
        return null;
    }

    public long dateToLong(Date data) {

        long dataLong = data.getTime();

        return dataLong;
    }

}
