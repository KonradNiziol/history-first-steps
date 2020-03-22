
window.onload = load;
var sizeOfGallery = 7;
var catalogName = 'author';;

var max_height = 100; // definiujemy maksymalną szerokość obrazka
var max_width = 800;


/**
//------------------------------------------------
var photo = {src: 'xxx', category: 'author'};
var photos = [photo, photo, photo];

var photoList = document.getElementById('lista od galerii');

function show_by_category(categoryName)
{
	//in - pod p masz kolejne indeksy z tablicy
	//of - pod p masz cale photo
	for (let p of photos)
	{
		if (p.category === categoryName)
		{
			photoList.appendChild('<li><img src="' + p.src +'"></li>')
		}
	}
}

//------------------------------------------------
*/

function changeGallery(size, catalog){
	sizeOfGallery = size;
	catalogName = catalog;
	load();
}

function resizeImage(img) {
    // jeśli szerokość obrazka jest większa niż dopuszczalna maksymalna szerokość
    if(img.height !== max_height) {
        // obliczamy proporcje szerokość do wysokość
        factor = img.width / img.height;
        // obliczamy proporcjonalną wysokość, zaokrąglamy ją używając Math.floor();
        width = Math.floor(max_height * factor);
        // nadajemy obrazkowi nowe wymiary
        img.width = width;
        img.height = max_height;
    }
}

function load() {
	document.querySelector('.list-1').innerHTML = null;
	for(var i = 0; i < sizeOfGallery; i++)
		document.querySelector('.list-1').innerHTML += '<img src="resources/photos/' + catalogName + '/' + catalogName + '_' + i +'.jpg"' + ' onload="resizeImage(this);"' + ' onclick="change(' + i + ')" />';
	change(0);
}



function change(id) {
	var custom = '';
	var preload = new Image(); 
	preload.onload = function() {
		if(preload.width > max_width) // 3
			custom = ' style="height: '+(Math.floor(max_width / (preload.width / preload.height)))+'px;width:'+max_width+'px;"';
			
		document.getElementById('show-photo').innerHTML = '<img src="resources/photos/' + catalogName + '/' + catalogName + '_' + id +'.jpg"' + custom + ' />';
	}
	preload.src = 'resources/photos/' + catalogName + '/' + catalogName + '_' + id +'.jpg';
}


/**
document.querySelector('.btn').addEventListener('click', function() {
	numberOfIMG = document.getElementById('btn-1').textContent;
	document.getElementById('photo').src = 'resources/photos/author/author_' + numberOfIMG + '.jpg';
});
*/