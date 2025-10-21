n = int(input())

plus = []
minus = []
total = 0

for i in range(n):
    num = int(input())
    if num > 1:
        plus.append(num)
    elif num <= 0:
        minus.append(num)
    else:
        total += num
    
plus.sort(reverse = True)
minus.sort()

for i in range(0, len(plus), 2):
    if i+1 >= len(plus):
        total += plus[i]
    else:
        total += (plus[i] * plus[i+1])
        
for i in range(0, len(minus), 2):
    if i+1 >= len(minus):
        total += minus[i]
    else:
        total += (minus[i] * minus[i+1])

print(total)