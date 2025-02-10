const http = require('http');
const fs = require('fs');
const port = 5000;

const server = http.createServer(function(req, res){
    res.writeHead(200, {'content-Type': 'text/html'});
    fs.readFile('index.html', function(error, data){
        if (error){
            res.writeHead(404);
            res.write('Ereur: Fichier inexistant')
        }else{
            res.write(data)
        }
        res.end();
    })
})
server.listen(port, function(error){
    if (error) {
        console.log('le serveur ne parvient pas à demarer', error);
    } else{
        console.log('le serveur a été listé au port '+port);
    }
});