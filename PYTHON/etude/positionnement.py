def position(a,b):
    ligne=len(b)
    col=len(b[0])
    for i in range(0,ligne):
        for j in range(0,col):
            if a==b[i][j]:
                return i,j
position(4,[[1,2,3,4],[5,6,7,8],[9,5,6,8],[6,7,9,0]])