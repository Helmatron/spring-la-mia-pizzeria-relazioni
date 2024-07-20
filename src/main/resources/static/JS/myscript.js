console.log("JS Pizzeria Collegato!");

function resetForm() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/nuova_pizza';
};

function resetFormGoHome() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/';
};

function resetFormGoGestionale() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/gestionale';
};