#include <stdio.h>
#include <stdlib.h>

#define IP_LENGTH 128
char ip[IP_LENGTH];

void check_ip() {
  int length = strlen(ip) - 1;
   if (length != 40) {
    printf ("IP address not correct size.\n");
    return;
  }

  int i;
  char encrypted[length];
  for (i = 0; i < length; i++) {
    if (!((48 <= ip[i] && ip[i] <= 57) || (97 <= ip[i] && ip[i] <= 102))) {
      printf("Not a valid IPv42 address.\n");
      return;
    }
  }
  
  for (i = 0; i < length; i++) {
    char c = ip[i];
    c = c ^ (c >> 4);
    encrypted[i] = (char)(c);
  }
  
  if (strncmp(encrypted, "2004e3bcbcgbdcc`2004`27:2004`27:bcgbdcc`", length) == 0) {
    printf("Ayy, you got it.\n");
  } else {
    printf("Nop, that ain't it.\n");
  }
}

int main(int argc, char** argv) {
  printf("In the prehistoric times, there was IPv4. In the almost-prehistoric-times, there was IPv6. Now, there's IPv42.\n");
  printf("IPv42 IP addresses are in the following form:\n");
  printf("A block is defined as a set of 8 consecutive hexadecimal digits. For example, aaaaaaaa, a8sd7fa8 are valid blocks, but go839a0f is not because g is not a hexadecimal digit.\n");
  printf("The entire IP address is a set of 5 consecutive non-separated blocks. For example, abfefa1212cfafde65bcdfae is a set of 3 such blocks because there are 24 total characters each of which is a valid hexadecimal character.\n");
  printf("Keith hasn't bought a domain though, you need to explicitly input the IP address to get access to it.\n");
  printf("What do you think Keith's IP address?\n");
  fgets(ip, IP_LENGTH, stdin);
  check_ip();
  return 0;
}
