package principal;

import proxy.Cache;
import proxy.Item;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Cache cache = new Cache();
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o caminho do arquivo: ");
            String path = sc.nextLine();
            long startTime = System.nanoTime();
            
            for (String line : Files.readAllLines(Paths.get(path))) {
                ArrayList<String> entrada=new ArrayList<>();
                for (String part : line.split("\\s+")) {
                    entrada.add(part);
                }

                Item novoItem = new Item(Long.parseLong(entrada.get(0)),Long.parseLong(entrada.get(2)));
                if(!cache.buscarChave((String)entrada.get(1))){
                    cache.addRegistro((String)entrada.get(1),novoItem);
                }
                else{
                    cache.alterarRegistro((String)entrada.get(1), novoItem);
                }

            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println(cache.toString());
            System.out.printf("Finalizado em %.3f segundos\n", (double)timeElapsed / 1000000000);
            
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo:\n"+e.toString());
        }catch (Exception e){
            System.out.println("Ocorreu um erro:\n"+e.toString());
        }
        
    }
}
