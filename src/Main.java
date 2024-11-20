import estruturas.arvores.avl.ArvoreAvl;
import estruturas.lineares.MeuVetorDinamico;
import utils.Tweet;
import utils.arquivo.EscritaDeArquivo;
import utils.arquivo.LeituraDeArquivo;

public class Main {
    public static void main(String[] args) throws Exception {
        MeuVetorDinamico<Tweet> database;
        ArvoreAvl arvore;

        //Gerando arquivos formatados com uma estrutura dinâmica
        System.out.println("Extraindo arquivos da database...");
        database = new MeuVetorDinamico<>();
        LeituraDeArquivo.extract_tweets_database(database);
        System.out.println("Escrevendo arquivo formatados...");
        EscritaDeArquivo.write_date_file("tweets_formatted_date", database);
        EscritaDeArquivo.write_mentioned_persons_file("tweets_mentioned_persons", database);
        database = null;

        database = new MeuVetorDinamico<>();
        LeituraDeArquivo.extract_database("tweets_mentioned_persons", database);

        arvore = new ArvoreAvl();
        for (int i = 0; i < database.getTamanho(); i++){ arvore.insertByCount(database.obter(i)); }
        EscritaDeArquivo.write_ordened_tree("tweets_mentioned_persons_count", arvore);
        arvore = null;

        arvore = new ArvoreAvl();
        for(int i = 0; i < database.getTamanho(); i++){arvore.insertByDate(database.obter(i));}
        EscritaDeArquivo.write_ordened_tree("tweets_mentioned_persons_date", arvore);
        arvore = null;

        arvore = new ArvoreAvl();
        for(int i = 0; i < database.getTamanho(); i++){arvore.insertByUser(database.obter(i));}
        EscritaDeArquivo.write_ordened_tree("tweets_mentioned_persons_user", arvore);
    }
}
