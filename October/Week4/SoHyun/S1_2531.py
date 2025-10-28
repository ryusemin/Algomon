n, d, k, c = map(int, input().split())
lst = [int(input()) for _ in range(n)]
seq = lst[:k]
result = 0

for i in range(n):
    seq.pop(0)
    seq.append(lst[(i+k) % n])
    result = max(result, len(set(seq + [c])))
    
    if result == d:
        break

print(result)