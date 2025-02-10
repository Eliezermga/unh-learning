import numpy as np
def move (p0,pf, a) :
    p0 = np.array(p0)
    pf = np.array(pf)
    return p0 + a * (pf - p0) / np.linalg.norm(pf-p0)

def planification (p0,pf,a) :
    pos = [p0]
    pt = p0[:]
    pf = np.array(pf)
    while (np.linalg.norm(pt - pf) >= 0.7) :
        pt = move(pt,pf,a)
        pos.append(pt)
    return np.array(pos)
if __name__ == "__main__":
    p0 = [0,1]
    pf = [5,5]
    a = 0.5
    pos = planification(p0, pf, a)
    print(pos)