# 🌳 Projeto Java – Árvore Trie (Prefix Tree)

Este projeto implementa uma estrutura de dados do tipo **Trie**, também conhecida como **árvore de prefixos**, utilizando Java puro e estruturas encadeadas manuais. A estrutura permite **inserir**, **buscar**, **remover** e **imprimir** palavras de forma eficiente, respeitando as restrições de uso limitado de recursos da linguagem.

---

# 📋 Sobre o Projeto

O projeto tem como objetivo reforçar o entendimento de **árvores multiway**, em especial a Trie, sem o uso de arrays ou listas prontas da linguagem. Todos os ponteiros e nós são manipulados manualmente.

- Utilização de **ponteiros entre nós** (`firstChild` e `nextSibling`) em vez de arrays.
- Implementação de **inserção**, **busca**, **remoção** e **impressão hierárquica**.
- Código autoexplicativo, ideal para estudos e visualização da estrutura.

---

# ⚙️ Funcionalidades

- ✅ Inserção de palavras (`insert`)
- 🔍 Busca por palavras (`search`)
- ❌ Remoção de palavras com poda de nós órfãos (`remove`)
- 🖨️ Impressão visual da Trie (`printTrie`)
- 💻 Exemplo prático no método `main`

---

# 🧱 Estrutura da Trie

- Cada **nó** armazena:
  - um caractere (`char c`)
  - um ponteiro para o **primeiro filho** (`firstChild`)
  - um ponteiro para o **próximo irmão** (`nextSibling`)
  - uma **flag de fim de palavra** (`isEnd`)
- A **raiz** é um nó especial com caractere nulo (`'\0'`)

---

# 🚀 Tecnologias Utilizadas

- Java
- Execução via terminal ou IDEs como VS Code, Eclipse ou IntelliJ

---

# 🛠️ Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/MisaelVR/arvores-multiway.git
2. Abra o arquivo Trie.java na sua IDE.
3. Compile e execute:
   javac Trie.java
   java Trie
4. Acompanhe a saída no terminal com inserções, buscas e estrutura da árvore.

---
# 👨‍💼 Créditos
- Projeto desenvolvido por Emmanuel Victorio, Misael Vicente e Otávio Augusto,
- Resolução de Problemas Estruturados em Computação, Engenharia de Software – PUCPR.

# 📌 Links Importantes
- Repositório GitHub: https://github.com/MisaelVR/arvores-multiway
- Vídeo explicativo: https://www.youtube.com/watch?v=c3t4U0sja1A
