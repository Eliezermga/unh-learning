def transpose(m):
    l=len(m)
    c=len(m[0])
    res =[[0] * c for i in range(l)]
    for i in range(l):
        for j in range(c):
            res[j][i]=m[i][j]
    for r in res:
        print(r)
transpose([[1,5,2,3],[7,9,8,2],[4,6,1,5],[4,6,1,7]])