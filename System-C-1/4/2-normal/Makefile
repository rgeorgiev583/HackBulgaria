CC=gcc
LDFLAGS=-lpthread

all: chat-server-sockets chat-client-sockets

chat-server-sockets:
	$(CC) chat-server-sockets.c $(LDFLAGS) -o chat-server-sockets

chat-client-sockets:
	$(CC) chat-client-sockets.c $(LDFLAGS) -o chat-client-sockets

clean:
	rm -f *.o chat-server chat-client

