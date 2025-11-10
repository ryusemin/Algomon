n, m = map(int, input().split())
box = [0] * n
for _ in range(m):
    i, j, k = map(int, input().split())
    
    for b in range(i-1, j, 1):
        box[b] = k
        
print(*box)