// Xử lý nút tăng số lượng
document.querySelectorAll(".increase-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {
        var input = this.parentElement.querySelector(".product-quantity");
        input.value = parseInt(input.value) + 1;
    });
});

// Xử lý nút giảm số lượng
document.querySelectorAll(".decrease-btn").forEach(function (btn) {
    btn.addEventListener("click", function () {
        var input = this.parentElement.querySelector(".product-quantity");
        if (parseInt(input.value) > 1) {
            input.value = parseInt(input.value) - 1;
        }
    });
});








