package reflection.pt1;

import reflection.pt1.dto.delegador.Reflexao;
import reflection.pt1.dto.delegador.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionPt7 {
    private String base;

    public ReflectionPt7(String base) {
        this.base = base;
    }

    public ReflectionPt7() {
        this.base = "reflection.pt1";
    }

    public Object executa1(String path) {
        Request request = new Request(this.base, path);
        Reflexao ref = new Reflexao();
        Object instancia = ref.refleteClasse(request.getCaminhoCompleto()).getConstrutor("teste")
                .invoca();
        try {
            Method method = ref.getController().getDeclaredMethod(request.getNomeMetodo());
            return method.invoke(instancia);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public Object executa2(String path) {
        Request request = new Request(this.base, path);
        Reflexao ref = new Reflexao();
        return new Reflexao().refleteClasse(request.getCaminhoCompleto()).invocaInstancia("teste2")
            .getMetodo(request.getNomeMetodo()).invoca();

    }
}
