def solution(commands):
    answer = []
    cell = [['EMPTY' for _ in range(51)] for _ in range(51)]
    pointer = [[[i, j] for j in range(51)] for i in range(51)]
    
    def insert(r, c, value):
        rr, cc = pointer[r][c]
        cell[rr][cc] = value
        
    def update(value1, value2):
        for i in range(51):
            for j in range(51):
                if cell[i][j] == value1:
                    cell[i][j] = value2
                    
    def merge(r1, c1, r2, c2):
        rr1, cc1 = pointer[r1][c1]
        rr2, cc2 = pointer[r2][c2]
        value1, value2 = [cell[rr1][cc1], cell[rr2][cc2]]
        cell[rr1][cc1] = value2 if value1 == 'EMPTY' else value1
        
        for i in range(51):
            for j in range(51):
                if pointer[i][j] == [rr2, cc2]:
                    pointer[i][j] = [rr1, cc1]
                    
    def unmerge(r, c):
        rr, cc = pointer[r][c]
        value = cell[rr][cc]
        for i in range(51):
            for j in range(51):
                if pointer[i][j] == [rr, cc]:
                    pointer[i][j] = [i, j]
                    cell[i][j] = 'EMPTY'
        cell[r][c] = value
        
    def pprint(r, c):
        rr, cc = pointer[r][c]
        answer.append(cell[rr][cc])
        
    for i in commands:
        a = i.split(' ')
        if a[0] == 'UPDATE':
            if len(a) == 4:
                insert(int(a[1]), int(a[2]), a[3])
            else:
                update(a[1], a[2])
        elif a[0] == 'MERGE':
            merge(int(a[1]), int(a[2]), int(a[3]), int(a[4]))
        elif a[0] == 'UNMERGE':
            unmerge(int(a[1]), int(a[2]))
        else:
            pprint(int(a[1]), int(a[2]))
                
    return answer