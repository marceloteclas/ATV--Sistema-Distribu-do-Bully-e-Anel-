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
Clone o repositório
```bash
git clone https://github.com/seu-usuario/algoritmo-bully-distribuido.git
cd algoritmo-bully-distribuido


### 📷 Exemplo de Saída

```txt
P5 iniciou eleição...
P5 se tornou o novo COORDENADOR
P2 detectou falha do coordenador!
P2 iniciou eleição...
P4 se tornou o novo COORDENADOR

--- CENÁRIO A: P5 retorna ao sistema ---

P5 iniciou eleição...
P5 se tornou o novo COORDENADOR

--- CENÁRIO B: P5 e P4 falham simultaneamente ---

P2 detectou falha do coordenador!
P3 iniciou eleição...
P3 se tornou o novo COORDENADOR





