package principal;

import recursos.*;
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
            for (String line : Files.readAllLines(Paths.get(path))) {
                ArrayList<String> entrada=new ArrayList<>();
                for (String part : line.split("\\s+")) {
                    entrada.add(part);
                }

                Item novoItem = new Item(Long.parseLong(entrada.get(0)),Long.parseLong(entrada.get(2)));
                if(!cache.buscarChave((String)entrada.get(1), novoItem))
                    cache.addItem((String)entrada.get(1), novoItem);
            }
            System.out.println(cache.toString());
        } catch (Exception e) {
            e.toString();
        }
        
    }
}
