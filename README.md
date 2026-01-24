# Serena
## by Cauã Grigolatto and his dog, Lyon

Project Serena is a Java-based command interpreter (and a purely personal project) that aims to automate manual tasks performed on the computer, such as typing, clicking, moving the mouse, and others.

Its clear and natural syntax, combined with the possibilities of programming languages such as loops and functions, brings ordinary users closer to technical programmers.

With Serena, you can execute (for now) the following list of commands:

* EXECUTE: executes a computer program. Requires either the path to the program's binaries or its executable terminal command;
```
EXECUTE "notepad"
EXECUTE "C:\Users\myUser\AppData\Local\Programs\Opera GX\opera.exe"
```

* MOVE MOUSE: moves the mouse pointer to an arbitrary x, y position;
```
MOVE MOUSE 190 , 200
```

* TYPE: type something using your keyboard. It is still in beta version, as it does not accept accents.;
```
TYPE "I love Java <3"
```

* WAIT SECONDS/MILLIS: make Serena sleep for a specified time (an integer number);
```
EXECUTE "notepad"

WAIT SECONDS 2

TYPE "I love Java <3 "

WAIT MILLIS 600

TYPE "I love Serena <3"
```

* CLICK RIGHT: performs a right-click with the mouse;
```
MOVE MOUSE 120 , 340

WAIT MILLIS 400

CLICK RIGHT
```

* CLICK LEFT: performs a left-click with the mouse;
```
MOVE MOUSE 2040 , 160

WAIT MILLIS 400

CLICK LEFT
```

* COPY: copy a text to your clipboard;
```
COPY "Support Free Software!"
```

* DISPLAY: it displays something in the standard output, which is great for debugging;
```
DISPLAY "Support Free Software!"
```

* PASTE: paste the value that is in your clipboard;
```
COPY "I play Minecraft"
PASTE
```

* PRESS: press a special key, such as ENTER, SPACE, BACKSPACE and TAB.
```
TYPE "I play Minecraft"

WAIT MILLIS 400

PRESS ENTER

WAIT MILLIS 400

TYPE "I play Fortnite"
```

But that's not all. Serena allows you to program commands according to your needs, using the command list below:

* ASSIGN: assigns a value to a variable;
```
ASSIGN program "notepad"

EXECUTE "$program"
```

* ASSIGN LIST: assign a list of values ​​to a variable;
```
ASSIGN LIST languages "Java" "C++" "Python"
```

* FOR EACH: iterates through a list of values;
```
ASSIGN LIST languages "Java" "C++" "Python"

FOR EACH lang IN $languages
	TYPE "I like programming in $lang"
	WAIT MILLIS 200
	PRESS ENTER
	WAIT MILLIS 200
END FOR EACH
```

```
FOR EACH country IN "Brazil" "France" "China"
	TYPE "$country is beautiful!"
	WAIT MILLIS 200
	PRESS ENTER
	WAIT MILLIS 200
END FOR EACH
```


* BLOCK: defines a Serena-reusable block of code, in which you can define a series of parameters;
```
BLOCK greet someone
	TYPE "Hello, $someone. What's up?"
END BLOCK
```

* CALL: executes a block of code defined by its name;
```
BLOCK greet someone
	TYPE "Hello, $someone. What's up?"
	WAIT MILLIS 300
	PRESS ENTER
	WAIT MILLIS 300
END BLOCK

CALL greet "Caua Grigolatto"
CALL greet "Linus Torvalds"
```

* REPEAT: executes a block of code a predetermined number of times;
```
REPEAT 5
	TYPE "I love programming <3"
	WAIT MILLIS 300
	PRESS ENTER
	WAIT MILLIS 300
END REPEAT
```

```
ASSIGN times "3"

REPEAT $times
	TYPE "Hello from Serena."
	WAIT MILLIS 200
	PRESS ENTER
	WAIT MILLIS 200
END REPEAT
```

* SCHEDULE/SCHEDULE FOR: allows you to schedule the execution of a block of code at various times;
```
SCHEDULE FOR "25/02/2026 00:00" "25/02/2027 00:00"
	TYPE "Happy Birthday!"
END SCHEDULE
```

* SET: used to configure user preferences, such as date and time format;
```
SET DATE TIME FORMAT "yyyy-MM-dd hh:mm:ss"

SCHEDULE FOR "2026-07-26 15:30:20"
	TYPE "Hello, world."
END SCHEDULE
```

* INCLUDE: use the contents from other Serena files.
```
// file1.ser

BLOCK say_hello
	TYPE "Hello from the other file!"
END BLOCK
```

```
// My main script

INCLUDE "C:\Users\myUser\Serena Scripts\file1.ser"

CALL say_hello
```
## How to run Serena?

First, you need at least JDK 21 and to set the environment variables to point to this version. 

Next, download the available jar file and run it with the following command: java -jar serena.jar "/path/to/script.ser".

Or, if you prefer, download the project code, modify it as you see fit, and compile it using your IDE or terminal.
