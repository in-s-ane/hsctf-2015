#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
char letters[4];
 
void printf_flag() {
  printf("{flag}\n");
  fflush(stdout);
}
 
void genome_loop() {
  char input[4];
  char genome[64];
  int i, j;
  int index = 0;
  printf("$ ");
  fflush(stdout);
  while (fgets(input, 5, stdin)) { // fgets will insert '\0' after the end of input
    int input_length = strlen(input);
    if (input_length == 1) {
      printf("$ ");
      fflush(stdout);
      continue;
    }
     
    if (strlen(genome) >= 64) { // We can bypass this check if the first character is '\0'
      printf("Genome complete. You have made a koala!.\n");
      fflush(stdout);
      break;
    } else {
      int length = strlen(input);     
      int is_valid_genome = 1;
      for (i = 0; i < length; i++) {
        int is_nucleotide = 0;
        for (j = 0; j < 4; j++) {
          if (input[i] == letters[j]) {
            is_nucleotide = 1;
            break;
          }
        }
        if (!is_nucleotide) {
          is_valid_genome = 0;
          break;
        }
      }
      
      if (!is_valid_genome) {
        printf("Not a valid genome.\n");
        fflush(stdout);
	break;
      }
 
      for (i = 0; i < length; i++) {
        genome[index] = input[i];
        index++;
      }
    }
    printf("$ ");
    fflush(stdout);
  }
}
 
int main(int argc, char** argv) {
  printf("Welcome to the Edelman Genome.\n");
  printf("It's well-known that all 100 children of the Edelman family are extremely similar. However, for decades, nobody has known why.\n");
  printf("We've discovered the reason.\n");
  printf("The Edelman family has a cloning machine. To use it, they input every 4 nucleotides in a genomic sequence.\n");
  printf("Every nucleotide is either A, T, G, or C.\n");
  printf("Every sequence of A's, T's, G's, and C's produces a unique creature.\n");
  printf("For example, you can create an Tralok, a Huice, a Shris, a Khanesh, a Sidhar, or an Edelman.\n");
  printf("We've replicated the Edelman Genome Cloning Machine (EGCM) here. You are free to play around with it.\n\n");
  printf("To use the EGCM, enter a series of characters. Each one must be either A, T, G, or C.\n");
  printf("We'll tell you what kind of creature you've made. Have fun!\n");
  fflush(stdout); 
  letters[0] = 'A';
  letters[1] = 'T';
  letters[2] = 'G';
  letters[3] = 'C';
  genome_loop();
  return 0;
}
