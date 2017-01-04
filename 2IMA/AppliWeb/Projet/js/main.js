var globalTimeout;
var reactiontime = 1000; //temps de reaction laisser au joueur en miliseconde
var couputilise = false;
var indice=-1;
var 
function connect(){
	document.getElementsByClassName("connexion-panel")[0].classList.add("toCircle");
	//document.getElementsByClassName("overlay")[0].classList.add("hidden");
}

function switchChat(){
	document.getElementsByClassName("main")[0].classList.toggle("chat-opened");

}
function sequence(tableau){
	for(var tab in tableau){
		var t = parseInt(tab);
		setTimeout(
			function(indice){
				document.getElementsByClassName("game-board")[0].children[indice].classList.add("tile-active");
			},
			t*1000,
			tableau[t]
		);
		 setTimeout(
			function(indice2){
				document.getElementsByClassName("game-board")[0].children[indice2].classList.remove("tile-active");
			},
			t*1000+800,
			tableau[t]
		);
		 console.log((t+1)*800);
	}
	
}
function jouer(tableau){
	var resultat = [];
	var i =0;
	faute=false;
	while(!faute && tableau[i]!=null &&){ // settimeout -> click desactive timeout
		//global variable indice maj 
			globalTimeOut = 
			 setTimeout(
						function(){
							faute=true;
						},
						tempsdereaction,
					);
			//JESUISENATTENTEDUCLICK(
		
	}
	//si il a gagné 
}
function click(tableau,caseclique){
	//utiliser la global indice et couputilise
	//allumer la case 50 ms puis eteindre
	//si il clique sur la bonne case 
		//il desactive le timeout et il doit annoncer qu'il peux passer au suivant à la fonction jouer!
	if (!couputilise){
		document.getElementsByClassName("game-board")[0].children[caseclique].classList.add("tile-active");
		if(caseclique==tableau[indice]){
			document.getElementsByClassName("game-board")[0].children[caseclique].classList.remove("tile-active");
			clearTimeout(globalTimeout);
		}
	}
}
//lien avec le server
var tableaurecue = [1,2,3,0,1,1,2,3,0,2];
setTimeout(sequence(tableaurecue), 2000);