package testeException.exemplo1_1;

/**
 * Classe que da continuidade ao MainTestLogToFile, gerando os logs no tratador de exceções não tratadas
 */
public class MainTestLogToFilePt2 {

    public static void main (String args[]) {
        ThreadExceptionHandler teh = new ThreadExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(teh);
        throw new NullPointerException("abc");
    }
}
