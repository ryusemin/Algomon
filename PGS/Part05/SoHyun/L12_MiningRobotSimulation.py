n, t = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

dp = [[-10**15] * n for _ in range(n)]
dp[0][0] = grid[0][0]

for i in range(n):
    for j in range(n):
        if i > 0:
            dp[i][j] = max(dp[i][j], dp[i-1][j] + grid[i][j])
        if j > 0:
            dp[i][j] = max(dp[i][j], dp[i][j-1] + grid[i][j])

dp2 = [[-10**15] * n for _ in range(n)]
dp2[-1][-1] = grid[-1][-1]

for i in range(n-1, -1, -1):
    for j in range(n-1, -1, -1):
        if i < n-1:
            dp2[i][j] = max(dp2[i][j], dp2[i+1][j] + grid[i][j])
        if j < n-1:
            dp2[i][j] = max(dp2[i][j], dp2[i][j+1] + grid[i][j])

arr = [[[-10**15] * (t+1) for _ in range(n)] for _ in range(n)]
for i in range(n):
    for j in range(n):
        arr[i][j][0] = grid[i][j]

for d in range(1, t+1):
    for i in range(n-1, -1, -1):
        for j in range(n-1, -1, -1):
            best = -10**15
            if i < n-1:
                best = max(best, arr[i+1][j][d-1])
            if j < n-1:
                best = max(best, arr[i][j+1][d-1]) 
            if best > -10**15 // 2:
                arr[i][j][d] = best + grid[i][j]

answer = -10**15

for i in range(n):
    for j in range(n):
        result = dp[i][j] + dp2[i][j] - grid[i][j]
        if arr[i][j][t] > -10**15 // 2:
            result += arr[i][j][t]
        answer = max(answer, result)

print(answer)