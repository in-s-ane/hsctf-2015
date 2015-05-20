from random import randrange, random
import commands
import string
from flask import Flask, request
# Using rhe latest Python Version as of May 16th, 2015 for Ubuntu.
app = Flask(__name__)

def run(game_path, bet, nums):
    if any(i not in string.letters+string.digits+"/_" for i in game_path):
        return "INVALID PATH"

    # There is only one game for now, but this stuff is here so I can add games in the future if I want to.
    return commands.getoutput("python games/%s %d "%(game_path, bet)+" ".join(nums))

@app.route('/<path:game_path>')
def get_game(game_path):
    try:
        bet = request.args.get('bet', '')
        nums = request.args.get('nums', '').split(",")
        if not bet.isdigit():
            return "THE BET IS NOT A POSITIVE NUMBER"
        if not all(num.isdigit() for num in nums):
            return "A NUM IS NOT A POSITIVE NUMBER"
    except Exception as e:
        return str(e)
        return "INVALID ARGUMENTS"
    return run(game_path, int(bet), nums)
if __name__ == "__main__":
    app.run(host='0.0.0.0', port = 5002)
