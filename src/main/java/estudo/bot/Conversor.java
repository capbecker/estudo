package estudo.bot;

import estudo.bot.request.annotations.RequestField;
import generico.Geral;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

public class Conversor {
    //Converte de uma classe generica para outra - que possuam mesmos parametros ou pelo menos anotações "RequestField"
    // que indiquem seus nomes equialentes
    public static <T,K> T convert(K origem, Class<T> destino) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException {
        Map<String, Object> curFields = new HashMap();
        for (Field f: origem.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            RequestField anotacao = f.getAnnotation(RequestField.class);
            String nomeCampo;
            nomeCampo = (anotacao == null) ? f.getName() : anotacao.value();
            curFields.put(nomeCampo, f.get(origem));
        }
        Optional<Constructor<?>> construtor =
                Stream.of(destino.getDeclaredConstructors())
                        .sorted(Comparator.comparingInt(c -> c.getParameterCount())).findFirst();
        if (construtor.isPresent()) {
            List<Object> paramsMetodo = new ArrayList<>();
            Stream.of(construtor.get().getParameters())
                    .forEach(p->paramsMetodo.add(curFields.get(p.getName())));
            T retorno = (T) (paramsMetodo.size()>0?
                    construtor.get().newInstance(paramsMetodo.toArray()):construtor.get().newInstance());
            curFields.forEach((nome, valor)-> {
                try {
                    Field f = destino.getDeclaredField(nome);
                    f.setAccessible(true);
                    f.set(retorno, valor);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return retorno;
        }
        throw new ClassNotFoundException("nop");
    }

    //Tenta converter a variavel "var" para
    public static Object identificaEConverte(String var) {
        return Geral.coalesce(
                tryValueOf(var, Integer.class),
                tryValueOf(var, Float.class),
                tryValueOf(var, Double.class),
                tryValueOf(var, Long.class),
                var
        );
    }

    //Tenta efetuar um "valueOf"
    public static Object tryValueOf(String var, Class<?> numero) {
        try {
            // Metodo estatico
            return numero.getMethod("valueOf",String.class).invoke(null,var);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
            System.out.print(numero.getName()+": ");
            e.printStackTrace();
        }
        return null;
    }
}
