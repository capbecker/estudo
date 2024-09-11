package reflection.pt1;

import reflection.pt1.dto.delegador.Reflexao;
import reflection.pt1.dto.delegador.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionPt6 {
    private String base;

    public ReflectionPt6(String base) {
        this.base = base;
    }

    public ReflectionPt6() {
        this.base = "reflection.pt1";
    }

    public Object executa1(String path) {
        // ambos invokeds funcionam, mas fiz o primeiro para nao instanciar ver uma forma alternativa
        Request request = new Request(this.base, path);
        Reflexao ref = new Reflexao();
        Object instancia = ref.refleteClasse(request.getCaminhoCompleto()).getConstrutor().invoca();
        try {
            Method method = ref.getController().getDeclaredMethod(request.getNomeMetodo());
            return method.invoke(instancia); //op1
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public Object executa2(String path) {
        // ambos invokeds funcionam, mas fiz o primeiro para nao instanciar ver uma forma alternativa
        Request request = new Request(this.base, path);
        Reflexao ref = new Reflexao();
        return new Reflexao().refleteClasse(request.getCaminhoCompleto()).invocaInstancia()
            .getMetodo(request.getNomeMetodo()).invoca();

    }
}
