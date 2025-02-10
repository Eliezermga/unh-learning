def diviseur(n):
    s=0
    for x in range(n,0,-1):
        if n % x ==0:
            s+=1
    if s == 2:
        print(True)
        return True
    else:
        print(False)
        return False
diviseur(21)