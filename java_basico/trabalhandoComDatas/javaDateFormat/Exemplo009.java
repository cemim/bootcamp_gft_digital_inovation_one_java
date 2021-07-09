package javaDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Exemplo de formatação de data com SimpleDateFormat
 */
public class Exemplo009 {
    public static void main(String[] args) {


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = formato.parse("07/10/1989");
            System.out.println(formato.format(data));
            // 07/10/1989
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
