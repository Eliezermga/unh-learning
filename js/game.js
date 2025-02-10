// Création d'un canvas pour le jeu
const canvas = document.createElement('canvas');
const ctx = canvas.getContext('2d');
canvas.width = 800;
canvas.height = 600;
document.body.appendChild(canvas);

// Création des variables pour les joueurs et les obstacles
let player1 = {x: 50, y: 300, width: 30, height: 20, color: 'red', speed: 5};
let player2 = {x: 50, y: 350, width: 30, height: 20, color: 'blue', speed: 5};
let obstacles = [];
let keys = {};

// Fonction pour dessiner les joueurs et les obstacles
function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = player1.color;
    ctx.fillRect(player1.x, player1.y, player1.width, player1.height);
    ctx.fillStyle = player2.color;
    ctx.fillRect(player2.x, player2.y, player2.width, player2.height);
    for (let i = 0; i < obstacles.length; i++) {
        let obstacle = obstacles[i];
        ctx.fillStyle = obstacle.color;
        ctx.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
    }
}

// Fonction pour mettre à jour les positions des joueurs et des obstacles
function update() {
    if (keys['ArrowUp']) {
        player1.y -= player1.speed;
    }
    if (keys['ArrowDown']) {
        player1.y += player1.speed;
    }
    if (keys['ArrowDown']) {
        player2.y -= player2.speed;
    }
    if (keys['ArrowUp']) {
        player2.y += player2.speed;
    }
    for (let i = 0; i < obstacles.length; i++) {
        let obstacle = obstacles[i];
        obstacle.x -= 5;
        if (obstacle.x < -obstacle.width) {
            obstacles.splice(i, 1);
            i--;
        }
    }
}

// Fonction pour ajouter un nouvel obstacle
function addObstacle() {
    let height = Math.random() * 200 + 100;
    let y = Math.random() * (canvas.height - height);
    let color = '#' + Math.floor(Math.random()*16777215).toString(16);
    let obstacle = {x: canvas.width, y: y, width: 50, height: height, color: color};
    obstacles.push(obstacle);
}

// Boucle de jeu
setInterval(function() {
    update();
    draw();
}, 1000/60);

// Ajout d'un nouvel obstacle toutes les deux secondes
setInterval(addObstacle, 2000);

// Gestion des événements clavier
document.addEventListener('keydown', function(e) {
    keys[e.code] = true;
});
document.addEventListener('keyup', function(e) {
    keys[e.code] = false;
});