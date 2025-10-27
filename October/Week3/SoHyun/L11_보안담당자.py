n = int(input())
s = input()

out = s.count(')')
need = n // 2 - out
check = 0

if n % 2 == 1:
    print("No")
    exit()

for i in range(n-1, -1, -1):
    if s[i] == ')':
        check -= 1
    elif s[i] == '(':
        check += 1
    else:
        if need > 0:
            check -= 1
            need -= 1
        else:
            check += 1
    
    if check > 0:
        print("No")
        exit()

if check == 0:
    print("Yes")
else:
    print("No")