var globalTimeout, timerInterval;
var answerTimeout = 1000; //temps de reaction laisser au joueur en miliseconde
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

function createTiles(size){
	document.getElementsByClassName("game-board")[0].innerHTML = "";
	tiles = [];
	for(var i = 0; i < Math.pow(size, 2); i++){
		var node = document.createElement("div");
		node.classList.add("tile");
		node.id = "tile" + i;
		tiles.push({id: i, node: node});
		document.getElementsByClassName("game-board")[0].appendChild(node);
	}
}

function connect(){
	document.getElementsByClassName("connexion-panel")[0].classList.add("toCircle");
	//document.getElementsByClassName("overlay")[0].classList.add("hidden");
}

function switchChat(){
	document.getElementsByClassName("main")[0].classList.toggle("chat-opened");

}

function displaySequence(sequence){
	for(var s = 0; s < sequence.length; s++){
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
		if(t.id == sequence[index]){
			t.node.addEventListener("click", wrightTileListener);
		}else{
			t.node.addEventListener("click", wrongTileListener);
		}
	}
	globalTimeout =	setTimeout(gameOver, answerTimeout);
	clearInterval(timerInterval);
	runTimer();

	function wrightTileListener(){
		clear();
		var tile = tileById(sequence[index]);
		Synth.play('piano', scale[tile.id].note, scale[tile.id].octave, 0.5 * answerTimeout / 1000);
		if(index+1 < sequence.length){
			// On passe à l'élément suivant
			getUserClick(sequence, index+1);
		}else{
			// Sequence réussie
			alert("GG !");
		}
	}

	function wrongTileListener(){
		clear();
		gameOver();
	}

	function clear(){
		clearTimeout(globalTimeout);
		for(var t of tiles){
			t.node.removeEventListener("click", wrightTileListener);
			t.node.removeEventListener("click", wrongTileListener);
		}
	}

	function gameOver(){
		clear();
		alert("GAME OVER");
	}
}


function runTimer(){
    var elem = document.getElementsByClassName("timeBar")[0];
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


// Tests
//var sequence = [1,1,2,3,0];
var sequence = [0,0,0,1,2,1,0,2,1,1,0];
setTimeout(createTiles, 500, 2);
setTimeout(displaySequence, 2000, sequence);
setTimeout(getUserClick, 2000+answerTimeout*sequence.length, sequence, 0);
