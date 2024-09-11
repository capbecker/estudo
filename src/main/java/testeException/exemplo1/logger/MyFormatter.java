package testeException.exemplo1.logger;

import java.util.logging.Handler;
import java.util.logging.XMLFormatter;

/**
 * Formater que extende o XMLFormater, para aproveitar a estrutura robusta existente no XMLFormater e poder remover o
 * cabecalho se desejado
 */
public class MyFormatter extends XMLFormatter {
    Boolean showHeader = false;
    Boolean showTail = false;

    public MyFormatter setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
        return this;
    }

    public MyFormatter setShowTail(Boolean showTail) {
        this.showTail = showTail;
        return this;
    }

    @Override
    public String getHead(Handler h) {
        if (showHeader) {
            return super.getHead(h);
        } else {
            return "";
        }
    }

    @Override
    public String getTail(Handler h) {
        if (showTail) {
            return super.getTail(h);
        } else {
            return "";
        }
    }
}
