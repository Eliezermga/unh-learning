//Veuillez comprendre l’exécution du programme C (process1.c) sur la création 
//des processus et étendre ce programme en un programme process2.c 
//de la façon suivante :

//Le processus parent initialise deux variables (variable1=10 ; variable2=20), 
//le processus enfant échange ces variables et les imprime, et le processus parent 
//effectue l'addition des valeurs des variables et imprime le résultat.


#include <stdio.h>
#include <unistd.h>
int main() {
    // Identifiant du processus
    pid_t pid;
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
    }
    // Code exécuté par le processus enfant
    else {
        printf("Je suis le processus enfant. Mon PID est : %d\n", getpid());
    }
    // Code exécuté par les deux processus
    printf("Ce code est exécuté par les deux processus (parent et enfant).\n");
    return 0;
}
