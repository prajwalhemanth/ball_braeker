# ball_braeker
ball breaker game using java
BallBreaker
This is a 2D ball break game implemented in Java Swing. Give it try and study well project for college stuents. Games has interesting poweres and nice graphics. Stages can be modified and increased easily by editing file Game.java. Main.java drive the project.

------------Ball Breaker
The whole logic behind is written by me from scratch.

It was helpful to read tutorials from http://zetcode.com/tutorials/java2dtutorial/

This is 2D game desinged in Java using Swing package.

Game starts with Introduction window and 'Start Game' button.

Information states type of bricks and power in the game. Levels:- 3 Levels are designed as of now.

Any number of levels can be desinged by just inserting elements in stage[][] array in Game class and editing respective levels in 'stages' variable.

Pad:- Pad can bare only one power at a time, either Weapon, big puddle or small puddle.

It's independant of power of ball.

Ball:- Ball can be either normal or Super ball. Ball should not be under Pad level.

Bullet:- It's been controleld to have at maximum 3 bullets on screen at a time and next can be fired when one of bullet go out of screen or collide with brick.

Brick:- Strength of brick depends on type- 1.normal, 2.hard and 3.Super.

Power:- There would be at max 10 power hidden in bricks assigned randomly. Power get unlocked when ball strokes the brick or bullet do.

Controls:-

Move pad using keyboard LEFT, RIGHT direction kets. (<- and ->)

Bullet can be fired using SPACE button if pad has the weapon power.

This game has been tested on mac osx mountain lion, linux (Ubuntu) and windows 7 environment. Some pixel may differ in two java implementation for different platforms.

If you are trying to run BallBreaker.jar on linux platform, copy it your disk and set permission executable by command

go to path of your .jar file

run 'sudo chmod +x BallBreaker.jar'

Thank you.
