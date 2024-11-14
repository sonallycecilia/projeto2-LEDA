package algoritmos;

import estruturas.*;
import utils.Tweet;

public interface AlgoritmosIF {

    public void byDate(MeuVetorDinamico<Tweet> estrutura);
    public void byDate(MinhaListaEncadeada<Tweet> estrutura);
    public void byDate(MinhaListaDuplamenteEncadeada<Tweet> estrutura);


    //public <E> void byUser(EstruturaIF<E> estrutura);

    //public <E> void sortByMentionedPersons(EstruturaIF<E> estrutura);

}
