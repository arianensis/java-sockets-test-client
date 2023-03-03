# Java Sockets Test Project - Client
This is a project I'm doing in my free time to experiment with Java sockets. The idea is that any numbers of this project can be run and connect to [the server](https://github.com/arianensis/java-sockets-test-server).

## What are Sockets?
A socket is a two way connection between two programs through a web address and a port, which gives a really fast way to send data between computers in an efficient way without the use of files such as json or HTTP infrastructure such an API REST. I'm just finding out about Sockets but they seem to me like a great tool to make real-time interacting programs such as chats or games, so I will use this project to experiment with these possibilities.

## Why Java?
Basically it's the language we learnt at school and it seemed very easy to set up a socket connection between two programs using it.

## This version
The repository tag **A.2** corresponds to a functioning, yet very simple, interface. It runs as a command-line app which is a client. It will ask for address and port of the server to which to connect. If everything is OK, it will connect and wait for a text. Upon input, creates a Message object and sends it to the server, which must return the exact same object.

Additionally, if the first eight characters of the text are numbers, they will be read as XXXXYYYY and the Message object will have its two int attributes x and y set accordingly.