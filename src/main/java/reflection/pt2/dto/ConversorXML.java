package reflection.pt2.dto;

import reflection.pt2.annotations.NomeTagXml;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {
    public String converte(Object obj) {
        StringBuilder sb = new StringBuilder();
        if (obj instanceof Collection) {
            sb.append("<lista>\n");
            ((Collection) obj).forEach(o-> {
                sb.append(xmlFields(o));
            });
            sb.append("</lista>");
        } else {
            sb.append(xmlFields(obj));
        }
        return sb.toString();
    }

    public String xmlFields(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> classe = obj.getClass();
            NomeTagXml ntx = classe.getDeclaredAnnotation(NomeTagXml.class);
            String nomeClasse = ntx == null?classe.getName():ntx.value(); // Ternario aplicado para caso nao tenha a anotacao
            sb.append("<"+nomeClasse+">");
            for (Field f : classe.getDeclaredFields()) {
                f.setAccessible(true);
                NomeTagXml ntxAttr = f.getDeclaredAnnotation(NomeTagXml.class);
                String nomeAtributo = ntxAttr==null?f.getName():ntxAttr.value();
                sb.append("<").append(nomeAtributo).append(">").append(f.get(obj)).append("</")
                        .append(nomeAtributo).append(">");
            }
            sb.append("</").append(nomeClasse).append(">\n");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            sb.append("<Erro>").append(e.getMessage()).append("</Erro>\n");
        }
        return sb.toString();
    }
}
