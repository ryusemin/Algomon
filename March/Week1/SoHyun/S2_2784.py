import sys
input = sys.stdin.readline
from itertools import permutations

arr = [input().strip() for _ in range(6)]
arr.sort()

answers = []

for comb in permutations(arr, 3):
    rows = list(comb)
    cols = []

    for c in range(3):
        col = rows[0][c] + rows[1][c] + rows[2][c]
        cols.append(col)

    candidate = rows + cols
    candidate.sort()

    if candidate == arr:
        answers.append(rows)

if answers:
    answers.sort()
    for line in answers[0]:
        print(line)
else:
    print(0)