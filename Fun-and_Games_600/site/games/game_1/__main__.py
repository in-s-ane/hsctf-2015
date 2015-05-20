import sys
import argparse
import random
secret_flag = "NOT_HERE"
def check_randomness(random_func):
    evens = 0
    #amount = 10000 # Useless variable
    for i in xrange(10000):
        evens += random_func()%2
    #return abs(10000/2 - evens) < 1000
    return abs(5000 - evens) < 1000

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("bet",
                        type = int)
    parser.add_argument(
        'nums', metavar = 'guess', nargs = '+', type = int,
        help = 'an integer to be summed')
    args = parser.parse_args()
    name = parser.prog
    print name
    print args
    nums = args.nums
    bet = args.bet
    random_num = lambda : random.randrange(bet)
    try:
        if not check_randomness(random_num):
            print "Redoing randomness for game number %d." % int(name[5:])
            random.seed()
        num = nums.pop()
        for i in range(100):
            if num == random_num():
                num = nums.pop()
    except Exception, e:
        print e
        if bet < 10**15:
            return "Success! You bet %d which is less than one million so you don't win the flag." % bet
        else:
            return "Success! You bet %d which is more than one million so you win the flag. The flag is: %s" % (bet, secret_flag)
    return "Failure."


if __name__ == '__main__':
    print main()
