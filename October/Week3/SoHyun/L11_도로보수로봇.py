def is_possible(l, x, n, k):
    cnt, last_x = 1, x[0]
    for i in range(1, n):
        if x[i] - last_x + 1 <= l:
            continue
        cnt += 1
        last_x = x[i]
    return cnt <= k

def search(s, e, x, n, k):
    if s > e:
        return x[-1] - x[0] + 1
    m = (s + e) // 2

    if is_possible(m, x, n, k):
        return min(m, search(s, m-1, x, n, k))
    else:
        return search(m+1, e, x, n, k)

n , k = map(int, input().split())
x = list(map(int, input().split()))
x.sort()

print(search(1, x[-1] - x[0] + 1, x, n, k))