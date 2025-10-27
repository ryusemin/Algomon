n = int(input())
nlst = list(map(int, input().split()))
nlst.sort()

m = int(input())
mlst = list(map(int, input().split()))

for i in mlst:
    left = 0
    right = n-1
    
    while left <= right:
        mid = (left + right) // 2
        if i > nlst[mid]:
            left = mid + 1
        elif i < nlst[mid]:
            right = mid - 1
        else:
            left = mid
            right = mid
            break
            
    if left == mid and right == mid:
        print(1)
    else:
        print(0)