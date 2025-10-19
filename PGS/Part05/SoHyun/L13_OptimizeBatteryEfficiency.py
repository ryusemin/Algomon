from collections import defaultdict

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

n, m = map(int, input().split())
board = [[0] * (m + 1) for _ in range(n+1)]
ans = -int(1e9)

def isOutrange(x, y):
    return not (1 <= x <= n and 1 <= y <= m)

def conv(x, y):
    return (x - 1) * m + y

def reconv(num):
    x = 1 + (num - 1) // m
    y = 1 + (num - 1) % m
    return (x, y)

vis = defaultdict(bool)
lef = []
se = set()
ve = []

v = []

def doit(num):
    if num == 5:
        newv = sorted(v)
        if tuple(newv) not in se:
            ve.append(newv)
            se.add(tuple(newv))
        return
    
    for i in range(len(lef)):
        next_val = lef[i]
        if vis[next_val]:
            continue
        vis[next_val] = True
        v.append(next_val)

        cur = reconv(next_val)
        x, y = cur
        
        for dir in range(4):
            nx = x + dx[dir]
            ny = y + dy[dir]

            if isOutrange(nx, ny):
                continue
            lef.append(conv(nx, ny))

        doit(num + 1)

        for dir in range(4):
            nx = x + dx[dir]
            ny = y + dy[dir]

            if isOutrange(nx, ny):
                continue
            lef.pop()

        vis[next_val] = False
        v.pop()

for i in range(1, n+1):
    board[i] = [0] + list(map(int, input().split()))

for i in range(1, n+1):
    for j in range(1, m+1):
        num = conv(i, j)
        vis[num] = True
        v.append(num)

        lef.clear()
        for dir in range(4):
            nx = i + dx[dir]
            ny = j + dy[dir]

            if isOutrange(nx, ny):
                continue
            lef.append(conv(nx, ny))

        doit(1)

        v.pop()
        vis[num] = False

for i in range(len(ve)):
    for j in range(i+1, len(ve)):
        dup = len(set(ve[i]) & set(ve[j]))

        if dup != 2:
            continue
        val = 0
        for ii in range(5):
            num = ve[i][ii]
            cur = reconv(num)
            x, y = cur
            val += board[x][y]

        for jj in range(5):
            num = ve[j][jj]
            cur = reconv(num)
            x, y = cur
            val += board[x][y]

        ans = max(ans, val)

print(ans)