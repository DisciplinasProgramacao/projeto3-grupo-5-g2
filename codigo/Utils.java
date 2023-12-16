public class Utils {


    /**
     * Centraliza um texto com base em outro texto.
     *
     * @param textoParaCentralizar Texto para ser centralizado
     * @param baseParaCentralizar Texto base para centralização
     * @return Texto centralizado.
     */
    public static String centralizarTexto(String textoParaCentralizar, String baseParaCentralizar){

        StringBuilder centralizadoBuilder = new StringBuilder();

        int i = 1;
        while(centralizadoBuilder.length() < baseParaCentralizar.length()){
            if(i == (baseParaCentralizar.length() / 2) - (textoParaCentralizar.length() / 2)) centralizadoBuilder.append(textoParaCentralizar);
            else centralizadoBuilder.append(" ");
            i++;
        }

        return centralizadoBuilder.toString();
    }

    /**
     * Centraliza um texto com base em outro texto.
     *
     * @param string String para se preencher até o limite
     * @param limit Limite para ser preenchido
     * @return Texto preenchido.
     */
    public static String fillSpacesToLimit(String string, int limit){
        if(string.length() > limit){
            return string.substring(0, limit - 4) + "...";
        }else return string + " ".repeat(limit - string.length());
    }
}
