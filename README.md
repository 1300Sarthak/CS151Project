# Mancala Game Project - CS151

## Overview

This project is a Java-based implementation of the ancient two-player game, Mancala. It was developed as part of the CS151 curriculum and adheres to object-oriented principles, including MVC and Strategy design patterns. The goal was to create a functional and interactive graphical version of Mancala with customizable board styles and user-friendly features.

---

## How We Made It

### 1. **Understanding Requirements**

We started by carefully analyzing the project requirements provided by Dr. Kim. The main focus was on:

- Implementing the game rules correctly.
- Designing a user-friendly GUI.
- Using MVC and Strategy patterns effectively.

### 2. **Planning and Design**

To structure our approach:

- **UML Diagrams**: We created UML diagrams to visualize the structure and interactions between classes.
- **CRC Cards**: Defined the roles and responsibilities for each class.
- **Design Patterns**: Incorporated the **MVC** pattern for separating logic and UI, and the **Strategy** pattern for board style customization.

### 3. **Development Process**

#### A. **Game Logic**

- Created a `GameModel` class to handle the core logic, such as distributing stones, checking game-ending conditions, and managing turn rules.
- Implemented undo functionality by maintaining a history stack of board states.

#### B. **GUI Development**

- Used Java Swing for building the graphical interface.
- Developed a `BoardView` class to display the Mancala board and pits.
- Added interactive features such as:
  - Mouse listeners for pit selection.
  - Buttons for undo and board style selection.

#### C. **Styling and Customization**

- Created two styles for the board using the **Strategy Pattern**:
  - Classic style with circular pits.
  - Modern style with rectangular pits and vibrant colors.
- Designed styles as pluggable modules, allowing easy addition of more styles in the future.

#### D. **Testing and Debugging**

- Built `MancalaTest.java` as the main entry point to test the application.
- Tested various edge cases, including:
  - Capturing stones.
  - Undoing moves.
  - Game-ending scenarios.
- Debugged and refined the GUI for better usability.

---

## Features

- **Interactive Gameplay**: Two human players can play on the same board.
- **Undo Functionality**: Up to 3 undos per turn.
- **Custom Board Styles**: Players can choose their preferred style before starting the game.
- **Dynamic Updates**: The GUI updates in real-time as players make moves.
- **Input Validation**: Ensures valid numbers of stones and proper player actions.
