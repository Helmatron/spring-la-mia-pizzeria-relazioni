console.log("JS Pizzeria Collegato!");

function resetForm() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/pizze/nuova_pizza';
};

function resetFormGoHome() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/';
};

function resetFormGoListaPizze() {
	document.getElementById("pizza-form").reset();
	window.location.href = '/pizze/lista_pizze';
};

function resetFormGoOffer() {
	document.getElementById("specialOffer-form").reset();
	window.location.href = '/offerte/lista_offerte';
};


// Avviso prima del submit di cancellazione Offerta
function confirmOfferDeletion(event, form) {
	event.preventDefault();
	if (confirm('Sei sicuro di voler cancellare questa offerta?')) {
		form.submit();
	}
}

// Avviso prima del submit di cancellazione Pizza
function confirmPizzaDeletion(event, form) {
	event.preventDefault();
	if (confirm('Sei sicuro di voler cancellare questa Pizza?')) {
		form.submit();
	}
}

// Selettore per le offerte
function updateInput(button) {
            const title = button.getAttribute('data-title');
            const sconto = button.getAttribute('data-sconto');
            document.getElementById('specialOfferInput').value = title;
            document.getElementById('specialOfferSconto').innerText = '%' + sconto;
        }