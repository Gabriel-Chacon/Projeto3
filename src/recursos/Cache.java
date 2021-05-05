package recursos;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String,Item>mapa;
    private long totalBytes;
    private long bytesEconomizados;
    
    public Cache(){
        totalBytes=0;
        bytesEconomizados=0;
        mapa=new HashMap<>();
    }
    
    public void addItem(String recurso,Item item){
        mapa.put(recurso,item);
        totalBytes+=item.getTamanho();
    }
    
    public Boolean buscarChave(String recurso,Item item){
        if(mapa.containsKey(recurso)){
            Item recursoEmCache = mapa.get(recurso);
            recursoEmCache.alterarTempo(item.getTempo());
            this.bytesEconomizados+=item.getTamanho();
            return true;
        }
        else return false;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Item> entry : mapa.entrySet()) {
            sb.append(String.format("Recurso: %s , %s\n", entry.getKey(),entry.getValue()));
        }
        sb.append(String.format("Tamanho total: %d\n", totalBytes));
        sb.append(String.format("Total de bytes economizados: %d\n", bytesEconomizados));
        return sb.toString();
    }
    
}

