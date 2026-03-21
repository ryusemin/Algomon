import sys
input = sys.stdin.readline
from collections import defaultdict

N, K, M = map(int, input().split())

cnt = defaultdict(lambda: defaultdict(int))

for _ in range(N):
    s = input().strip()
    for i in range(len(s)-1):
        cnt[s[i]][s[i+1]] += 1

next_char = {}

for c in cnt:
    best = None
    best_cnt = -1
    for nc in cnt[c]:
        if cnt[c][nc] > best_cnt:
            best_cnt = cnt[c][nc]
            best = nc
        elif cnt[c][nc] == best_cnt:
            if nc < best:
                best = nc
    next_char[c] = best

visited = {}
order = []

cur = '['

while cur not in visited:
    visited[cur] = len(order)
    order.append(cur)
    
    if cur == ']':
        break
    
    cur = next_char[cur]

result = []

is_end = (order[-1] == ']')

if not is_end:
    cycle_start = visited[cur]
    cycle = order[cycle_start:]

for i in range(K-1, K+M-1):
    if i < len(order):
        result.append(order[i])
    else:
        if is_end:
            result.append('.')
        else:
            idx = (i - cycle_start) % len(cycle)
            result.append(cycle[idx])

print(''.join(result))