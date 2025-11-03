import math

n = int(input())

a = [True] * (n+1)
a[0] = False
a[1] = False

prime_num = []

for i in range(2, n+1):
    if a[i]:
        prime_num.append(i)
        for j in range(2*i, n+1, i):
            a[j] = False
            
answer = 0
s = 0
e = 0

while e <= len(prime_num):
    tmp = sum(prime_num[s:e])
    if tmp == n:
        answer += 1
        e += 1
    elif tmp < n:
        e += 1
    else:
        s += 1
        
print(answer)