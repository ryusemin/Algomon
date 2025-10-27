n, m = map(int, input().split())
arr = [int(input()) for _ in range(n)]
arr.sort()

answer = int(2e9)
s = 0
e = 0

while e < n:
    v = arr[e] - arr[s]
    if v > m:
        answer = min(answer, v)
        s += 1
    elif v < m:
        e += 1
    else:
        answer = m
        break
    
print(answer)