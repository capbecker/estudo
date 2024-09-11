package recursosJ8;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Classe que da continuidade a MainOrdenaPt2, apresentando o LocalDate
 */
public class MainOrdenaPt3 {
    public static void main (String args[]) {
        LocalDate   hoje = LocalDate.now();
        LocalDate   proxNiver;
        MonthDay    meuNiver = MonthDay.of(8,26);
        if (Period.between(
            LocalDate.of(hoje.getYear(), meuNiver.getMonth(),meuNiver.getDayOfMonth()), hoje).isNegative()) {
            proxNiver = LocalDate.of(hoje.getYear(), meuNiver.getMonth(),meuNiver.getDayOfMonth());
        } else {
            proxNiver = LocalDate.of(hoje.getYear()+1, meuNiver.getMonth(),meuNiver.getDayOfMonth());
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i<5; i++) {
            System.out.println("O niver de "+proxNiver.getYear()+" cai "+proxNiver.getDayOfWeek());
            proxNiver = proxNiver.plusYears(1);
        }

    }
}
