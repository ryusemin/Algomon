def dfs(ycnt):
    global candidate
    
    if ycnt >= 4:
        return

    if len(candidate) == 7:
        members.add(tuple(sorted(candidate)))
        return
    
    if tuple(sorted(candidate)) in v:
        return
    
    for x, y in candidate:
        for k in range(4):
            ni = x + di[k]
            nj = y + dj[k]
            
            if 0 <= ni < 5 and 0 <= nj < 5 and (ni, nj) not in candidate:
                newY = ycnt
                if students[ni][nj] == 'Y':
                    newY += 1
                candidate.append((ni, nj))
                dfs(newY)
                candidate.pop()
                
    v.add(tuple(sorted(candidate)))
    

students = [input() for _ in range(5)]
di = [0, 1, 0, -1]
dj = [1, 0, -1, 0]

members = set()
v = set()

for i in range(5):
    for j in range(5):
        if students[i][j] == 'S':
            candidate = [(i, j)]
            dfs(0)
            
print(len(members))