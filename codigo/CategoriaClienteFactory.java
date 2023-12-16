import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriaClienteFactory {
    private static final List<String> LISTA_CATEGORIAS = new ArrayList<>(Arrays.asList("Horista", "Turnista", "Mensalista"));

    public static List<String> getListaCategorias(){
        return new ArrayList<String>(LISTA_CATEGORIAS);
    }

    public static ICategoriaCliente getCategoriaByName(String nomeCategoria){
        switch(nomeCategoria){
            case "Turnista": {
                return new Turnista();
            }
            case "Mensalista": {
                return new Mensalista();
            }
            default: {
                return new Horista();
            }
        }
    }

    public static ICategoriaCliente getPadrao() {
        return new Horista();
    }
}
