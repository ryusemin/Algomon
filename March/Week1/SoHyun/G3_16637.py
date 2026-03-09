import sys
input = sys.stdin.readline

n = int(input())
cal = list(input().strip())

result = -2**31

def calculate(a, op, b):
    if op == '+':
        return a + b
    elif op == '-':
        return a - b
    else:
        return a * b

def dfs(index, value):
    global result
    
    if index >= n - 1:
        result = max(result, value)
        return

    next_val = calculate(value, cal[index+1], int(cal[index+2]))
    dfs(index + 2, next_val)

    if index + 4 < n:
        bracket = calculate(int(cal[index+2]), cal[index+3], int(cal[index+4]))
        next_val = calculate(value, cal[index+1], bracket)
        dfs(index + 4, next_val)

dfs(0, int(cal[0]))
print(result)