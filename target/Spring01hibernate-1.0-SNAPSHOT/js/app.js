document.addEventListener("DOMContentLoaded", function () {
    let counter = document.getElementById('car-counter');
    let counterIn = document.getElementsByName('mileage');

    function icons() {

        for (let i = 0; i < counter.length; i++) {
            let newCounter = '';
            let number = counter[i].innerHTML;
            let characters = number.split("");
            for (let j = 0; j < characters.length; j++) {
                switch (characters[j]) {
                    case '0':
                        characters[j] = '<img src="/images/counter/0.png" alt="0" class="counter">';
                        break;
                    case '1':
                        characters[j] = '<img src="/images/counter/1.png" alt="1" class="counter">';
                        break;
                    case '2':
                        characters[j] = '<img src="/images/counter/2.png" alt="2" class="counter">';
                        break;
                    case '3':
                        characters[j] = '<img src="/images/counter/3.png" alt="3" class="counter">';
                        break;
                    case '4':
                        characters[j] = '<img src="/images/counter/4.png" alt="4" class="counter">';
                        break;
                    case '5':
                        characters[j] = '<img src="/images/counter/5.png" alt="5" class="counter">';
                        break;
                    case '6':
                        characters[j] = '<img src="/images/counter/6.png" alt="6" class="counter">';
                        break;
                    case '7':
                        characters[j] = '<img src="/images/counter/7.png" alt="7" class="counter">';
                        break;
                    case '8':
                        characters[j] = '<img src="/images/counter/8.png" alt="8" class="counter">';
                        break;
                    case '9':
                        characters[j] = '<img src="/images/counter/9.png" alt="9" class="counter">';
                        break;
                    default:
                        characters[j] = '';
                        break;
                }
                newCounter = newCounter + characters[j];
            }
            counter[i].innerHTML = newCounter;
        }
    }

    counterIn[0].addEventListener('change', function () {
        icons();
    })

});