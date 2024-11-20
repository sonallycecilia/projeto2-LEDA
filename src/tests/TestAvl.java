import estruturas.arvores.avl.ArvoreAvl;
import utils.Tweet;

public class TestAvl {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();

        // Criando Tweets de exemplo
        Tweet t1 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t2 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t3 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");

        // Inserindo Tweets na árvore
        arvore.insertByCount(t1);
        arvore.insertByCount(t2);
        arvore.insertByCount(t3);

        // Exibindo elementos em ordem crescente
        arvore.printInOrder();

        // Iterando com for-each
        System.out.println("Iterando pela árvore:");
        for (Tweet tweet : arvore) {
            System.out.println(tweet);
        }
    }
}
