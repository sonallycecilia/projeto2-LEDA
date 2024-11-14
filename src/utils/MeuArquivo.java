package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import estruturas.EstruturaIF;

public class MeuArquivo {

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
    
    public static void write_date_file(String file_name, EstruturaIF<Tweet> data){
        String path = DIR_PROJECT_DATABASE + File.separator + file_name + ".csv";

        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))){
			file.write("Target,ID,Date,flag,User,Text");
            file.newLine();

            for(Tweet tweet : data) {
                String line = String.format("%s,%s,%s,%s,%s,%s", 
                    tweet.getTarget(), 
                    tweet.getId(), 
                    tweet.getFormatted_date(), 
                    tweet.getFlag(), 
                    tweet.getUser(), 
                    tweet.getText()
                );
                file.write(line);
                file.newLine();
            }
            System.out.println("Informações salvas por data.");
        } catch (IOException e){
			System.out.println("Erro ao escrever arquivo.");
			e.printStackTrace();
        }
    }

    public static void write_mentioned_persons_file(String file_name, EstruturaIF<Tweet> data) {
        String path_formatted_date = DIR_PROJECT_DATABASE + File.separator + "tweets_formatted_date.csv";
        String path_mentioned_persons = DIR_PROJECT_DATABASE + File.separator + file_name + ".csv";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(path_formatted_date));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path_mentioned_persons))) {
        
            String line;
            boolean isFirstLine = true;
            
            for (Tweet tweet : data) {
                if ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        writer.write(line + ",Mentioned persons,Mentioned persons count");
                        writer.newLine();
                        isFirstLine = false;
                    } else {
                        writer.write(line + "," +
                                tweet.getMentioned_person() + "," +
                                tweet.getMentioned_person_count());
                        writer.newLine();
                    }
                }
            }
            System.out.println("Informações salvas por pessoas mencionadas.");
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo.");
            e.printStackTrace();
        }
    }
    
    public static void write_ordened_file(String name, EstruturaIF<Tweet> data){
        String path = DIR_PROJECT_DATABASE + File.separator + "ordened_database" + File.separator + name + ".csv";

        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))){
			file.write("Target,ID,Date,flag,User,Text,Mentioned persons,Mentioned persons count");
            file.newLine();

            for (Tweet tweet : data) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%d", 
                    tweet.getTarget(), 
                    tweet.getId(), 
                    tweet.getFormatted_date(), 
                    tweet.getFlag(), 
                    tweet.getUser(), 
                    tweet.getText(),
                    tweet.getMentioned_person(),
                    tweet.getMentioned_person_count()
                );
                file.write(line);
                file.newLine();
            }
        } catch (IOException e){
			System.out.println("Erro ao escrever arquivo.");
			e.printStackTrace();
        }
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
