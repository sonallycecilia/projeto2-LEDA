package estruturas.arvores;

import estruturas.lineares.MinhaFila;
import utils.Tweet;

public class NoAvl {
    public Tweet key;
    public int height;
    public NoAvl left, right, next;  // Adicionando o campo "next" para a fila
    MinhaFila<Tweet> duplicates;

    public NoAvl(Tweet key) {
        this.key = key;
        this.height = 1; // Ao criar um nó, a altura inicial é 1
        this.next = null; // O próximo nó é inicialmente nulo
        this.duplicates = new MinhaFila<>();
    }

    public Tweet getKey(){
        return key;
    }
}
