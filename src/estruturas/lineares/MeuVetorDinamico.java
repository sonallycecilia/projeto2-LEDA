package estruturas.lineares.indexada;

import estruturas.lineares.EstruturaIF;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MeuVetorDinamico<E> implements EstruturaIF<E> {
    int tamanhoAtual;
    int capacidadeTotal;
    public E[] valores;

    @SuppressWarnings("unchecked")  //ta dando warning na criação generica
    public MeuVetorDinamico() {
        capacidadeTotal = 10;     
        tamanhoAtual = 0;
        valores = (E[]) new Object[capacidadeTotal];  
    }

    @Override
    public void adicionar(E valor) {
        if (tamanhoAtual == capacidadeTotal) {
            redimensionar();
        }
        valores[tamanhoAtual] = valor;
        tamanhoAtual++;
    }

    @SuppressWarnings("unchecked") //ta dando warning na criação generica
    private void redimensionar() {
        capacidadeTotal *= 2; // Aumenta a capacidade do vetor (por exemplo, dobrando o tamanho)
        E[] novoArray = (E[]) new Object[capacidadeTotal];
        
        // Copia os elementos do vetor original para o novo vetor redimensionado
        System.arraycopy(valores, 0, novoArray, 0, tamanhoAtual);
        valores = novoArray;
    }

    public E obter(int indice) {
        if (indice < 0 || indice >= tamanhoAtual) {
            System.out.println("Este índice não existe.");
            return null; 
        }
        return valores[indice];
    }

    @Override
    public int getTamanho() {
        return tamanhoAtual;
    }

    public boolean estaVazio() {
        return tamanhoAtual == 0;
    }

    public void adicionar(int indice, E valor) {
        if (indice < 0 || indice > tamanhoAtual) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }

        // Verifica se o vetor precisa ser redimensionado
        if (tamanhoAtual == capacidadeTotal) {
            redimensionar();
        }

        // Desloca os elementos à direita para abrir espaço para o novo valor
        for (int i = tamanhoAtual; i > indice; i--) {
            valores[i] = valores[i - 1];
        }

        // Insere o novo valor na posição indicada
        valores[indice] = valor;
        tamanhoAtual++;
    }

    public void trocar(int indice1, int indice2) {
        if (indice1 < 0 || indice2 < 0 || indice1 >= tamanhoAtual || indice2 >= tamanhoAtual) {
            throw new IndexOutOfBoundsException("Índices fora dos limites.");
        }

        E temp = valores[indice1];
        valores[indice1] = valores[indice2];
        valores[indice2] = temp;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int indiceAtual = 0;

            @Override
            public boolean hasNext() {
                return indiceAtual < tamanhoAtual;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return valores[indiceAtual++];
            }
        };
    }
}