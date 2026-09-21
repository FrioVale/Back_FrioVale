# 🥶 FrioVale

## Monitoramento IoT de Câmaras Frias para Conservação de Frutas

O **FrioVale** é uma solução IoT desenvolvida para monitorar as condições de temperatura e umidade durante o transporte de frutas produzidas no **Vale do São Francisco**.

A proposta utiliza sensores e um microcontrolador para realizar o monitoramento das condições da carga, identificar situações fora dos parâmetros configurados e disponibilizar essas informações para acompanhamento por meio de uma aplicação web/PWA.

> **Temperatura na faixa certa, frutas em boas mãos! 🍈🍐**

---

## 📌 Problema

O transporte de frutas exige condições adequadas de temperatura para preservar a qualidade dos produtos durante o trajeto.

A ausência de monitoramento contínuo pode fazer com que alterações de temperatura passem despercebidas, contribuindo para perdas na qualidade das frutas e prejuízos durante o transporte.

Dessa forma, o FrioVale busca solucionar a dificuldade de acompanhar continuamente as condições térmicas das câmaras frias.

---

## 💡 Solução Proposta

O FrioVale utiliza um dispositivo IoT baseado em **ESP32** e sensor **DHT22** para realizar a coleta de temperatura e umidade.

No protótipo, as informações são apresentadas localmente por meio de um **LCD 16x2**, enquanto LEDs indicam o estado da temperatura:

- 🟢 **LED Verde:** temperatura dentro da faixa configurada;
- 🔴 **LED Vermelho:** temperatura fora da faixa configurada.

Em etapas posteriores, os dados serão enviados por **MQTT** para um backend, armazenados em banco de dados e disponibilizados em um **Dashboard/PWA**, permitindo acompanhar o histórico das leituras e receber alertas.

---

# 🎯 Objetivos

## Objetivo Geral

Desenvolver uma solução IoT para monitoramento das condições de temperatura e umidade de câmaras frias utilizadas no transporte de frutas.

## Objetivos Específicos

- Realizar a coleta periódica de temperatura e umidade;
- Identificar condições de temperatura fora dos limites configurados;
- Apresentar alertas localmente por meio de LEDs e LCD;
- Enviar os dados coletados para uma infraestrutura de backend;
- Armazenar o histórico das leituras;
- Disponibilizar um dashboard para acompanhamento das condições da carga;
- Apoiar a identificação de anomalias durante o transporte.

---

# 🔧 Tecnologias

## Protótipo IoT

- **ESP32**
- **DHT22**
- **LCD 16x2 I2C**
- **LED Verde**
- **LED Vermelho**
- **Wokwi**
- **Arduino/C++**

## Comunicação

- **MQTT**
- **JSON**

## Backend

- **FastAPI**
- **Python**

## Banco de Dados

- **PostgreSQL**

## Interface

- **React**
- **PWA**
- Dashboard Web

---

# 🏗️ Arquitetura

A arquitetura preliminar do FrioVale é composta por quatro camadas principais:

### 1. Edge IoT

Responsável pela coleta das informações no local.

```text
DHT22
  ↓
ESP32
  ↓
LED + LCD
