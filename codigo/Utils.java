public class Utils {
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

    public static String fillSpacesToLimit(String string, int limit){
        if(string.length() > limit){
            return string.substring(0, limit - 4) + "...";
        }else return string + " ".repeat(limit - string.length());
    }
}
