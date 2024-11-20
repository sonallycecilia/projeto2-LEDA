package tests;

import estruturas.lineares.MinhaFila;
import utils.Tweet;

public class TestFila {
    public static void main(String[] args) {
        Tweet t1 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t2 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");
        Tweet t3 = new Tweet("0", "1467812025", "Mon Apr 06 22:20:09 PDT 2009", "NO_QUERY", "mimismo", "@twittera que me muera ?");

        MinhaFila fila = new MinhaFila();

        System.out.println("Adicionando tweets na fila:");
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);

        fila.dequeue();

        for (Object tweet : fila){
            if (tweet != null)
                System.out.println(tweet);
        }
    }
}

