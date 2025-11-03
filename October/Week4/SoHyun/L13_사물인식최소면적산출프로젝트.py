N, K = map(int, input().split())
res = 1e9
v = [[] for _ in range(22)]

def func(depth, maxY, maxX, minY, minX):
    global res
    if depth > K:
        res = min(res, abs(maxX - minX) * abs(maxY - minY))
        return

    for x, y in v[depth]:
        newMaxX = max(maxX, x)
        newMaxY = max(maxY, y)
        newMinX = min(minX, x)
        newMinY = min(minY, y)

        tmp = abs(newMaxX - newMinX) * abs(newMaxY - newMinY)
        if tmp < res or depth == 1:
            func(depth + 1, newMaxY, newMaxX, newMinY, newMinX)

for _ in range(N):
    x, y, k = map(int, input().split())
    v[k].append((x, y))

func(1, -1001, -1001, 1001, 1001)
print(res)