// ============Slider show========================
function nextSlide() {
    let lists = document.querySelectorAll('.item');
    document.getElementById('slide').appendChild(lists[0]);
}

function prevSlide() {
    let lists = document.querySelectorAll('.item');
    document.getElementById('slide').prepend(lists[lists.length - 1]);
}

document.getElementById('next').onclick = function () {
    nextSlide();
    resetAutoSlide(); 
};

document.getElementById('prev').onclick = function () {
    prevSlide();
    resetAutoSlide(); 
};


let autoSlide = setInterval(nextSlide, 5000);

function resetAutoSlide() {
    clearInterval(autoSlide); 
    autoSlide = setInterval(nextSlide, 5000); 
}


// ===================CountDown===================
const countdownTime = 100 * 60 * 1000; 
const endTime = Date.now() + countdownTime;

const countdownElement = document.getElementById('countdown');

function updateCountdown() {
    const now = Date.now();
    const timeRemaining = endTime - now;

    if (timeRemaining <= 0) {
        countdownElement.innerHTML = "00:00:00";
        clearInterval(interval);
        return;
    }
    
    const hours = Math.floor((timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);

    countdownElement.innerHTML = `${hours < 10 ? '0' : ''}${hours} ${minutes < 10 ? '0' : ''}${minutes} ${seconds < 10 ? '0' : ''}${seconds}`;
}
const interval = setInterval(updateCountdown, 1000);
updateCountdown();

// ===================Bars==============================
document.querySelector('.bars').addEventListener('click', function() {
    const menu = document.querySelector('.nav-left'); 
    menu.classList.toggle('active');
});




  