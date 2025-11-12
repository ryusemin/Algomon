import sys
import heapq

n = int(sys.stdin.readline())
max_heap = []

for _ in range(n):
    num = int(sys.stdin.readline())
    
    if num > 0:
        heapq.heappush(max_heap, -num)
        
    else:
        if not max_heap:
            print(0)
        else:
            print(-heapq.heappop(max_heap))