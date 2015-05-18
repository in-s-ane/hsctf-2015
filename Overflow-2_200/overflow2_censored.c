#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void seashell() {
  printf("Our systems are breached :(\n");
  printf("{flag}\n");
  fflush(stdout);
}

void this_seems_bad(char *input) {
  char buf[64];
  strcpy(buf, input);
  printf("Phew, we're okay here.\n");
  fflush(stdout);
}

int main(int argc, char** argv) {
  char temp[128];
  fgets(temp, 128, stdin);
  this_seems_bad(temp);
  return 0;
}
