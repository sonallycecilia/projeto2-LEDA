package estruturas;

public class MeuVetorDinamico<T> {
    private int tamanhoAtual;        
    private int capacidadeTotal;     
    private T[] valores;     

    @SuppressWarnings("unchecked")
    public MeuVetorDinamico() {
        capacidadeTotal = 10;     
        tamanhoAtual = 0;
        valores = (T[]) new Object[capacidadeTotal];  
    }

    public void adicionar(T valor) {
        if (tamanhoAtual == capacidadeTotal) {
            redimensionar();
        }
        valores[tamanhoAtual] = valor;
        tamanhoAtual++;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        capacidadeTotal *= 2;
        T[] novoArray = (T[]) new Object[capacidadeTotal];
        System.arraycopy(valores, 0, novoArray, 0, tamanhoAtual);
        valores = novoArray;
    }

    public T obter(int indice) {
        if (indice < 0 || indice >= tamanhoAtual) {
            System.out.println("Este índice não existe.");
            return null; 
        }
        return valores[indice];
    }

    public T removerUltimo() {
        if (tamanhoAtual > 0) {
            T temp = valores[tamanhoAtual - 1];
            valores[tamanhoAtual - 1] = null;
            tamanhoAtual--;
            return temp;
        }
        System.out.println("O Array está vazio, impossível remover o elemento.");
        return null;
    }

    public int tamanho() {
        return tamanhoAtual;
    }

    public boolean estaVazio() {
        return tamanhoAtual == 0;
    }
}
