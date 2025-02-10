a=input('entrer un mot:')
def detect(a):
    nouv=""
    maj=''
    taille=len(a)
    for i in range (taille):
        if ord(a[i])>=65 and ord(a[i]) <= 90 :
            maj+=(a[i])
            maj+=" "
            x=ord(a[i])
            y=x+32
            n=chr(y)
            nouv+=n
        else:
            nouv+=a[i]
    print(maj)
    print(nouv)
    return nouv
detect(a)