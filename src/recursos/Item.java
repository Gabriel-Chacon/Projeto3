package recursos;

public class Item {

    private long tempo;
    private long tamanho;
    
    public Item(long tempo,long bytes){
        this.tempo=tempo;
        this.tamanho=bytes;
    }
    
    public long getTempo(){
        return this.tempo;
    }
    public long getTamanho(){
        return this.tamanho;
    }
    
    void alterarTempo(long tempo){
        this.tempo=tempo;
    }
    
    @Override
    public String toString(){
        return String.format("Tempo: %d, Tamanho: %d", tempo,tamanho);

    }
}
