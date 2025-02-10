#include <stdio.h>
#include <unistd.h>

int main() {
    // Identifiant du processus
    pid_t pid;

    // Variables à échanger entre le parent et l'enfant
    int variable1 = 10;
    int variable2 = 20;

    // Création d'un nouveau processus
    pid = fork();

    // Vérification si la création du processus a échoué
    if (pid < 0) {
        fprintf(stderr, "La création du processus a échoué\n");
        return 1;
    }

    // Code exécuté par le processus parent
    if (pid > 0) {
        printf("Je suis le processus parent. Mon PID est : %d\n", getpid());
        printf("Avant l'échange : variable1 = %d, variable2 = %d\n", variable1, variable2);
    }
    // Code exécuté par le processus enfant
    else {
        printf("Je suis le processus enfant. Mon PID est : %d\n", getpid());
        
        // Échange des valeurs des variables
        int temp = variable1;
        variable1 = variable2;
        variable2 = temp;

        printf("Après l'échange : variable1 = %d, variable2 = %d\n", variable1, variable2);
    }

    // Code exécuté par les deux processus
    if (pid > 0) {
        // Addition des valeurs des variables par le processus parent
        int sum = variable1 + variable2;
        printf("Le processus parent effectue l'addition : variable1 + variable2 = %d\n", sum);
    }

    return 0;
}
