package estruturas.lineares;

import java.util.Iterator;

public class MeuVetorDinamico<E> implements Iterable<E> {
    private int tamanhoAtual;
    private int capacidadeTotal;
    private E[] valores;

    @SuppressWarnings("unchecked")
    public MeuVetorDinamico() {
        capacidadeTotal = 10;
        tamanhoAtual = 0;
        valores = (E[]) new Object[capacidadeTotal];
    }

    public void adicionar(E valor) {
        if (tamanhoAtual == capacidadeTotal) {
            redimensionar();
        }
        valores[tamanhoAtual] = valor;
        tamanhoAtual++;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        capacidadeTotal *= 2;
        E[] novoArray = (E[]) new Object[capacidadeTotal];
        System.arraycopy(valores, 0, novoArray, 0, tamanhoAtual);
        valores = novoArray;
    }

    public E obter(int indice) {
        if (indice < 0 || indice >= tamanhoAtual) {
            throw new IndexOutOfBoundsException("Este índice não existe.");
        }
        return valores[indice];
    }

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

        if (tamanhoAtual == capacidadeTotal) {
            redimensionar();
        }

        for (int i = tamanhoAtual; i > indice; i--) {
            valores[i] = valores[i - 1];
        }

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
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < tamanhoAtual;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    System.out.println("Nao ha proximo.");
                }
                return valores[index++];
            }
        };
    }
}
