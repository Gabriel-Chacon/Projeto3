package recursos;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String,Item>mapa;
    private int quantidade;
    private long totalBytes;
    private long bytesEconomizados;
    
    public Cache(){
        this.totalBytes = 0;
        this.bytesEconomizados = 0;
        this.quantidade = 0;
        this.mapa = new HashMap<>();
    }
    
    public void addRegistro(String recurso,Item item){
        mapa.put(recurso,item);
        totalBytes += item.getTamanho();
        this.quantidade++;
    }
    
    public Boolean buscarChave(String recurso,Item item){
        if(mapa.containsKey(recurso)){
            Item recursoEmCache = mapa.get(recurso);
            recursoEmCache.alterarTempo(item.getTempo());
            this.bytesEconomizados += item.getTamanho();
            return true;
        }
        else return false;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Recursos presentes em cache:\n");
        for (Map.Entry<String, Item> entry : mapa.entrySet()) {
            sb.append(String.format("Recurso: %s , %s\n", entry.getKey(),entry.getValue()));
        }
        sb.append(String.format("Quantidade total de páginas em cache: %d\n", this.quantidade));
        sb.append(String.format("Tamanho total: %d\n", this.totalBytes));
        sb.append(String.format("Total de bytes economizados: %d\n", this.bytesEconomizados));
        return sb.toString();
    }
    
}

