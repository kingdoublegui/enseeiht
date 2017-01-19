var globalTimeout, timerInterval;
var answerTimeout = 1500; //temps de reaction laisser au joueur en millisecondes
var tiles = [];
var scale = [
	{note: 'C', octave: 5},
	{note: 'D', octave: 5},
	{note: 'E', octave: 5},
	{note: 'F', octave: 5},
	{note: 'G', octave: 5},
	{note: 'A', octave: 5},
	{note: 'B', octave: 5},
	{note: 'C', octave: 6},
	{note: 'D', octave: 6}
];
var user = {};

window.onload = function() {
	checkLogin();
};

var sequenceSocket = new WebSocket("ws://localhost:8080/Decubx/actions");
sequenceSocket.onmessage = onSequenceMessage;
var chatSocket = new WebSocket("ws://localhost:8080/Decubx/chatws");
chatSocket.onmessage = onChatMessage;

function createTiles(size){
	document.getElementById("game-board").innerHTML = "";
	tiles = [];
	for(var i = 0; i < Math.pow(size, 2); i++){
		var node = document.createElement("div");
		node.classList.add("tile");
		node.id = "tile" + i;
		tiles.push({id: i, node: node});
		document.getElementById("game-board").appendChild(node);
	}
}

function signup(){
	showLoginMessage();
	document.getElementById("overlay").classList.add("toCircle");
	var params = {
		username: document.getElementById("connexion-username").value,
		password: document.getElementById("connexion-password").value
	};
	httpRequest(
		{
			url: "users?action=add",
			type: "POST",
			params: params
		},
		function(res){
			document.getElementById("overlay").classList.remove("toCircle");
			if(res.status == 200 && res.responseText == "ok"){
				showLoginMessage("sign-up-successful");
			}else{
				showLoginMessage("sign-up-error");
			}
		}
	);
}

function getUserInfo(callback){
	httpRequest(
		{
			url: "users?action=info",
			type: "POST"
		},
		function(res){
			if(res.status == 200 && Object.keys(JSON.parse(res.responseText)).length != 0){
				user = JSON.parse(res.responseText);
				if(user.roles.includes('premium')){
					document.getElementById("#main").classList.add("premium");
				}
			}
			if(callback){
				callback();
			}
		}
	);
}

function checkLogin(){
	document.getElementById("overlay").classList.add("toCircle");
	getUserInfo(function(res){
		document.getElementById("overlay").classList.remove("toCircle");
		showOverlayPanel("game-mode-panel");
	});
}

function login(){
	showLoginMessage();
	document.getElementById("overlay").classList.add("toCircle");
	var params = {
			j_username: document.getElementById("connexion-username").value,
			j_password: document.getElementById("connexion-password").value
	};
	httpRequest(
		{
			url: "j_security_check",
			type: "POST",
			params: params
		},
		function(res){
			if(res.status == 200 && res.responseText == ""){
				getUserInfo();
			}else{
				document.getElementById("overlay").classList.remove("toCircle");
				showLoginMessage("login-error");
			}
		}
	);
}

function showLoginMessage(id){
	for(var c of document.getElementById("login-info").children){
		if(c.id == id){
			c.classList.remove("hidden");
		}else{
			c.classList.add("hidden");
		}
	}
}

function showOverlayPanel(id){
	for(var c of document.getElementById("overlay").children){
		if(c.id == id){
			c.classList.remove("hidden");
		}else{
			c.classList.add("hidden");
		}
	}
}

function newGame(){
	document.getElementById("overlay-back").classList.add("hidden");
	document.getElementById("score").innerHTML = 0;
	document.getElementById("score-gameOver").innerHTML = 0;
	createTiles(2);
	var SequenceAction = {
			action: "sequence"
		};
	sequenceSocket.send(JSON.stringify(SequenceAction));
}

function switchChat(){
	document.getElementById("main").classList.toggle("chat-opened");
}

function playSequence(sequence){
	setTimeout(displaySequence, 500, sequence);
	setTimeout(getUserClick, 500+answerTimeout*sequence.length, sequence, 0);
}

function displaySequence(sequence){	for(var s = 0; s < sequence.length; s++){
		setTimeout(
			function(tile){
				tile.node.classList.add("tile-active");
				Synth.play('piano', scale[tile.id].note, scale[tile.id].octave, 0.8 * answerTimeout / 1000);
			},
			s * answerTimeout,
			tileById(sequence[s])
		);
		setTimeout(
			function(tile){
				tile.node.classList.remove("tile-active");
			},
			(s + 0.8) * answerTimeout,
			tileById(sequence[s])
		);
	}
}

function getUserClick(sequence, index){
	for(var t of tiles){
		t.node.classList.add("tile-clickable");
		if(t.id == sequence[index]){
			t.node.addEventListener("click", rightTileListener);
		}else{
			t.node.addEventListener("click", wrongTileListener);
		}
	}
	globalTimeout =	setTimeout(gameOver, answerTimeout);
	clearInterval(timerInterval);
	runTimer();

	function rightTileListener(){
		clearTiles();
		var tile = tileById(sequence[index]);
		Synth.play('piano', scale[tile.id].note, scale[tile.id].octave, 0.5 * answerTimeout / 1000);
		if(index+1 < sequence.length){
			getUserClick(sequence, index+1);	// On passe à l'élément suivant
		}else{
			document.getElementById("score").innerHTML = index + 1;
			document.getElementById("score-gameOver").innerHTML = index + 1;
			var SequenceAction = {
			        action: "sequence"
			    };
			sequenceSocket.send(JSON.stringify(SequenceAction));
		}
	}

	function wrongTileListener(){
		clearTiles();
		gameOver();
	}

	function gameOver(){
		clearTiles();
		var SequenceAction = {
		        action: "gameover"
		    };
		sequenceSocket.send(JSON.stringify(SequenceAction));
		document.getElementById("overlay-back").classList.remove("hidden");
		showOverlayPanel("gameOver-panel");
	}

	function clearTiles(){
		clearTimeout(globalTimeout);
		clearInterval(timerInterval);
		document.getElementById("timeBar").style.width = 0;
		for(let t of tiles){
			t.node.classList.remove("tile-clickable");
			t.node.removeEventListener("click", rightTileListener);
			t.node.removeEventListener("click", wrongTileListener);
		}
	}
}

function onSequenceMessage(event) {
	var sequence = JSON.parse(event.data);
    if (sequence.action === "sequence") {
    	playSequence(sequence.cases);
    }
}

function onChatMessage(event) {
	var msg = JSON.parse(event.data);
	var node = document.createElement("div");
	var pseudo = document.createElement("span");
	pseudo.classList.add("pseudo");
	pseudo.innerHTML = msg.pseudo + " : ";
	node.appendChild(pseudo);
	node.innerHTML = node.innerHTML + msg.msg;
    document.getElementById("chat").appendChild(node);
}

function sendMessage(){
	var input = document.getElementById("chat-input");
	if(input.value != ""){
		var message = {
			pseudo: user.username,
			msg: input.value
		}
		chatSocket.send(JSON.stringify(message));
		input.value = "";

	}
}

function runTimer(){
    var elem = document.getElementById("timeBar");
    var width = 100;
    timerInterval = setInterval(
    	function(){
		    if (width <= 0) {
		        clearInterval(timerInterval);
		    } else {
		        width--;
		        elem.style.width = width + '%';
		    }
		},
		answerTimeout/100
	);
}

function tileById(id){
    for(var t of tiles){
        if(t.id === id){
            return t;
        }
    }
}

function httpRequest(req, callback){
	var data = null;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4 && callback){
            callback(xmlHttp);
		}
    }
	xmlHttp.open(req.type, req.url, true);
	if(req.type === "POST"){
		// Params parsing
		data = [];
		if(req.params){
			for(var p in req.params){
				data.push(p + "=" + req.params[p])
			}
			data = data.join("&");
		}
		// Headers
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	}
    xmlHttp.send(data);
}

// Tests
//var sequence = [1,1,2,3,0];
//var sequence = [0,0,0,1,2,1,0,2,1,1,0];
//setTimeout(createTiles, 500, 2);
//setTimeout(displaySequence, 2000, sequence);
//setTimeout(getUserClick, 2000+answerTimeout*sequence.length, sequence, 0);
