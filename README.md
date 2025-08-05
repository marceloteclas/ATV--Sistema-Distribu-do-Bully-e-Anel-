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

## 🏁 Como Executar
1- Clone o repositório: git clone https://github.com/Ronaldo-Correia/Sistema-Legado--ADD-Padroes.git

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




