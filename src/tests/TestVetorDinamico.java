package tests;

import estruturas.lineares.MeuVetorDinamico;

public class TestVetorDinamico {
    public static void main(String[] args) {
        MeuVetorDinamico<String> vetor = new MeuVetorDinamico<>();

        System.out.println("Testando adicionar:");
        vetor.adicionar("Elemento 1");
        vetor.adicionar("Elemento 2");
        vetor.adicionar("Elemento 3");
        System.out.println("Tamanho após adicionar elementos: " + vetor.getTamanho());
        imprimirVetor(vetor);

        System.out.println("\nTestando obter:");
        System.out.println("Elemento na posição 1: " + vetor.obter(1));
        System.out.println("Tentando obter índice inválido (-1): " + vetor.obter(-1));

        System.out.println("\nTestando adicionar em índice:");
        vetor.adicionar("1");
        System.out.println("Tamanho após adicionar em índice 1: " + vetor.getTamanho());
        imprimirVetor(vetor);

        System.out.println("\nTestando iterator:");
        System.out.print("Elementos no vetor: ");
        for (Object elemento : vetor) {
            System.out.print(elemento + " ");
        }
        System.out.println();

        System.out.println("\nTestando estaVazio:");
        System.out.println("O vetor está vazio? " + vetor.estaVazio());

        System.out.println("\nTestando redimensionamento:");
        for (int i = 4; i <= 15; i++) {
            vetor.adicionar("Elemento " + i);
        }
        System.out.println("Tamanho após adicionar vários elementos: " + vetor.getTamanho());
        imprimirVetor(vetor);
    }

    private static void imprimirVetor(MeuVetorDinamico<?> vetor) {
        System.out.print("Elementos: ");
        for (Object elemento : vetor) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
}
