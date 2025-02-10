#include <stdio.h>

int main() {
  char nom[21];

  printf("Entrez votre nom: ");
  scanf("%s", nom);

  printf("Bienvenue, %s\n", nom);

  return 0;
}
