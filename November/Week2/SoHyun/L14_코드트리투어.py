import sys
import heapq

INF = float('inf')
MAX_N = 2000
MAX_ID = 30005

input = sys.stdin.readline

N, M = 0, 0
A = []
D = []
isMade = []
isCancel = []
S = 0

class Package:
    def __init__(self, id, revenue, dest, profit):
        self.id = id
        self.revenue = revenue
        self.dest = dest
        self.profit = profit

    def __lt__(self, other):
        if self.profit == other.profit:
            return self.id < other.id
        return self.profit > other.profit

pq = []

def dijkstra():
    global D
    D = [INF] * N
    visit = [False] * N
    D[S] = 0

    for _ in range(N):
        v = -1
        minDist = INF
        for j in range(N):
            if not visit[j] and minDist > D[j]:
                v = j
                minDist = D[j]

        if v == -1:
            break

        visit[v] = True

        for j in range(N):
            if A[v][j] != INF and D[j] > D[v] + A[v][j]:
                D[j] = D[v] + A[v][j]

def buildLand(n_param, m_param, arr):
    global A, N, M
    N, M = n_param, m_param
    A = [[INF] * N for _ in range(N)]
    for i in range(N):
        A[i][i] = 0

    for i in range(M):
        u, v, w = arr[i*3], arr[i*3+1], arr[i*3+2]

        A[u][v] = min(A[u][v], w)
        A[v][u] = min(A[v][u], w)

def addPackage(id, revenue, dest):
    global isMade, pq
    isMade[id] = True

    profit = revenue - D[dest]

    heapq.heappush(pq, Package(id, revenue, dest, profit))

def cancelPackage(id):
    global isCancel
    
    if isMade[id]:
        isCancel[id] = True

def sellPackage():
    global pq, isCancel
    
    while pq:
        p = pq[0]

        if p.profit < 0:
            break

        heapq.heappop(pq)

        if not isCancel[p.id]:
            isCancel[p.id] = True
            return p.id

    return -1

def changeStart(param):
    global S, pq
    S = param
    dijkstra()

    temp_packages = []

    while pq:
        temp_packages.append(heapq.heappop(pq))

    for p in temp_packages:
        p.profit = p.revenue - D[p.dest]
        heapq.heappush(pq, p)

Q = int(input())

isMade = [False] * MAX_ID
isCancel = [False] * MAX_ID

for _ in range(Q):
    query = list(map(int, input().split()))
    T = query[0]

    if T == 100:
        buildLand(query[1], query[2], query[3:])
        dijkstra()
    elif T == 200:
        id, revenue, dest = query[1], query[2], query[3]
        addPackage(id, revenue, dest)
    elif T == 300:
        id = query[1]
        cancelPackage(id)
    elif T == 400:
        print(sellPackage())
    elif T == 500:
        changeStart(query[1])