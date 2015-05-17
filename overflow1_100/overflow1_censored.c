#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
void this_looks_bad(char* in) {
  int bad = 0;;
  char buf[64];
  strcpy(buf, in);
  if (bad == 0x13371337) {
    printf("Oh no! It's not safe! You can read the flag now! Ahhhhhh\n");
    printf("{flag}\n");
  } else {
    printf("Phew, I'm still safe\n");   
  }
  printf("bad: %d\n", bad);
  printf("buf: %s\n", buf);
  fflush(stdout);
}
 
int main(int argc, char** argv) {
  char temp[128];
  fgets(temp, 128, stdin);
  this_looks_bad(temp);
  return 0;
}
