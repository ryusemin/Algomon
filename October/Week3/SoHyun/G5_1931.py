n = int(input())

lst = []
for _ in range(n):
    s, e = map(int, input().split())
    lst.append((s, e))

lst.sort(key = lambda x : (x[1], x[0]))
    
cnt = 1
e = lst[0][1]
for i in range(1, n):
    if lst[i][0] >= e:
        e = lst[i][1]
        cnt += 1
        
print(cnt)