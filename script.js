    function salutation(){
        var horaire = new Date()
        var heures= horaire.getHours()
        var salut = "salut à, le veilleur de nuit!"
        if(heures<5){salut="bonne matiné"}
        if(heures<16){salut="bonsoir"}
        if(heures<22){salut="bonne nuit"}
        document.whrite
    }

    
    function charge(){
        alert("mon charegement en text")
    }
    /* la fonction retourne */
    function total(chiffre1,chiffre2){
        addition = chiffre1 + chiffre2;
        return addition;
    }
    var chiffre1 = 10
    var chiffre2 = 11
    alert("le resultat de l'addition est: "+total(chiffre1,chiffre2))
    /*procedure*/
    function bonjour(){
        alert("bonjour")
    }
    var m=prompt("quelle valeur voulez vous prendre pour faire un test avec l'operateur conditionnel ternaire");
    (m<2)? alert("la valeur est inferieur 2"): alert("la valeur est superieur à 2")
        nom = prompt("veuillez saisir votre nom!","");
        alert("hereux de faire votre connaissance, "+nom+" !");
        x=5
        alert(++x)
        /*incrementation de 1 à la valeur */

        var ok = 'bonjour',name=' à toi';
        resultat = ok + name
        alert(resultat);
        /*la concatenation se fait avec le signe + */

        suit = confirm("voulez vous poursuivre ?");
            if (suit==true){
                alert(" j'en suis hereux");
            }
            else{
                alert("c'est dommage") 
            }
        
        reponse=prompt("Javascript est un languange :A. Non typé B faiblement typé","saisissez ici la lettre correspondant à votre réposnse");
        switch(reponse){
            case"A":
                alert("pas tout-à-fait,ré-essayez");
                break;
            case"B":
                alert("cela n'est pas exact, ré-ssayez");
                break;
        }
        /* les boucles*/
        for(var iter = 1; iter<6; iter++){
            alert('Itération n°' +iter);
        }
        var iter = 1
        while(iter<6){
            alert('Iteration n°')
            iter ++
        }
        for(j=0; j<5; j++){
            if(j==3){
                continue;
            }
            alert("La valeur de j est égale à :"+j);
        }