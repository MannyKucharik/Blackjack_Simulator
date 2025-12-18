# Blackjack Strategy Simulator

A Java-based Blackjack simulation engine designed to evaluate player strategies
using Monte Carlo simulation. The project emphasizes clean architecture,
extensibility, and statistical analysis rather than a graphical interface.

## Overview
This simulator runs large numbers of Blackjack hands to measure strategy
performance through win rates, bust rates, and expected value (EV). The system
is modular, allowing strategies and rules to be modified without changing core
game logic.

## Features
- Modular design with clear separation of concerns (Model, Engine, Strategy, Util)
- Pluggable Strategy interface for player decision logic
- Realistic casino rules (multi-deck shoe, dealer soft-17 behavior, blackjack payouts)
- Statistical reporting of outcomes and expected value
- Command-line runnable with no external dependencies

## Project Structure
bj/
├── Model/ # Cards, hands, shoe, player state
├── Engine/ # Game flow, rules, simulator
├── strategy/ # Strategy interface and implementations
├── Util/ # Stats aggregation and reporting
└── Main.java # Entry point

## How to Run
From the project root:
```bash
javac bj/Model/*.java bj/strategy/*.java bj/Engine/*.java bj/Util/*.java bj/Main.java
java bj.Main
```

Simulation parameters (number of hands, rules, strategy) can be adjusted in Main.java.

### Example Output
Hands: 100000
Wins: 42135
Losses: 49120
Pushes: 8735
EV (units/hand): -0.0182

### Author
Manny Kucharik
Computer Science student at the University of Central Florida
