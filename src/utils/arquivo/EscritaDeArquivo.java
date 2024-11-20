package utils.arquivo;

import estruturas.arvores.avl.ArvoreAvl;
import estruturas.lineares.MeuVetorDinamico;
import utils.Tweet;

import java.io.*;

public class EscritaDeArquivo {
    public static final String DIR_PROJECT_DATABASE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "database";

    public static void write_date_file(String file_name, MeuVetorDinamico<Tweet> data){
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

    public static void write_mentioned_persons_file(String file_name, MeuVetorDinamico<Tweet> data) {
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

    public static void write_ordened_file(String name, MeuVetorDinamico<Tweet> data){
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

    public static void write_ordened_tree(String name, ArvoreAvl estrutura){
        String path = DIR_PROJECT_DATABASE + File.separator + "ordened_database" + File.separator + name + ".csv";

        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))){
            file.write("Target,ID,Date,flag,User,Text,Mentioned persons,Mentioned persons count");
            file.newLine();

            for (Tweet tweet : estrutura) {
                if (tweet != null){
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
            }
        } catch (IOException e){
            System.out.println("Erro ao escrever arquivo.");
            e.printStackTrace();
        }
    }
}
