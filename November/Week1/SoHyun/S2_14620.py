def check(x, y, v):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if (nx, ny) in v:
            return False
    return True

def dfs(v, total):
    global answer
    
    if total >= answer:
        return
    
    if len(v) == 15:
        answer = min(answer, total)
    else:
        for i in range(1, n-1):
            for j in range(1, n-1):
                if check(i, j, v) and (i, j) not in v:
                    temp = [(i, j)]
                    temp_total = maps[i][j]
                    
                    for d in range(4):
                        nx = i + dx[d]
                        ny = j + dy[d]
                        
                        temp_total += maps[nx][ny]
                        temp.append((nx, ny))
                    dfs(v + temp, total + temp_total)

n = int(input())

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

result = []
maps = []
answer = int(1e9)

for _ in range(n):
    maps.append(list(map(int, input().split())))
    
dfs([], 0)

print(answer)