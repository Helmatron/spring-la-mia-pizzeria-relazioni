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
function updateInput(element) {
	var title = element.getAttribute('data-title');
	var sconto = element.getAttribute('data-sconto');
	var offerId = element.getAttribute('data-id');

	document.getElementById('specialOfferInput').value = title;
	document.getElementById('specialOfferSconto').textContent = sconto + '%';
	document.getElementById('specialOfferId').value = offerId; // Aggiorna il campo nascosto con l'ID dell'offerta
}

// Elimina collegamento offerta/pizza
function removeOffer() {
	document.getElementById('specialOfferInput').value = "Nessuna Offerta";
	document.getElementById('specialOfferSconto').textContent = '';
	document.getElementById('specialOfferId').value = ''; // Imposta l'ID dell'offerta a null
}

//Funzione prezzo scontato
