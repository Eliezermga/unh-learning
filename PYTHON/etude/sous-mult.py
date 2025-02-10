def p9(n):
    s = list()
    x=n
    while x > 0:
        if n % x ==0:
            s.append(x)
        x=x-1
    print(s)
    return s
s1=p9(7)
s1=p9(15)
s3=p9(21)
s4=p9(12)