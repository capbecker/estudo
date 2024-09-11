package reflection.pt2.produto.ioc;

import generico.Geral;
import reflection.pt2.produto.dao.ProdutoDao;
import reflection.pt2.produto.dao.ProdutoDaoMock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;

public class ContainerIoC {
    //Lista de conversao de tipos, para, por exemplo, ao encontrar um list, instanciar um arraylist
    private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

    public Object getInstancia(Class<?> tipoFonte) {
        Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);
        if (tipoDestino!=null) {
            return getInstancia(tipoDestino);
        }
        Stream<Constructor<?>> contrutores = Stream.of(tipoFonte.getDeclaredConstructors());

        Optional<Constructor<?>> padrao = contrutores
            .filter(constructor -> constructor.getParameterCount() == 0)
            .findFirst();

        try {
            if (padrao.isPresent()) {
               return padrao.get().newInstance();
            } else {
                Constructor<?> contrutor = tipoFonte.getDeclaredConstructors()[0];
                List<Object> params = new ArrayList<>();
                for (Parameter param: contrutor.getParameters()) {
                    Class<?> tipoDoParametro = param.getType();
                    params.add(getInstancia(tipoDoParametro));
                }
                return contrutor.newInstance(params.toArray());
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void registra(Class<T> tipoFonte, Class<? extends T> tipoDestino) {
        if (verficaCast(tipoFonte, tipoDestino)) { //essa verificação agora é redundante
            mapaDeTipos.put(tipoFonte, tipoDestino);
        } else {
            throw new ClassCastException("nao rolou");
        }
    }

    public Boolean verficaCast(Class<?> tipoFonte, Class<?> tipoDestino) {
        //feito na raça
//        return Stream.of(tipoDestino.getInterfaces()).anyMatch(eachDestino->eachDestino.equals(tipoFonte))
//                || tipoFonte.equals(tipoDestino)
//                || tipoDestino.getSuperclass().equals(tipoFonte)
//        ;
        return tipoFonte.isAssignableFrom(tipoDestino);
    }
}
