package estruturas;

import java.util.Iterator;

// Implementação da lista encadeada
public class MinhaListaEncadeada<E> implements EstruturaIF<E> {
    public Nodo<E> head;  // Nó inicial da lista
    private int tamanho;

    // Classe interna para representar os nós
    public class Nodo<T> {
        public E valor;
        public Nodo<E> next;

        public Nodo(E valor) {
            this.valor = valor;
            this.next = null;
        }
    }

    // Construtor da lista encadeada
    public MinhaListaEncadeada() {
        this.head = null;
        this.tamanho = 0;
    }

    @Override
    public void adicionar(E valor) {
        Nodo<E> novoNodo = new Nodo<>(valor);
        if (head == null) {
            head = novoNodo;
        } else {
            Nodo<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = novoNodo;
        }
        tamanho++;
    }

    public E pegarPrimeiro() { //head
        return (head != null) ? head.valor : null;
    }

    @Override
    public int tamanho(){
        return this.tamanho;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Nodo<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E valor = current.valor;
                current = current.next;
                return valor;
            }
        };
    }
}
