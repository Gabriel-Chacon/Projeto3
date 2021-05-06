package proxy;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String,Item>mapa;
    private int quantidade;
    private long totalBytes;
    private long bytesEconomizados;
    private int totalRequests;
    
    public Cache(){
        this.totalBytes=0;
        this.bytesEconomizados=0;
        this.quantidade=0;
        this.totalRequests=0;
        this.mapa=new HashMap<>();
    }
    
    /**
     * Retorna a quantidade total de recursos armazenados em cache
    */
    public long getQuantidade(){
        return this.quantidade;
    }
    
    /**
     * Retorna o total de requisições feitas ao servidor
    */
    public long getTotalRequests(){
        return this.totalRequests;
    }
    
    /**
     * Retorna o total de bytes economizados
    */
    public long getBytesEconomizados(){
        return this.bytesEconomizados;
    }
    /**
     * Adiciona um recurso ao cache e incrementa a quantidade de recursos em cache, a quantidade de requests feitos e o total de bytes economizados
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
            this.totalRequests++;
        }catch(Exception e){
            throw new Exception("Ocorreu um erro ao adicionar o recurso ao cache\nErro: "+e.toString());
        } 
        
    }
    
    /**
     * Retorna um Boolean mostrando se a key está no HashMap ou não
     * @param recurso recebe uma string contendo o recurso a ser buscado
    */
    public Boolean buscarChave(String recurso){
        return mapa.containsKey(recurso);
    }
    
    /**
     * Altera o tempo de acesso ao recurso para o mais recente e incrementa a quantidade de requests feitos e o total de bytes economizados
     * @param   recurso    uma url contendo o recurso
     * @param   item   um objeto do tipo Item contendo o instante de acesso
     * e o tamanho em bytes do recurso.
     * @throws Exception
     */
    public void alterarRegistro (String recurso, Item item) throws Exception{
        try {
            Item recursoEmCache = mapa.get(recurso);
            recursoEmCache.alterarTempo(item.getTempo());
            this.bytesEconomizados+=item.getTamanho();
            this.totalRequests++;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao alterar o recurso no cache\nErro: "+e.toString());
        }
        
    }
    
    
    /**
     * Sobrescreve o método toString() da classe cache e retorna uma string personalizada
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Recursos presentes em cache:\n");
        for (Map.Entry<String, Item> entry : mapa.entrySet()) {
            sb.append(String.format("Recurso: %s , %s\n", entry.getKey(),entry.getValue()));
        }
        
        sb.append(String.format("\nQuantidade total de páginas em cache: %d\n", this.quantidade));
        sb.append(String.format("Quantidade total de requisições feitas: %d\n", this.totalRequests));
        sb.append(String.format("Tamanho total do cache armazenado: %d\n", this.totalBytes));
        sb.append(String.format("Total de bytes economizados: %d", this.bytesEconomizados));
        return sb.toString();
    }
    
}

