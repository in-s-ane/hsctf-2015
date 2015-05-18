#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXLEN 64
int current_marbles = 10;

void print_flag() {
  printf("{flag}\n");
  fflush(stdout);
}

void withdraw(int delta) {
  if (delta < 0) {
    printf("You cannot withdraw a negative amount of marbles.\n");
  } else if (current_marbles - delta < 0) {
    printf("You cannot withdraw more marbles than you have.\n");
  }
  fflush(stdout);
  current_marbles  -= delta;
}

void deposit(int delta) {
  if (delta < 0) {
    printf("You cannot deposit a negative amount of marbles.\n");
    fflush(stdout);
  }
  current_marbles += delta;
}

int validate_password() {
  char input[MAXLEN];
  gets(input); // LOL THAT BUFFER OVERFLOW OBVIOUS MUCH??
  return (strcmp("hue_hue_psswd", input) == 0);
}

int main(int argc, char **argv) {  
  char transaction[16];
  fgets(transaction, 16, stdin);
  char amount[4];
  fgets(amount, 4, stdin);

  printf("What is your box's password?\n");
  fflush(stdout);
  if (validate_password() != 1) {
    printf("Invalid password\n");
    fflush(stdout);
    return -1;
  } 

  if (strcmp(transaction, "withdraw\n") == 0) {
    withdraw(strtol(amount, NULL, 10));
  } else if (strcmp(transaction, "deposit\n") == 0) {
    deposit(strtol(amount, NULL, 10));
  } else {
    printf("%s is not a valid action.\n", transaction);
    fflush(stdout);
    return -2;
  }

  return 0;
}

