#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LENGTH 8

char grid[LENGTH][LENGTH];

void print_flag() {
  printf("{flag}");
  fflush(stdout);
}

void set_up_game() {
  gid_t gid = getegid();
  setresgid(gid, gid, gid);

  int i, j;
  for (i = 0; i < LENGTH; i++) {
    for (j = 0; j < LENGTH; j++) {
      grid[i][j] = 'R';
    }
  }
}

void print_grid() {
  int i, j;
  for (i = 0; i < LENGTH; i++) {
    for (j = 0; j < LENGTH; j++) {
      printf("%c", grid[i][j]);
    }
    printf("\n");
  }
  fflush(stdout);
}

void save_game() {
  FILE *saved_game = fopen("/home/saved_game.txt", "w");
  if (saved_game == NULL) {
    printf("Error in opening saved game file.\n");
    fflush(stdout);
    return;
  }

  int i, j;
  for (i = 0; i < LENGTH; i++) {
    for (j = 0; j < LENGTH; j++) {
      fprintf(saved_game, "%c", grid[i][j]);
    }
  }
  fclose(saved_game);
  printf("Game saved.\n");
}

void load_game() {
  FILE *saved_game = fopen("/home/saved_game.txt", "r");
  if (saved_game == NULL) {
    printf("Error in opening saved game file.\n");
    fflush(stdout);
    return;
  }

  int i, j;
  for (i = 0; i < LENGTH; i++) {
    char saved_game_row[LENGTH + 1];
    fgets(saved_game_row, LENGTH + 1, saved_game);
    for (j = 0; j < LENGTH; j++) {
      grid[i][j] = saved_game_row[j];
    }
  }
  fclose(saved_game);
  printf("Game loaded.\n");
  fflush(stdout);
}

void check() {
  FILE *solution = fopen("/home/solution.txt", "r");
  if (solution == NULL) {
    printf("Error in opening solution file.\n");
    fflush(stdout);
    return;
  }
  
  int i, j;
  for (i = 0; i < LENGTH; i++) {
    char solution_row[LENGTH + 1];
    fgets(solution_row, LENGTH + 1, solution);
    for (j = 0; j < LENGTH; j++) {
      if (solution_row[j] != grid[i][j]) {
        printf("Incorrect solution. Discrepancy at row %d and column %d\n", i, j);
        fflush(stdout);
	return;
      }
    }
  }
  fclose(solution);
  printf("Correct! You win the game! But no flag ;).\n");
  fflush(stdout);
}

void toggle(char *command) {
  int i, j;
  int length = strlen(command);
  if (length % 3 != 0) {
    printf("Invalid toggle: length not a multiple of 3\n");
    fflush(stdout);
    return;
  }
  
  FILE *solution_file = fopen("/home/solution.txt", "r");
  char solution[LENGTH][LENGTH];
  if (solution_file == NULL) {
    printf("Error in opening solution file.\n");
    fflush(stdout);
    return;
  }

  for (i = 0; i < LENGTH; i++) {
    char solution_row[LENGTH + 1];
    fgets(solution_row, LENGTH + 1, solution_file);
    for (j = 0; j < LENGTH; j++) {
      solution[i][j] = solution_row[j];
    }
  }

  for (i = 0; i < length; i += 3) {
    int row = command[i] - 48; // Subtracting the offset of the '0' char
    int col = command[i + 1] - 48;
    if (row < 0 || col < 0 || row > LENGTH - 1 || col > LENGTH - 1) { // After bounds checking, we still continue even if out of bounds
      printf("Invalid toggle: coordinates out of bounds %d%d.\n", row, col);
      fflush(stdout);
    }
    char color = command[i + 2];
    grid[row][col] = color; // Can buffer overflow here since row 8+ and col 8+ can be overwritten
    if (grid[row][col] != solution[row][col]) {
      printf("%c", 'F');
   } else {
      printf("%c", 'T');
    }
    fflush(stdout);
  }
  printf("\n");
  fflush(stdout);
}

void game_loop() {
  char input[128];
  printf(">> ");
  fflush(stdout);
  while (fgets(input, 128, stdin)) {
    if (strlen(input) == 1) {
      printf(">> ");
      fflush(stdout);
      continue;
    }
    char *command = strtok(input, " \n");
    if (strcmp(command, "print") == 0) {
      print_grid();
    } else if (strcmp(command, "save") == 0) {
      save_game();
    } else if (strcmp(command, "load") == 0) {
      load_game();
    } else if (strcmp(command, "toggle") == 0) {
      char *arg = strtok(NULL, "\n");
      toggle(arg);
    } else if (strcmp(command, "check") == 0) {
      check();
    } else {
      printf("Unknown command: %s\n", command);
      fflush(stdout);
    }
    printf(">> ");
    fflush(stdout);
  }
}

int main(int argc, char** argv) {
  set_up_game();
  printf("Welcome to Connect the Colors!\n");
  printf("To play this game, you must try to toggle each square to get the winning configuration.\n");
  printf("Each cell is either red, blue, or green. Toggling a cell changes its color.\n");
  printf("You can toggle cell [i][j] by simply entering toggle ijX. i and j range from 0 to 7, and X is either R, B, or G.\n");
  printf("You can also toggle several cells at once by entering toggle abXcdYefZ ...\n");
  printf("This would toggle cells [a][b], [c][d], and [e][f] to colors X Y, and Z.\n");
  printf("Toggle also prints out a space-separated list of F's and T's, where T corresponds to a query toggle being correct\n");
  printf("and F corresponds to a query toggle being wrong.\n");
  printf("If you would like to print out the grid, simply enter print.\n");
  printf("You can also save the game by entering the save command.\n");
  printf("Naturally, you can also load a saved game with the load command.\n");
  printf("Once you're confident about solution, you can check against the answer using the check command.\n");
  printf("Good luck and have fun!\n");
  fflush(stdout);
  game_loop();
  return 0;
}
