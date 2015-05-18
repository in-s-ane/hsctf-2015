#include <stdio.h>
#include <stdlib.h>

#define LENGTH 40

int main() {
    char encrypted[] = "2004e3bcbcgbdcc`2004`27:2004`27:bcgbdcc`";
    char decrypted[LENGTH+1];
    int i;
    for (i=0; i<LENGTH; i++) {
        char c = encrypted[i];
        decrypted[i] = c ^ (c >> 4);
    }
    decrypted[LENGTH] = '\0';
    printf("%s\n", decrypted);

    return 0;
}

/**
 * All we have to do is xor the values again by the same bit-shifted amount
 * and we get the input that works.
 *
 * Flag: 1337c0dedeadbeef1337f1491337f149deadbeef
 */
