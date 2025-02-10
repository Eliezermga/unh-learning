m1=[[1,2,3],[7,9,10],[4,12,10]]
m2=[[9,5,2],[7,3,1],[17,24,6]]
def multiply(m1, m2):
  m = []
  if len(m1[0]) != len(m2):
    print ("impossible de multiplier ces deux matrices")
    return False
  for i in range(len(m1)):
    ligne = []
    for j in range(len(m2[0])):
      element = 0
      for k in range(len(m1[0])):
        element = element + m1[i][k] * m2[k][j]
      ligne.append(element)
    m.append(ligne)
  print("M3=",m)
  return m
multiply(m1,m2)