package estruturas.arvores.avl;

import estruturas.lineares.MeuVetorDinamico;
import estruturas.lineares.MinhaFila;
import utils.Tweet;

import java.util.Iterator;

public class ArvoreAvl implements Iterable<Tweet> {
    private NoAvl root;

    public ArvoreAvl() {
        root = null;
    }

    public NoAvl getRoot() {
        return this.root;
    }

    public int height(NoAvl node) {
        if (node == null) return 0;
        return node.height;
    }

    public int getBalance(NoAvl node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private NoAvl rotateRight(NoAvl y) {
        if (y == null || y.left == null) return y;

        NoAvl x = y.left;
        NoAvl T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }


    private NoAvl rotateLeft(NoAvl x) {
        if (x == null || x.right == null) return x;

        NoAvl y = x.right;
        NoAvl T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
    public void insertByCount(Tweet key){
        this.root = insertByCount(root, key);
    }

    private NoAvl insertByCount(NoAvl node, Tweet key) {
        if (node == null) return new NoAvl(key);

        // Inserção com base no número de menções (ordem decrescente)
        if (key.getMentioned_person_count() > node.key.getMentioned_person_count()) {
            node.left = insertByCount(node.left, key);
        } else if (key.getMentioned_person_count() < node.key.getMentioned_person_count()) {
            node.right = insertByCount(node.right, key);
        } else {
            // Adiciona o tweet na fila de duplicatas
            node.duplicates.enqueue(key);
            return node;
        }

        // Atualiza a altura do nó
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Calcula o fator de balanceamento
        int balance = getBalance(node);

        // Corrige desequilíbrios se necessário
        if (balance > 1) {
            if (key.getMentioned_person_count() > node.left.key.getMentioned_person_count()) {
                return rotateRight(node);
            }
            if (key.getMentioned_person_count() < node.left.key.getMentioned_person_count()) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (key.getMentioned_person_count() < node.right.key.getMentioned_person_count()) {
                return rotateLeft(node);
            }
            if (key.getMentioned_person_count() > node.right.key.getMentioned_person_count()) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }




    public void insertByDate(Tweet key) {
        this.root = insertByDate(root, key);
    }

    private NoAvl insertByDate(NoAvl node, Tweet key) {
        if (node == null) {
            return new NoAvl(key);
        }

        if (Tweet.compararDatas(key, node.getKey())) {
            node.left = insertByDate(node.left, key);
        } else {
            node.right = insertByDate(node.right, key);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Corrige desequilíbrios se necessário
        if (balance > 1 && Tweet.compararDatas(node.left.getKey(), key)) {
            return rotateRight(node);
        }

        if (balance > 1 && !Tweet.compararDatas(node.left.getKey(), key)) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && !Tweet.compararDatas(node.right.getKey(), key)) {
            return rotateLeft(node);
        }

        if (balance < -1 && Tweet.compararDatas(node.right.getKey(), key)) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insertByUser(Tweet key) {
        this.root = insertByUser(root, key);
    }

    private NoAvl insertByUser(NoAvl node, Tweet key) {
        if (node == null) {
            return new NoAvl(key);
        }

        if (Tweet.compararStrings(key, node.getKey())) {
            node.left = insertByUser(node.left, key);
        } else {
            node.right = insertByUser(node.right, key);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && Tweet.compararStrings(node.left.getKey(), key)) {
            return rotateRight(node);
        }

        // Caso LR
        if (balance > 1 && !Tweet.compararStrings(node.left.getKey(), key)) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Caso RR
        if (balance < -1 && !Tweet.compararStrings(node.right.getKey(), key)) {
            return rotateLeft(node);
        }

        // Caso RL
        if (balance < -1 && Tweet.compararStrings(node.right.getKey(), key)) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; // Retorna o nó ajustado
    }

    public void printInOrder() {
        System.out.print("Elementos em ordem: ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(NoAvl node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.key.getMentioned_person_count() + " ");
            printInOrder(node.right);
        }
    }

    @Override
    public Iterator<Tweet> iterator() {
        MinhaFila<Tweet> elementos = new MinhaFila<>();
        inOrderTraversal(root, elementos);
        return elementos.iterator();
    }

    private void inOrderTraversal(NoAvl node, MinhaFila<Tweet> elementos) {
        if (node != null) {
            // Percorre o lado esquerdo
            inOrderTraversal(node.left, elementos);

            // Adiciona o nó principal
            elementos.enqueue(node.key);

            // Adiciona os duplicados, se existirem
            for (Tweet tweetDuplicado : node.duplicates) {
                elementos.enqueue(tweetDuplicado);
            }

            // Percorre o lado direito
            inOrderTraversal(node.right, elementos);
        }
    }


}
