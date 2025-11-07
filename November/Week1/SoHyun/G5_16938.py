from itertools import combinations

n, l, r, x = map(int, input().split())
d = list(map(int, input().split()))
cnt = 0

for i in range(2, n+1):
    dc = list(combinations(d, i))
    for j in dc:
        if l <= sum(j) <= r and max(j) - min(j) >= x:
            cnt += 1
            
print(cnt)