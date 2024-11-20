package utils.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import estruturas.lineares.EstruturaIF;
import utils.Tweet;

public class LeituraDeArquivo {

    public static final String DIR_PROJECT_DATABASE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "database";
    
    public static <T extends EstruturaIF<Tweet>> T extract_tweets_database(T estrutura) {
        String path = DIR_PROJECT_DATABASE + File.separator + "tweets.csv";
        
        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            
            while ((line = file.readLine()) != null) {
                if (i > 0) { 
                    String[] field = line.split(","); 
                    
                    Tweet tweet = new Tweet(field[0], field[1], field[2], field[3], field[4], field[5].trim());
                    
                    estrutura.adicionar(tweet);
                }
                i++;
            }
            
            System.out.println("Extract database completed.");
        } catch (IOException e) {
            System.out.println("Erro ao extrair dados.");
            e.printStackTrace();
        }
        
        return estrutura;
    }

    public static <T extends EstruturaIF<Tweet>> T extract_database(String name, T estrutura) {
        String path = DIR_PROJECT_DATABASE + File.separator + name + ".csv";
    
        try (BufferedReader file = new BufferedReader(new FileReader(path))) {
            String line;
            int i = 0;
            
            System.out.println("Extraindo dados...");
            while ((line = file.readLine()) != null) {
                if (i > 0) { // Pulando a linha inicial de cabeçalho
                    String[] field = line.split(","); // Separando a linha em campos
    
                    // Tratamento de possíveis erros de conversão
                    int count_mentioned_person = 0;
                    try {
                        count_mentioned_person = Integer.parseInt(field[7]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error converting number for mentioned persons.");
                        e.printStackTrace();
                    }
    
                    // Criando o objeto Tweet
                    Tweet tweet = new Tweet(field[0], field[1], field[2], field[3], field[4], field[5], field[6], count_mentioned_person);
                    estrutura.adicionar(tweet); // Adicionando o tweet à estrutura genérica
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo" + path);
            e.printStackTrace();
        }
        
        System.out.println("Extração de dados completa.");
        return estrutura; // Retorna a estrutura de dados preenchida
    }

}
