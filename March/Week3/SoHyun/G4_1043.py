import sys
input = sys.stdin.readline

n, m = map(int, input().split())
know = set(input().split()[1:])
parties = []

for _ in range(m):
    parties.append(set(input().split()[1:]))
    
for _ in range(m):
    for party in parties:
        if party & know:
            know  = know.union(party)
            
cnt = 0
for party in parties:
    if party & know:
        continue
    cnt += 1
    
print(cnt)