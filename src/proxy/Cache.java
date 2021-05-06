package proxy;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String,Item>mapa;
    private int quantidade;
    private long totalBytes;
    private long bytesEconomizados;
    
    public Cache(){
        this.totalBytes=0;
        this.bytesEconomizados=0;
        this.quantidade=0;
        this.mapa=new HashMap<>();
    }
    
    
    /**
     * Adiciona um recurso ao cache
     * <ul><li>Caso k seja menor do que 0 dispara uma exceção
     * <li>Caso m seja menor do que 1 dispara uma exceção</ul>
     * @param   recurso    uma url contendo o recurso
     * @param   item   um objeto do tipo Item contendo o instante de acesso
     * e o tamanho em bytes do recurso.
     * @throws Exception
     */
    public void addRegistro(String recurso,Item item) throws Exception{
        try {
            mapa.put(recurso,item);
            totalBytes+=item.getTamanho();
            this.quantidade++;
        }catch(Exception e){
            throw new Exception("Ocorreu um erro ao adicionar o recurso ao cache\nErro: "+e.toString());
        } 
        
    }
    
    public Boolean buscarChave(String recurso){
        return mapa.containsKey(recurso);
    }
    
    public void alterarRegistro (String recurso, Item item) throws Exception{
        try {
            Item recursoEmCache = mapa.get(recurso);
            recursoEmCache.alterarTempo(item.getTempo());
            this.bytesEconomizados+=item.getTamanho();
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao alterar o recurso no cache\nErro: "+e.toString());
        }
        
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Recursos presentes em cache:\n");
        for (Map.Entry<String, Item> entry : mapa.entrySet()) {
            sb.append(String.format("Recurso: %s , %s\n", entry.getKey(),entry.getValue()));
        }
        
        sb.append(String.format("\nQuantidade total de páginas em cache: %d\n", this.quantidade));
        sb.append(String.format("Tamanho total: %d\n", this.totalBytes));
        sb.append(String.format("Total de bytes economizados: %d", this.bytesEconomizados));
        return sb.toString();
    }
    
}

