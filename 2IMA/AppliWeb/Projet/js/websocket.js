window.onload = init;
var socket = new WebSocket("ws://localhost:8080/Decubx/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var joueur = JSON.parse(event.data);
    if (joueur.action === "add") {
       ;
    }
    if (joueur.action === "remove") {
        document.getElementById(joueur.id).remove();
    }
}

function addJoueur(pseudo, mail) {
    var JoueurAction = {
        action: "add",
        pseudo: pseudo,
        mail: mail
    };
    socket.send(JSON.stringify(JoueurAction));
}

function removeJoueur(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(JoueurAction));
}

function init() {
    ;
}