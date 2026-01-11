# Serena
## by Cauã Grigolatto and its dog, Lyon

[English]:

Project Serena is a Java-based command interpreter (and a purely personal project) that aims to automate manual tasks performed on the computer, such as typing, clicking, moving the mouse, and others.

Its clear and natural syntax, combined with the possibilities of programming languages such as loops and functions, brings ordinary users closer to technical programmers.

With Serena, you can execute (for now) the following list of commands:
* EXECUTE: executes a computer program. Requires either the path to the program's binaries or its executable terminal command;
* MOVE MOUSE: moves the mouse pointer to an arbitrary x,y position, e.g. MOVE MOUSE 200,300;
* TYPE: type something using your keyboard;
* WAIT SECONDS/MILLIS: make Serena sleep for a specified time (an integer number);
* CLICK RIGHT: performs a right-click with the mouse;
* CLICK LEFT: performs a left-click with the mouse;
* COPY: copy a text to your clipboard;
* DISPLAY: it displays something in the standard output, which is great for debugging;
* PASTE: paste the value that is in your clipboard;
* PRESS: press a special key, such as ENTER.

That's not all. Serena allows you to program commands according to your needs, using the command list below:
* ASSIGN: assigns a value to a variable;
* BLOCK..END BLOCK: defines a Serena-reusable block of code;
* CALL: executes a block of code defined by its name;
* REPEAT..END REPEAT: executes a block of code a predetermined number of times;
* SCHEDULE/SCHEDULE FOR...END SCHEDULE: allows you to schedule the execution of a block of code;
* SET: used to configure user preferences, such as date and time format;
* INCLUDE: use the contents from other Serena files.

Examples of use

1. Opening YouTube with a simple command.
```
ASSIGN target_program "C:\Users\myUser\AppData\Local\Programs\Opera GX\opera.exe"

EXECUTE "$target_program"

WAIT SECONDS 2

TYPE "youtube.com"

PRESS ENTER
```

2. Setting up automatic message sending.
```
REPEAT 10
	TYPE "Have a great day!"
	WAIT MILLIS 500
	PRESS ENTER
	WAIT SECONDS 1
END REPEAT
```

3. Sending a message to different people.
```
// this is a global variable
ASSIGN message "Support Free Software Community!"

// "to" is a parameter - could be several of them: to, when, message...
BLOCK send_message to
  // this is a local variable
  TYPE "$to"
  WAIT SECONDS 2
  TYPE "$message"
  WAIT MILLIS 500
  PRESS ENTER
  DISPLAY "Message sent successfully!"
END BLOCK

ASSIGN special_email "cauagrigolatto23@gmail.com"
CALL send_message test1@gmail.com
CALL send_message $special_email
```

4. Scheduling the execution of a specific program
```
SET DATE_TIME_FORMAT "yyyy-MM-dd HH:mm"

SCHEDULE FOR 2026-02-25 12:00
  EXECUTE "notepad"
  WAIT SECONDS 1
  TYPE "Happy Birthday :)"
  WAIT MILLIS 500
  PRESS ENTER
END SCHEDULE
```
Feel free to automate any task. But remember: Serena is still in an early stage of development, which means that the syntax and how commands are executed may change.

[Português]:



