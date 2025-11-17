import heapq

n, k = map(int, input().split())
jew = []
for _ in range(n):
    heapq.heappush(jew, list(map(int, input().split())))

bags = []
for _ in range(k):
    bags.append(int(input()))
bags.sort()

answer = 0
tmp_jew = []

for b in bags:
    while jew and b >= jew[0][0]:
        heapq.heappush(tmp_jew, -heapq.heappop(jew)[1])
    if tmp_jew:
        answer -= heapq.heappop(tmp_jew)
    elif not jew:
        break
    
print(answer)