package estruturas.lineares;

import java.util.Iterator;

public class MinhaFila<E> implements Iterable<E> {
    private int tamanho;
    private int cauda;
    private int cabeca;
    private E[] meusDados;


    public MinhaFila(int tamanhoInicial) {
        tamanho = tamanhoInicial;
        meusDados = (E[]) new Object[tamanho];
        cauda = 0;
        cabeca = 0;
    }

    public MinhaFila() {
        tamanho = 10;
        meusDados = (E[]) new Object[tamanho];
        cauda = 0;
        cabeca = 0;
    }

    public void enqueue(E item) {
        if (isFull()) {
            redimensionar();
        }
        this.meusDados[this.cauda] = item;
        if (this.cauda < this.meusDados.length) {
            this.cauda++;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            System.out.println("A fila está vazia");
        }
        E temp = this.meusDados[cabeca];
        for(int i = this.cabeca; i < this.cauda-1; i++){
            this.meusDados[i] = this.meusDados[i+1];
        }
        this.meusDados[cauda-1] = null;
        cauda--;
        return temp;
    }

     private void redimensionar() {
         tamanho *= 2;
         E[] novoArray = (E[]) new Object[tamanho];

         System.arraycopy(this.meusDados, 0, novoArray, 0, cauda);
         this.meusDados = novoArray;
     }

    public boolean isEmpty() {
        return this.cabeca == this.cauda;
    }

    public boolean isFull() {
        return this.cauda == this.tamanho;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < tamanho;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    System.out.println("Nao ha proximo.");
                }
                return meusDados[index++];
            }
        };
    }

 }