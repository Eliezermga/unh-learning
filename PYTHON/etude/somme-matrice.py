m1=[[1,2,3,4],[7,9,10,11],[4,12,10,3],[0,1,5,7]]
m2=[[9,5,2,1],[7,3,1,9],[17,24,6,31],[1,8,9,6]]
def addmatrice(m1,m2):
    if len(m1) == len(m2):
        l = len(m1)
        c = len(m1[0])
        m3 = [[0] * c for i in range(l)]
        for i in range(l):
            for j in range(c):
                m3[i][j] = m1[i][j] + m2[i][j]
        print("La somme de m1 et m2 est : ", m3)
    else:
        print("imposible d'additionner ces deux matrices")
addmatrice(m1,m2)