const ACTIVE_CLASS = "active";
const cvform =  document.getElementById("cvForm");
const steps = [...cvform.getElementsByClassName("form_wrapper")];
const removebuttons = [...cvform.getElementsByClassName("remove")];
const progress = [... document.getElementsByClassName("progress_item")]
const lines = [... document.getElementsByClassName("line")]
const btnpPrevious = cvform.querySelector("#Previous");
const btnpNext = cvform.querySelector("#Next");
const btnpSubmit = cvform.querySelector("#Submit");
console.log(btnpNext);
console.log(btnpPrevious);
console.log(btnpSubmit);
let currentIndex = steps.findIndex(step => {
	return step.classList.contains(ACTIVE_CLASS);
});
if(currentIndex < 0){
	currentIndex = 0;
	steps[0].classList.add(ACTIVE_CLASS)
}
for(let i = currentIndex; i>=0; i--){
    progress[i].style.background = "#FFFFCC";
	progress[currentIndex].style.borderColor = "#FFFF66";
	if (i<lines.length){
		lines[i].style.background = "#FFFF66";
	}

}
let currentStep = steps[currentIndex];
console.log(currentStep);

activateButtons = () => { 
if(currentIndex == 0){
	btnpPrevious.disabled=true;
}else{
	btnpPrevious.disabled=false;
}
if(currentIndex == steps.length-1){
	btnpNext.style.display="none";
	btnpSubmit.style.display="inline";
}else{
	btnpSubmit.style.display="none";
	btnpNext.style.display="inline";
}
};

for(let btn in removebuttons ){
	removebuttons[btn].onclick = (e =>{
			e.target.parentElement.remove();
			return;
	});
}

activateButtons();
btnpNext.onclick = (e => 
{
	console.log("Next clicked"); 
	currentIndex++;
	showPage();
	activateButtons();
	progress[currentIndex].style.background = "#FFFFCC";
	progress[currentIndex].style.borderColor = "#FFFF66";
	if (currentIndex<lines.length){
		lines[currentIndex].style.background = "#FFFF66";
	}
	return;
});
btnpPrevious.onclick = (e => 
{
	console.log("Next clicked"); 
	progress[currentIndex].style.background = "#E0E0E0";
	progress[currentIndex].style.borderColor = "#A0A0A0";
	if (currentIndex<lines.length){
		lines[currentIndex].style.background = "#A0A0A0";
	}	
	currentIndex--;
	showPage();
	activateButtons();
	return;
});

showPage = () => {
	console.log(currentStep);
	currentStep.classList.remove(ACTIVE_CLASS);
	steps[currentIndex].classList.add(ACTIVE_CLASS);
	currentStep = steps[currentIndex];
	progress[currentIndex].style.background = "#FFFFCC";
	progress[currentIndex].style.borderColor = "#FFFF66";
		if (currentIndex<lines.length){
		lines[currentIndex].style.background = "#FFFF66";
	}

}


console.log(currentIndex);