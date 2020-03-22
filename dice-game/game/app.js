
/*
VARIABLE DECLARATION
*/
var scores, roundScore, activePlayer, gamePlaying, scoreToWin, isConfirm, isEndRound, scoreFromSelectedDice;
var diceToCheck = [];
var dices = [];


/*
CONSTRUCTOR
*/
//to create dice
function Dice(numberOfHoles, isCheck) {
	this.numberOfHoles = numberOfHoles;
	this.isCheck = isCheck;
}


/*
BUTTON SUPORT
*/
//NEW GAME BUTTON
document.querySelector('.btn-new').addEventListener('click', restart);

//GAME RULES BUTTON
document.querySelector('.btn-rules').addEventListener('click', function () {
	window.open("http://www.kosteczki.republika.pl/pliki/tysiac.html");
});

//ROLL DICE BUTTON
document.querySelector('.btn-roll').addEventListener('click', function () {
	
	if (gamePlaying) {
		
		if (isAllHide()){
			showTheDice();
		}
		randomDice();
		isConfirm = false;
	}
});

//CHECK BUTTON
document.querySelector('.btn-check').addEventListener('click', checkAndDisplay);

//CONFIRM BUTTON
document.querySelector('.btn-confirm').addEventListener('click', function () {
		checkAndDisplay();
	if (scoreFromSelectedDice > 0) {
		roundScore += scoreFromSelectedDice;
		document.getElementById('current-'+activePlayer).textContent = roundScore;
		isConfirm = true;
		scoreFromSelectedDice = 0;
		setScoreThrow(scoreFromSelectedDice);
		
		for(var i = 0; i<5; i++) {
			if(dices[i].isCheck === true){
				dices[i].isCheck = document.getElementById('checkbox-' + i).checked = false;
				document.querySelector('.dice-check-'+i).style.display = 'none';	
			}
		}
		activeBTNRollDice(true);
		
	}
});

//HOLD POINTS BUTTON
document.querySelector('.btn-hold').addEventListener('click', function () {
	
	if(isConfirm) {
		scores[activePlayer] += roundScore;
		document.getElementById('score-' + activePlayer).textContent = scores[activePlayer];
		scoreToWin = document.getElementById('score-win').value;
		//Check if player won the game
		if (scores[activePlayer] >= scoreToWin) {
			document.querySelector('.player-' + activePlayer + '-panel').classList.add('winner');
			document.querySelector('.player-' + activePlayer + '-panel').classList.remove('active');
			document.getElementById('name-' + activePlayer).textContent = 'Winner!!!';
			
			hideTheDice();
			activeBTNRollDice(false);
	
		} else {
			//Next Player
			nextPlayer();
		}
		activeBTNRollDice(true);
		
	}
	
	
});


/*
~~ALL FUNCTION~~
*/

function nextPlayer() {
	activePlayer === 0 ? activePlayer = 1 : activePlayer = 0;
	roundScore = 0;
	activeBTNRollDice(true);
	hideTheDice();
		
	document.getElementById('current-0').textContent = '0';
	document.getElementById('current-1').textContent = '0';
		
	document.querySelector('.player-0-panel').classList.toggle('active');
	document.querySelector('.player-1-panel').classList.toggle('active');
	
	for(var i = 0; i<5; i++) {	
		dices[i].isCheck = document.getElementById('checkbox-' + i).checked = false;
	}
}

function checkAndDisplay() {
	var j = 0;
	scoreFromSelectedDice = 0;
	
	for (var i = 0; i<dices.length; i++) {
		if(document.querySelector('.dice-check-'+i).style.display === 'block'){
			diceToCheck[j] = dices[i].numberOfHoles;
			j++;
		} 
	}
	
	isEndRound = false;
	diceToCheck.sort(function(a, b){return a - b});
	checkScoredCombinations();
	isEndRound = true;
	
	if (!isConfirm && (scoreFromSelectedDice === 0 && !isAnyPoint())){
		alert('One dice roll too much!! Next Player Pliss... :)');
		nextPlayer();
	}
	
	
	setScoreThrow(0);
	diceToCheck = [];
	j = 0;
	for (var i = 0; i<dices.length; i++) {
		dices[i].isCheck = document.getElementById('checkbox-' + i).checked;

		if (dices[i].isCheck){
			diceToCheck[j] = dices[i].numberOfHoles; 
			j++;
		}
	}
	
	diceToCheck.sort(function(a, b){return a - b});
	scoreFromSelectedDice = 0;
	checkScoredCombinations();
	setScoreThrow(scoreFromSelectedDice);

	
	
	
	
}


function isAnyPoint(){
	
	var isTheSame = false; 
	var howMany = 0;
	var firstDice = diceToCheck[0];
	
	for (var i = 0; i< diceToCheck.length ; i++) {
		if(firstDice === diceToCheck[i]){
			howMany++;
			if (howMany === 3) {
				isTheSame = true;
				return isTheSame;
			}	
		} else {
			firstDice = diceToCheck[i];
			howMany = 1;
		}
	}
	return isTheSame;
}

function checkScoredCombinations() {
	
	switch(diceToCheck.length){
		case 5:
			
			if(theSameResult(5)) {
				scoreFromSelectedDice = 1000 - scores[activePlayer];
				break;
			} else if (fiveConsecutiveNumbers()) {
				scoreFromSelectedDice = 150;
				break;
			}
			
			checkingTheCombination();
			break;
			
		case 4:
			
			if(theSameResult(4) && diceToCheck[0] !== 1) {
				scoreFromSelectedDice += 20*diceToCheck[0];
				break;	
			} else if(theSameResult(4)) {
				scoreFromSelectedDice += 200*diceToCheck[0];
				break;	
			}
			checkingTheCombination();
			break;
			
		case 3:
			
			if(theSameResult(3) && diceToCheck[0] !== 1) {
				scoreFromSelectedDice += 10*diceToCheck[0];
				break;
			} else if(theSameResult(3)) {
				scoreFromSelectedDice += 100*diceToCheck[0];
				break;
			}
			checkingTheCombination();
			break;
			
		case 2:
			scoreFromSelectedDice += fiveOrTenPoints(2);
			if(((diceToCheck[0] !== 1 && diceToCheck[0] !== 5) || (diceToCheck[1] !== 1 && diceToCheck[1] !== 5)) && isEndRound){
				scoreFromSelectedDice = 0;
			}
			break;
		case 1:
			scoreFromSelectedDice += fiveOrTenPoints(1);
			if(diceToCheck[0] !== 1 && diceToCheck[0] !== 5 && isEndRound){
				scoreFromSelectedDice = 0;
			}
			break;
	}
	
	return scoreFromSelectedDice;
	
}

function theSameResult(loopSize) {
	
	var isTheSameDice = true; 
	var firstDice = diceToCheck[0];
	
	for (var i = 1; i< loopSize ; i++) {
		if(firstDice !== diceToCheck[i]){
			isTheSameDice = false;
			return isTheSameDice;
		}
	}
	return isTheSameDice;
}

function fiveConsecutiveNumbers() {
	var isCorrect = true; 
	var firstDice = diceToCheck[0];
	
	for (var i = 1; i< diceToCheck.length ; i++) {
		if((firstDice + 1) !== diceToCheck[i]){
			isTheSameDice = false;
			return isTheSameDice;
		}
		firstDice += 1;
	}
	return isCorrect;
}

function fiveOrTenPoints(size) {
	var score = 0;
	
	for(var i = 0; i< size ; i++){
		if(diceToCheck[i]===1){
			score += 10;
		} else if(diceToCheck[i]===5) {
			score += 5;
		}
	}
	return score;
}

function checkingTheCombination() {
	
	if(diceToCheck[0] === 1 && !theSameResult(4)) {
		scoreFromSelectedDice += 10;
		diceToCheck.shift();
		checkScoredCombinations();
		
	} else if (diceToCheck[0] === 1 && !theSameResult(3)) {
		scoreFromSelectedDice += 10;
		diceToCheck.shift();
		checkScoredCombinations();
		
	} else if(diceToCheck[0] === 5) {
		scoreFromSelectedDice += 5;
		diceToCheck.shift();
		checkScoredCombinations();
				
	} else if (diceToCheck[diceToCheck.length-1] === 5) {
		scoreFromSelectedDice += 5;
		diceToCheck.pop();
		checkScoredCombinations();
	}
}

function randomDice(){
	for(var i = 0; i<5; i++) {
		//Lottery numbers
		dices[i].numberOfHoles = Math.floor(Math.random() * 6) + 1;
		//Displaying the results on the dice png
		document.getElementById('dice-' + i).src = 'dice-' + dices[i].numberOfHoles + '.png';
	}
	activeBTNRollDice(false);
}

function isAllHide(){
	var isHide = true;
	for(var i = 0; i<dices.length; i++){
		if(document.querySelector('.dice-check-'+i).style.display === 'block'){
			isHide = false;
			return isHide;
		}
	}
	return isHide;
}

function restart() {
	scores = [0, 0];
	activePlayer = 0;
	roundScore = 0;
	lastDice = 0;
	activeBTNRollDice(true);
	hideTheDice();
	
	dices[0] = new Dice(0,false);
	dices[1] = new Dice(0,false);
	dices[2] = new Dice(0,false);
	dices[3] = new Dice(0,false);
	dices[4] = new Dice(0,false);
	
	document.querySelector('.player-0-panel').classList.remove('active');
	document.querySelector('.player-1-panel').classList.remove('active');
	document.querySelector('.player-0-panel').classList.remove('winner');
	document.querySelector('.player-1-panel').classList.remove('winner');
	document.querySelector('.player-0-panel').classList.add('active');
	
	document.getElementById('name-0').textContent = 'PLAYER 1';
	document.getElementById('name-1').textContent = 'PLAYER 2';
	document.getElementById('score-0').textContent = '0';
	document.getElementById('score-1').textContent = '0';
	document.getElementById('current-0').textContent = '0';
	document.getElementById('current-1').textContent = '0';
	document.getElementById('temporary-0').textContent = '0';
	document.getElementById('temporary-1').textContent = '0';
}

function showTheDice() {
	document.querySelector('.dice-check-0').style.display = 'block';		
	document.querySelector('.dice-check-1').style.display = 'block';		
	document.querySelector('.dice-check-2').style.display = 'block';		
	document.querySelector('.dice-check-3').style.display = 'block';		
	document.querySelector('.dice-check-4').style.display = 'block';		

}

function hideTheDice() {
	document.querySelector('.dice-check-0').style.display = 'none';		
	document.querySelector('.dice-check-1').style.display = 'none';		
	document.querySelector('.dice-check-2').style.display = 'none';		
	document.querySelector('.dice-check-3').style.display = 'none';		
	document.querySelector('.dice-check-4').style.display = 'none';
}

function setScoreThrow(result) {
	document.getElementById('temporary-'+activePlayer).textContent = result;
}

function activeBTNRollDice(isActive) {
	gamePlaying = isActive;
	if(isActive) {
		document.querySelector('.roll').classList.add('active-roll');
	} else {
		document.querySelector('.roll').classList.remove('active-roll');
	}
	
}

