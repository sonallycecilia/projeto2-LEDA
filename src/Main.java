import utils.*;
import estruturas.*;
import algoritmos.*;
public class Main {
    public static void main(String[] args) throws Exception {
        EstruturaIF<Tweet> database;
        
        //Gerando arquivos formatados com uma estrutura dinâmica
        System.out.println("Extraindo arquivos da database...");
        database = new MeuVetorDinamico<>();
        MeuArquivo.extract_tweets_database(database);
        System.out.println("Escrevendo arquivo formatados...");
        MeuArquivo.write_date_file("tweets_formatted_date", database);
        MeuArquivo.write_mentioned_persons_file("tweets_mentioned_persons", database);
        database = null;


    }
}
