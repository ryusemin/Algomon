import heapq

t = int(input())

for i in range(t):
    k = int(input())
    q1, q2 = [], []
    v = [0] * k
    
    for j in range(k):
        com, num = input().split()
        
        if com == 'I':
            heapq.heappush(q1, (int(num), j))
            heapq.heappush(q2, (-int(num), j))
            v[j] = 1
            
        else:
            if num == '1':
                while q2 and not v[q2[0][1]]:
                    heapq.heappop(q2)
                if q2:
                    v[q2[0][1]] = 0
                    heapq.heappop(q2)
                    
            else:
                while q1 and not v[q1[0][1]]:
                    heapq.heappop(q1)
                if q1:
                    v[q1[0][1]] = 0
                    heapq.heappop(q1)
                    
    while q1 and not v[q1[0][1]]:
        heapq.heappop(q1)
    while q2 and not v[q2[0][1]]:
        heapq.heappop(q2)
        
    if not q1 or not q2:
        print("EMPTY")
    else:
        a = -q2[0][0]
        b = q1[0][0]
        print("%d %d" % (a, b))