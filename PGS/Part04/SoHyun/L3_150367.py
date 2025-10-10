def search(number):
    l = len(number)
    if l == 1 or '1' not in number or '0' not in number:
        return True
    
    mid = l // 2
    if number[mid] == '0':
        return False
    
    return search(number[:mid]) and search(number[mid+1:])

def solution(numbers):
    bin_numbers = [bin(x)[2:] for x in numbers]
    bin_lst = [2**x - 1 for x in range(50)]
    answer = list()
    
    for num in bin_numbers:
        l = len(num)
        for n in bin_lst:
            if n >= l:
                num = '0' * (n-l) + num
                break
        answer.append(1 if search(num) else 0)
        
    return answer