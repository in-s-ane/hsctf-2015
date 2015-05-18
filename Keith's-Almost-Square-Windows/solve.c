#include <stdio.h>
#include <stdlib.h>
#include <gmp.h>

int main(int argc, char *argv[]) {
    mpz_t total;
    mpz_init_set_str(total, "0", 10);

    mpz_t i;
    mpz_init_set_str(i, "1", 10);

    mpz_t upper_bound;
    mpz_init_set_str(upper_bound, "9999999996", 10);
    printf("upper_bound: ");
    mpz_out_str(stdout, 10, upper_bound);
    printf("\n");

    mpz_t prev_pow;
    mpz_init_set_str(prev_pow, "1", 10);

    for (; mpz_cmp(i, upper_bound) < 0; mpz_add_ui(i, i, 1)) {
        // (i+1)^2
        mpz_t tmp;
        mpz_init(tmp);
        mpz_add_ui(tmp, i, 1);
        mpz_pow_ui(tmp, tmp, 2);

        // prev_pow + tmp
        mpz_t tmp_sum;
        mpz_init(tmp_sum);
        mpz_add(tmp_sum, prev_pow, tmp);

        // sqrt(prev_pow + tmp)
        mpf_t result;
        mpf_init(result);
        mpf_set_z(result, tmp_sum);
        mpf_sqrt(result, result);

        mpz_clear(tmp_sum);

        mpz_set(prev_pow, tmp);
        mpz_clear(tmp);

        // (int) result
        mpz_t result_int;
        mpz_init(result_int);
        mpz_set_f(result_int, result);

        // (float) (int) result
        mpf_t result_int_float;
        mpf_init(result_int_float);
        mpf_set_z(result_int_float, result_int);

        // result = (float) (int) result ?
        if (mpf_cmp(result, result_int_float) == 0) {
            mpz_out_str(stdout, 10, i);
            printf("\n");
            mpz_add(total, total, i);
        }

        mpz_clear(result_int);
        mpf_clear(result_int_float);
        mpf_clear(result);

    }

    printf("Total: ");
    mpz_out_str(stdout, 10, total);
    printf("\n");
    mpz_clear(total);
    mpz_clear(i);
    mpz_clear(upper_bound);
    mpz_clear(prev_pow);

    return 0;
}
