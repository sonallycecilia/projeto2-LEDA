package tests;

import estruturas.arvores.avl.ArvoreAvl;
import utils.Tweet;

public class TestAvl {
    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();

        Tweet t1 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t2 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t3 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");

        arvore.insertByCount(t1);
        arvore.insertByCount(t2);
        arvore.insertByCount(t3);

        arvore.printInOrder();

        for (Tweet tweet : arvore){
            if (tweet != null)
                System.out.println(tweet);
        }

    }
}
