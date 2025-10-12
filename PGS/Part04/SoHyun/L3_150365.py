def dist(x, y, r, c):
    return abs(x - r) + abs(y - c)

def solution(n, m, x, y, r, c, k):
    if (k - dist(x, y, r, c)) % 2 or k < dist(x, y, r, c):
        return "impossible"
    answer = ""
    move = 0
    
    while x < n and (k - move) > dist(x, y, r, c):
        move += 1
        x += 1
        answer += "d"
        
    while 1 < y and (k - move) > dist(x, y, r, c):
        move += 1
        y -= 1
        answer += "l"
    
    while (k - move) > dist(x, y, r, c):
        move += 2
        answer += "rl"
        
    if x < r:
        answer += "d" * (r - x)
        x = r
    if y > c:
        answer += "l" * (y - c)
        y = c
    if y < c:
        answer += "r" * (c - y)
        y = c
    if x > r:
        answer += "u" * (x - r)
        x = r
    
    return answer