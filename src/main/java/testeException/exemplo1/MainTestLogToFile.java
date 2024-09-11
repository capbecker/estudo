package testeException.exemplo1;

import testeException.exemplo1.logger.MyLogToFile;

/**
 * Classe para testar os logs gerados
 */
public class MainTestLogToFile {

    public static void main (String args[]) {
        //Exception ex, String level, String msg)
//        LogToFile.initialize();
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_SEVERE,"-------- Erro 1 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_CONFIG,"-------- Erro 2 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_FINE,"-------- Erro 3 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_FINER,"-------- Erro 4 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_FINEST,"-------- Erro 5 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_INFO,"-------- Erro 6 ----------");
        MyLogToFile.log(new ArithmeticException(), MyLogToFile.TIPO_WARNING,"-------- Erro 7 ----------");
    }
}
