M=[[1,2,3,4],[7,9,10,11],[4,12,10,3],[0,1,5,7]]
TD=[]
def diagonale(a):
    ligne = len(a)
    col = len(a[0])
    for i in range(0, ligne):
        for j in range(0, col):
            if i == j:
                TD.append(M[i][j])
    print(TD)
    return TD
diagonale(M)