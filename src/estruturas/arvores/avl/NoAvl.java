package estruturas.arvores.avl;

import estruturas.lineares.MinhaFila;
import utils.Tweet;

public class NoAvl {
    public Tweet key;
    public int height;
    public NoAvl left, right, next;
    MinhaFila<Tweet> duplicates;

    public NoAvl(Tweet key) {
        this.key = key;
        this.height = 1;
        this.next = null;
        this.duplicates = new MinhaFila<>();
    }

    public Tweet getKey(){
        return key;
    }
}
