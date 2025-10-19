answer = 0

def dfs(k, cnt, dungeons, v):
    global answer
    if cnt > answer:
        answer = cnt
        
    for i in range(len(dungeons)):
        if not v[i] and k >= dungeons[i][0]:
            v[i] = 1
            dfs(k-dungeons[i][1], cnt+1, dungeons, v)
            v[i] = 0

def solution(k, dungeons):
    global answer
    v = [0] * len(dungeons)
    dfs(k, 0, dungeons, v)
    return answer