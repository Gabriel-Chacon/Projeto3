package principal;

import java.io.File;
import java.io.FileOutputStream;
import proxy.Cache;
import proxy.Item;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            
            sb.append("Quantidade de recursos em cache,Quantidade total de requisições,Total de bytes economizados\n");
            
            String fileName1 = "Projeto3.csv";
            
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
                sb.append(cache.getQuantidade());
                sb.append(",");
                sb.append(cache.getTotalRequests());
                sb.append(",");
                sb.append(cache.getBytesEconomizados());
                sb.append("\n");
                
            }
            
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            
            System.out.println(cache.toString());
            System.out.println(csvExport(sb, fileName1));
            System.out.printf("Finalizado em %.3f segundos\n", (double)timeElapsed / 1000000000);
            
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao manipular o arquivo:\n"+e.toString());
        }catch (Exception e){
            System.out.println("Ocorreu um erro:\n"+e.toString());
        }
        
    }
    
    /**Cria e gera um arquivo csv contendo os dados passados por parâmetro e salva com um nome especificado.
     * <br>Por padrão será criado um folder na pasta de usuarios de sistema chamado "Projeto_3", onde serão salvos todos os arquivos.
     * @param sb um objeto do tipo StringBuilder contendo os dados já formatados.
     * @param fileName indica o nome do arquivo a ser gerado.
     */
    public static String csvExport(StringBuilder sb, String fileName) throws Exception{
        String userFolder=System.getProperty("user.home");
        String path=userFolder+"\\Projeto_3\\";
        String filePath=path+fileName;
        
        try{
            File file = new File(path);
            file.mkdirs();
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
            osw.write(sb.toString());
            osw.close();
            
        } catch (Exception e) {
            throw e;
        }
        return ("Arquivo csv exportado com sucesso\nCaminho:"+filePath);
    }
}
