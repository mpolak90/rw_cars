document.addEventListener("DOMContentLoaded", function () {

        let nameInput = document.getElementsByName('name');
        let priceInput = document.getElementsByName('price');
        let yearInput = document.getElementsByName('production_year');
        let powerInput = document.getElementsByName('power');
        let capacityInput = document.getElementsByName('capacity');
        let fuelInput = document.getElementsByName('fuel_type');
        let mileageInput = document.getElementsByName('mileage');
        let imageInput = document.getElementsByName('image_link');
        let imagePut = document.getElementById('image_upload');

        let nameOutput = document.getElementById('car-name');
        let priceOutput = document.getElementById('car-price');
        let powerOutput = document.getElementById('car-power');
        let capacityOutput = document.getElementById('car-capacity');
        let yearOutput = document.getElementById('car-year');
        let fuelOutput = document.getElementById('car-fuel');
        let mileageOutput = document.getElementById('car-mileage');
        let imageOutput = document.getElementById('car-image');


        if (nameInput[0].value !== '') {
            nameOutput.innerHTML = nameInput[0].value;
            priceOutput.innerHTML = Number(priceInput[0].value).toLocaleString();
            powerOutput.innerHTML = Number(powerInput[0].value).toLocaleString();
            yearOutput.innerHTML = yearInput[0].value;
            capacityOutput.innerHTML = Number(capacityInput[0].value).toLocaleString();
            mileageOutput.innerHTML = Number(mileageInput[0].value).toLocaleString();
            fuelOutput.innerHTML = fuelInput[0].value;
            imageOutput.src = imageInput[0].value;
        }

        nameInput[0].addEventListener('change', function () {
            nameOutput.innerHTML = nameInput[0].value;
        });

        priceInput[0].addEventListener('change', function () {
            priceOutput.innerHTML = Number(priceInput[0].value).toLocaleString();
        });

        powerInput[0].addEventListener('change', function () {
            powerOutput.innerHTML = Number(powerInput[0].value).toLocaleString();
        });

        yearInput[0].addEventListener('change', function () {
            yearOutput.innerHTML = yearInput[0].value;
        });

        capacityInput[0].addEventListener('change', function () {
            capacityOutput.innerHTML = Number(capacityInput[0].value).toLocaleString();
        });

        mileageInput[0].addEventListener('change', function () {
            mileageOutput.innerHTML = Number(mileageInput[0].value).toLocaleString();
        });

        fuelInput[0].addEventListener('change', function () {
            fuelOutput.innerHTML = fuelInput[0].value;
        });

        imageInput[0].addEventListener('change', function () {
            imageOutput.src = imageInput[0].value;
        });

        imagePut.addEventListener('change', function () {
            let file = this.files[0];

            let reader = new FileReader();
            reader.onload = function () {
                let output = document.getElementById('car-image');
                imageInput[0].value = "images/auctions/" + file.name;
                output.src = reader.result;
            };

            reader.readAsDataURL(event.target.files[0]);
        });
    }
);