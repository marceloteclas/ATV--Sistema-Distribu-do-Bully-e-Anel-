# 🗳️ Algoritmo de Eleição Distribuída (Bully e Anel) – Simulação em Java

Este projeto simula um sistema distribuído com múltiplos processos que utilizam os **algoritmo de eleição Bully** e **algoritmo de eleição Anel**para determinar um coordenador entre eles. A implementação considera falhas, recuperação de processos e eleição automática em dois cenários distintos.

## 🗳️ Algoritmo de Eleição Distribuída (Bully)

## 📌 Descrição

- ✅ 5 processos (P1 a P5) simulados por **threads Java**
- ✅ Detecção automática de falha do coordenador
- ✅ Implementação do algoritmo **Bully**
- ✅ **Cenário A**: coordenador falha e retorna após eleição
- ✅ **Cenário B**: múltiplos processos falham simultaneamente
- ✅ Registro detalhado de logs no terminal
- 
## 🧠 Conceito – Algoritmo de Bully
O algoritmo Bully funciona da seguinte forma:

Quando um processo detecta a falha do coordenador, inicia uma eleição.

Ele envia mensagens para todos os processos com ID maior.

Se ninguém responde, ele se torna coordenador.

Caso contrário, espera que outro processo assuma o papel de coordenador.

## 🗳️ Algoritmo de Eleição Distribuída (Anel)

##📌 Descrição
-✅ 5 processos (P1 a P5) simulados por threads Java
-✅ Implementação do algoritmo Anel
-✅ O token de eleição circula no anel até que o coordenador seja definido
-✅ Considera falha do coordenador e reinício automático do processo
-✅ Cenário A: coordenador falha e eleição é iniciada por outro processo
-✅ Cenário B: falhas múltiplas e reeleição com token circular
-✅ Registro detalhado de logs no terminal

## 🧠 Conceito – Algoritmo de Anel
O algoritmo de eleição Anel funciona da seguinte forma:

Quando um processo detecta a falha do coordenador, inicia uma eleição e envia um token com seu ID ao próximo processo no anel.

Cada processo ao receber o token compara seu ID com os IDs no token e adiciona o seu, se for maior.

O token circula até retornar ao iniciador, que então identifica o processo com o maior ID e o declara como novo coordenador.

Todos os processos são notificados do novo coordenador.

## 🏁 Como Executar
1- Clone o repositório: git clone https://github.com/marceloteclas/ATV--Sistema-Distribu-do-Bully-e-Anel-.git

2- Navegue até o diretório do projeto:
cd ATV--Sistema-Distribu-do-Bully-e-Anel-

3- Execute o programa no arquivo 'main.java' ao clicar no botao "Run" na sua IDEA.

## 🛠️ Estrutura de Arquivos

📁 algoritmo-bully-distribuido/

├── Main.java               
├── Processo.java          
├── EleicaoService.java     
├── README.md              
└── RelatorioTecnico.pdf   




