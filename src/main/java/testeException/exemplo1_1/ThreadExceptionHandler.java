package testeException.exemplo1_1;

import testeException.exemplo1.logger.MyLogToFile;

/**
 * Gera os logs e trata as exceções não tratadas
 */
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        MyLogToFile.log(e, MyLogToFile.TIPO_SEVERE);
    }
}
