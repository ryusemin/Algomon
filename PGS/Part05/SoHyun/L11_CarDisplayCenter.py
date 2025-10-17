from collections import deque

INT_MAX = 10**9

n, m, k = tuple(map(int, input().split()))

edges = [[] for _ in range(n)]

for _ in range(m):
    x, y = tuple(map(int, input().split()))
    edges[x - 1].append(y - 1)

start_points = list(map(int, input().split()))

for i in range(k):
    start_points[i] = start_points[i] - 1

v = [[0] * n for _ in range(k)]

step = [[-1] * n for _ in range(k)]

ans = INT_MAX

def bfs(i):
    s_num = start_points[i]

    for j in range(n):
        step[i][j] = -1

    q = deque()
    v[i][s_num] = 1
    step[i][s_num] = 0
    q.append(s_num)

    while q:
        x = q.popleft()
        for y in edges[x]:
            if not v[i][y]:
                v[i][y] = 1
                step[i][y] = step[i][x] + 1
                q.append(y)

for i in range(k):
    bfs(i)

for i in range(n):
    impossible = 0
    max_t = -1
    for j in range(k):
        if not v[j][i]:
            impossible = 1

        max_t = max(max_t, step[j][i])

    if not impossible:
        ans = min(ans, max_t)

if ans == INT_MAX:
    ans = -1

print(ans)
