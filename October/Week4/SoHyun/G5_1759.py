from itertools import combinations

l, c = map(int, input().split())
alphabets = input().split()

alpha = combinations(sorted(alphabets), l)

answer = []

for al in alpha:
    consonant = 0
    vowel = 0
    
    for a in al:
        if a in "aeiou":
            consonant += 1
        else:
            vowel += 1
            
    if consonant >= 1 and vowel >= 2:
        print("".join(al))