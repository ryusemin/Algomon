n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
clst1 = [(n-1, 0), (n-1, 1), (n-2, 0), (n-2, 1)] 

di, dj = [0, 0, -1, -1, -1, 0, 1, 1, 1], [0, -1, -1, 0, 1, 1, 1, 0, -1]
for _ in range(m):
    d, s = map(int, input().split())
    clst2 = []              
    for ci, cj in clst1:
        ni, nj = (ci + di[d] * s + n) % n, (cj + dj[d] * s + n) % n
        arr[ni][nj] += 1      
        clst2.append((ni, nj))
         
    v = [[0]*n for _ in range(n)]
    for ci, cj in clst2:
        v[ci][cj] = 1     
        for dii, djj in ((-1, -1), (-1, 1), (1, -1), (1, 1)):
            ni, nj = ci + dii, cj + djj
            if 0 <= ni < n and 0 <= nj < n and arr[ni][nj] > 0:
                arr[ci][cj] += 1
      
    clst1 = []
    for i in range(n):
        for j in range(n):
            if arr[i][j] >= 2 and v[i][j] == 0:
                arr[i][j] -= 2
                clst1.append((i, j))
                    
ans = 0
for lst in arr:
    ans += sum(lst)
print(ans)            