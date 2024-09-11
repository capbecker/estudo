package testeException.exemplo1.logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.*;

/**
 * Classe que alimenta 2 logs - 1 simplificado e 1 detalhado
 * Se quiser o log bem personalizado, basta criar um novo Formatter e atribuir ao FileHandler
 */
public class MyLogToFile {
    public static final Level TIPO_SEVERE = Level.SEVERE ;
    public static final Level TIPO_WARNING = Level.WARNING ;
    public static final Level TIPO_INFO = Level.INFO ;
    public static final Level TIPO_CONFIG = Level.CONFIG ;
    public static final Level TIPO_FINE = Level.FINE ;
    public static final Level TIPO_FINER = Level.FINER ;
    public static final Level TIPO_FINEST = Level.FINEST ;

    protected static final Logger logger = Logger.getLogger("MYLOG");

    public static void log(Throwable t, Level level) {
        log(t,level,null);
    }

    public static void log(Throwable t, Level level, String msg) {
        LocalDate ld = LocalDate.now();
        String path1 = "src/main/java/testeException/exemplo1/logger/log/";
        String path2 = "src/main/java/testeException/exemplo1/logger/log/simple/";
        String nomeArquivo = new StringBuilder()
                .append(ld.getDayOfMonth()>9?ld.getDayOfMonth():"0"+ld.getDayOfMonth())
                .append("_"+ld.getMonthValue()).append("_"+ld.getYear()).toString();
        String extensao1 = ".xml";
        String extensao2 = ".txt";
        FileHandler fh = null;
        FileHandler fh2 = null;
        try {
            fh = new FileHandler(path1+nomeArquivo+extensao1, true);
            fh.setFormatter(new MyFormatter());
            fh2 = new FileHandler(path2+nomeArquivo+extensao2, true);
            fh2.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.addHandler(fh2);
            logger.log(level, msg==null?t.getMessage():msg, t);
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally {
            if (fh != null) fh.close();
            if (fh2 != null) fh2.close();
        }
    }

//-- Se eventualmente eu quiser que o header e tail sejam adicionados
//    protected static String nomeArquivo;

//    public static void inicializa() throws IOException {
//        LocalDate ld = LocalDate.now().plusDays(1);
//        String curNomeArquivo = new StringBuilder().append("src/main/java/testeException/exemplo1/logger/log/")
//                .append(ld.getDayOfMonth()>9?ld.getDayOfMonth():"0"+ld.getDayOfMonth())
//                .append("_"+ld.getMonthValue()).append("_"+ld.getYear()).append(".xml").toString();
//        File lastArquivo;// = new File(nomeArquivo);
//
//        //-- Se a variavel ainda não foi inicializada --
//        if (nomeArquivo == null) {
//            lastArquivo = new File(curNomeArquivo);
//            nomeArquivo = curNomeArquivo;
//        } else {
//            lastArquivo = new File(nomeArquivo);
//        }
//
//        //-- Se o log existir
//        if (lastArquivo.exists()) {
//            //-- Se não ser o de hoje - encerra o do dia anterior e incializa o novo
//            if (lastArquivo.exists() && curNomeArquivo != nomeArquivo) {
//                addHeaderTail(false, true);
//                nomeArquivo = curNomeArquivo;
//                addHeaderTail(true, false);
//            }
//            //Se o log não existir, inicaliza
//        } else {
//            nomeArquivo = curNomeArquivo;
//            addHeaderTail(true, false);
//
//        }
//    }

//    private static void addHeaderTail(Boolean header, Boolean tail) throws IOException {
//        FileHandler fh = null;
//        try {
//            fh = new FileHandler(nomeArquivo, true);
//            fh.setFormatter(new MyFormatter().setShowHeader(header).setShowTail(tail));
//        } finally {
//            if (fh != null) fh.close();
//        }
//    }
}