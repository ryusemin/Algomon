def find(x, d):
    global result
    if x < 500:
        return
    if d == n:
        result += 1
        return
    x -= k
    
    for i in range(n):
        if not v[i]:
            v[i] = 1
            find(x + kit[i], d+1)
            v[i] = 0

n, k = map(int, input().split())
kit = list(map(int, input().split()))

result = 0
v = [0] * n

find(500, 0)
print(result)