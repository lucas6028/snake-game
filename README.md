# Snake Game

![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/2p.png)

This is a classic Snake game implemented in Java using Swing for the graphical user interface. The game supports both single-player and two-player modes.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Game Features](#game-features)
- [Controls](#controls)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/Snake-game.git
   cd Snake-game/demo
   ```

2. Build the project using Maven:

   ```sh
   mvn clean install
   ```

3. Run the game:
   ```sh
   java -jar target/Snake-game.jar
   ```

## Usage

- Run the game and use the keyboard to control the snake(s).
- Eat fruits to gain points and avoid bombs.
- The game ends when a snake collides with itself, another snake, or a bomb.

## Game Features

- Single-player and two-player modes.
- Various types of fruits and bombs.
- Increasing difficulty levels.
- Sound effects for different game events.

## Controls

### Single Player

- Arrow keys to move the snake.
- Spacebar to shoot bullets.

### Two Players

- Player 1: Arrow keys to move, Enter to shoot.
- Player 2: WASD keys to move, Spacebar to shoot.

## Project Structure

```
.vscode/
    settings.json
pom.xml
src/
    main/
        java/
            com/
                snake/
                    Bomb.java
                    Bullet.java
                    ContainerPanel.java
                    Draw.java
                    Fruit.java
                    ImageLoader.java
                    Main.java
                    Node.java
                    OpeningScreen.java
                    ScoreFile.java
                    Snake.java
                    Sounds.java
                    Stone.java
                    TransparentButton.java
    resources/
        filename.txt
        images/
            background/
            bombs/
            buttons/
            fruits/
        sounds/
test/
    java/
        com/
            snake/
target/
    classes/
        com/
            snake/
        filename.txt
        images/
            background/
            bombs/
            buttons/
            fruits/
        sounds/
    generated-sources/
        annotations/
    maven-archiver/
        pom.properties
    maven-status/
        maven-compiler-plugin/
            compile/
            testCompile/
    Snake-game.jar
    test-classes/
        com/
            snake/
```

## Screenshots

![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/p1p2.png)
![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/1.png)
![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/2.png)
![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/3.png)
![alt text](https://github.com/lucas6028/snake-game/blob/main/src/main/resources/images/readme/4.png)

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
