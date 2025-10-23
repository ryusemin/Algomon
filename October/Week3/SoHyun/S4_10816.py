n = int(input())
nlst = list(map(int, input().split()))
nlst.sort()

m = int(input())
mlst = list(map(int, input().split()))

dic = {}
for n in nlst:
    if n in dic:
        dic[n] += 1
    else:
        dic[n] = 1
        
def binary(m, nlst, start, end):
    if start > end:
        return 0
    
    mid = (start + end) // 2
    if m == nlst[mid]:
        return dic[m]
    elif m < nlst[mid]:
        return binary(m, nlst, start, mid-1)
    else:
        return binary(m, nlst, mid+1, end)

for m in mlst:
    print(binary(m, nlst, 0, len(nlst)-1), end = ' ')