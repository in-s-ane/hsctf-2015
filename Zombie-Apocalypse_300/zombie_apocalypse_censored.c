#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
#define ZOMBIES 32
 
typedef struct {
  char name[25];
  int height;
} zombie;
 
void fight_zombies() {;
  int i;
   
  zombie zombies[ZOMBIES];
   
  for (i = 0; i < ZOMBIES; i++) {
    char temp_name[8];
    int temp_height;
    printf("Zombie name: ");
    fflush(stdout);
    scanf("%s", temp_name); // Buffer overflow into temp_name
    printf("Zombie height: ");
    fflush(stdout);
    scanf("%d", &temp_height);
    if (temp_height < 0 || temp_height > 100) {
      printf("Invalid height.\n");
      fflush(stdout);
      return;
    }
    zombies[i].height = temp_height;
    strcpy(zombies[i].name, temp_name); // Later copies temp_name into zombie struct to overwrite height
 
    if (zombies[i].height > 100) {
      printf("Wow. You are a brave warrior. Here is the flag: {flag}\n");
      fflush(stdout);
    }
  }
}
 
int main(int argc, char** argv) {
  fight_zombies();
  return 0;
}
