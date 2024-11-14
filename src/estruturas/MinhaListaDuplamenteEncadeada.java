package estruturas;

import java.util.Iterator;

// Implementação da lista duplamente encadeada
public class MinhaListaDuplamenteEncadeada<E> implements EstruturaIF<E> {
    public No<E> head;  // Nó inicial da lista
    public No<E> tail;  // Nó final da lista
    public int tamanho;

    // Classe interna para representar os nós
    @SuppressWarnings("hiding")
    public class No<E> {
        public E valor;
        public No<E> proximo;  // Ponteiro para o próximo nó
        public No<E> anterior;  // Ponteiro para o nó anterior

        public No(E valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }

    // Construtor da lista duplamente encadeada
    public MinhaListaDuplamenteEncadeada() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }

    @Override
    public void adicionar(E valor) {
        No<E> novoNo = new No<>(valor);
        if (head == null) {
            head = novoNo;
            tail = novoNo;
        } else {
            tail.proximo = novoNo;  // O atual 'tail' aponta para o novo nó
            novoNo.anterior = tail;  // O novo nó aponta de volta para o 'tail'
            tail = novoNo;           // O 'tail' agora é o novo nó
        }
        tamanho++;
    }

    public E pegarPrimeiro() {
        return (head != null) ? head.valor : null;
    }

    public E pegarUltimo() {
        return (tail != null) ? tail.valor : null;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private No<E> atual = head;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                E valor = atual.valor;
                atual = atual.proximo;
                return valor;
            }
        };
    }

    public E removerPrimeiro() {
        if (head == null) {
            return null;  // Lista vazia
        }
        E valor = head.valor;
        head = head.proximo;
        if (head != null) {
            head.anterior = null;  // Se houver um novo 'head', o ponteiro anterior é setado como null
        } else {
            tail = null;  // Se a lista ficar vazia, 'tail' também é null
        }
        tamanho--;
        return valor;
    }

    public E removerUltimo() {
        if (tail == null) {
            return null;  // Lista vazia
        }
        E valor = tail.valor;
        tail = tail.anterior;
        if (tail != null) {
            tail.proximo = null;  // Se houver um novo 'tail', o ponteiro próximo é setado como null
        } else {
            head = null;  // Se a lista ficar vazia, 'head' também é null
        }
        tamanho--;
        return valor;
    }
}
