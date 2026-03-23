from collections import deque
import sys
input = sys.stdin.readline

n, k = map(int, input().split())
seq = tuple(map(int, input().split()))

q = deque([(seq, 0)])
v = set()

while q:
    cur, d = q.popleft()
    
    if cur == tuple(range(1, n+1)):
        print(d)
        break
    
    for i in range(n-k+1):
        next_seq = cur[:i] + cur[i:i+k][::-1] + cur[i+k:]
        if next_seq not in v:
            v.add(next_seq)
            q.append((next_seq, d+1))
else:
    print(-1)