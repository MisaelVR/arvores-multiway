public class Trie {

    /**
     * Classe interna que representa um nó da Trie.
     * Cada nó armazena:
     *  - um caractere (c)
     *  - flag isEnd indicando fim de palavra
     *  - ponteiro para o primeiro filho (firstChild)
     *  - ponteiro para o próximo irmão (nextSibling)
     */
    private class Node {
        char c;
        boolean isEnd;
        Node firstChild;
        Node nextSibling;

        // Construtor inicializa o caractere e zera ponteiros e flag
        Node(char c) {
            this.c = c;
            this.isEnd = false;
            this.firstChild = null;
            this.nextSibling = null;
        }
    }

    // Raiz da Trie não contém caractere útil, somente serve de ponto de partida
    private Node root;

    /**
     * Construtor padrão: cria a raiz vazia
     */
    public Trie() {
        root = new Node('\0');
    }

    /**
     * Insere uma palavra na Trie.
     * @param word String não nula
     * @throws Exception se word for null
     */
    public void insert(String word) throws Exception {
        if (word == null) {
            throw new Exception("Palavra nula não permitida");
        }
        Node current = root;
        // Para cada caractere da palavra
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // Encontra ou cria o nó filho correspondente
            current = insertChild(current, ch);
        }
        // Marca o fim de palavra no último nó
        current.isEnd = true;
    }

    /**
     * Auxiliar para inserir ou obter filho com determinado caractere.
     * @param node   Nó pai
     * @param ch     Caractere a inserir/buscar
     * @return       Nó filho que contém ch
     */
    private Node insertChild(Node node, char ch) {
        // Se não houver nenhum filho, cria o primeiro
        if (node.firstChild == null) {
            Node novo = new Node(ch);
            node.firstChild = novo;
            return novo;
        }
        // Percorre irmãos até encontrar ou chegar ao fim
        Node prev = null;
        Node curr = node.firstChild;
        while (curr != null && curr.c != ch) {
            prev = curr;
            curr = curr.nextSibling;
        }
        // Se já existe nó com ch, retorna-o
        if (curr != null) {
            return curr;
        }
        // Caso contrário, cria um novo irmão ao final da lista
        Node novo = new Node(ch);
        prev.nextSibling = novo;
        return novo;
    }

    /**
     * Verifica se uma palavra está presente na Trie.
     * @param word String não nula
     * @return true se existir e for marcada como fim de palavra
     * @throws Exception se word for null
     */
    public boolean search(String word) throws Exception {
        if (word == null) {
            throw new Exception("Palavra nula não permitida");
        }
        Node current = root;
        // Para cada caractere, busca o filho correspondente
        for (int i = 0; i < word.length(); i++) {
            current = findChild(current, word.charAt(i));
            if (current == null) {
                return false; // caractere ausente
            }
        }
        // Verifica se o nó final está marcado como fim de palavra
        return current.isEnd;
    }

    /**
     * Auxiliar para buscar um filho com caractere específico.
     * @param node Nó onde busca ocorre
     * @param ch   Caractere a buscar
     * @return     Nó encontrado ou null
     */
    private Node findChild(Node node, char ch) {
        Node curr = node.firstChild;
        while (curr != null) {
            if (curr.c == ch) {
                return curr;
            }
            curr = curr.nextSibling;
        }
        return null;
    }

    /**
     * Remove uma palavra da Trie, podando nós órfãos.
     * @param word String não nula
     * @throws Exception se word for null
     */
    public void remove(String word) throws Exception {
        if (word == null) {
            throw new Exception("Palavra nula não permitida");
        }
        // Chama recursivo iniciando da raiz
        remove(root, word, 0);
    }

    /**
     * Método recursivo para remoção.
     * @param node  Nó atual
     * @param word  Palavra a remover
     * @param index Índice do caractere atual
     * @return      true se o nó atual puder ser apagado pelo pai
     */
    private boolean remove(Node node, String word, int index) {
        // Se chegamos ao fim da palavra
        if (index == word.length()) {
            if (!node.isEnd) {
                return false; // não era fim de palavra
            }
            node.isEnd = false; // desmarca fim
            // apaga este nó caso não tenha filhos
            return node.firstChild == null;
        }
        char ch = word.charAt(index);
        Node prev = null;
        Node curr = node.firstChild;
        // Procura o filho com ch
        while (curr != null && curr.c != ch) {
            prev = curr;
            curr = curr.nextSibling;
        }
        if (curr == null) {
            return false; // caminho inexistente
        }
        // Chama recursivamente para o próximo nível
        boolean shouldDelete = remove(curr, word, index + 1);
        if (shouldDelete) {
            // Remove curr da lista de filhos/irmãos
            if (prev == null) {
                node.firstChild = curr.nextSibling;
            } else {
                prev.nextSibling = curr.nextSibling;
            }
        }
        // Retorna true se este nó também puder ser removido
        return !node.isEnd && node.firstChild == null;
    }

    /**
     * Imprime a Trie em formato hierárquico (para depuração).
     */
    public void printTrie() {
        printTrie(root, 0);
    }

    private void printTrie(Node node, int level) {
        Node child = node.firstChild;
        while (child != null) {
            // Indenta de acordo com o nível
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            // Exibe caractere e marcações de fim de palavra
            System.out.println(child.c + (child.isEnd ? " (fim)" : ""));
            // Desce recursivamente
            printTrie(child, level + 1);
            child = child.nextSibling;
        }
    }

    /**
     * Exemplo de uso: testa inserção, busca, remoção e impressão.
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        try {
            trie.insert("car");
            trie.insert("cat");
            trie.insert("cap");
            trie.insert("dog");

            System.out.println("Busca 'cat': " + trie.search("cat"));
            System.out.println("Busca 'cow': " + trie.search("cow"));

            System.out.println("\n-- Antes da remoção --");
            trie.printTrie();

            trie.remove("cap");
            System.out.println("\nBusca 'cap' após remoção: " + trie.search("cap"));

            System.out.println("\n-- Após remover 'cap' --");
            trie.printTrie();

        } catch (Exception e) {
            // Caso ocorra erro, imprime stack trace
            e.printStackTrace();
        }
    }
}
