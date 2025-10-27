n, k = map(int, input().split())

coins = []
for _ in range(n):
    coins.append(int(input()))

coins.sort(reverse = True)

cnt = 0
for i in coins:
    if k >= i:
        cnt += k // i
        k %= i
        if k <= 0:
            break
        
print(cnt)