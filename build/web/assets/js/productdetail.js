function addProductCart(productID, quantity, isLoggedIn) {
    if (!isLoggedIn) {
        alert("Bạn cần đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
        window.location.href = "login";
        return;
    }

    if (confirm("Bạn có chắc chắn thêm vào giỏ hàng không?")) {
        const request = new XMLHttpRequest();
        request.open("GET", "addproductcart?productID=" + productID + "&quantity=" + quantity, true);

        request.onreadystatechange = function () {
            if (request.readyState === 4) {

                const notification = document.getElementById("notification");
                const errorNotification = document.getElementById("errorNotification");

                if (request.status === 200) {
                    showNotification("notification", request.responseText, 3000);
                } else if (request.status === 401) {
                    alert("Bạn cần đăng nhập trước khi thêm sản phẩm vào giỏ hàng.");
                    window.location.href = "login";
                } else {
                    showNotification("errorNotification", request.responseText, 3000);
                }
            }
        };
        request.send();
    }
}
function buyProduct(productID, quantity, isLoggedIn) {
    if (!isLoggedIn) {
        if (confirm("Bạn cần đăng nhập trước khi thêm sản phẩm vào giỏ hàng. Bạn có muốn đăng nhập ngay không?")) {
            window.location.href = "login";
        }
        return;
    } else {
        window.location.href = "payment?productID=" + productID + "&quantity=" + quantity
    }
}


function showNotification(notificationId, message, duration) {
    const notification = document.getElementById(notificationId);
    const progress = notification.querySelector("div[id$='Progress']"); // Lấy thanh tiến trình
    const span = notification.querySelector("span");
    span.textContent = message;
    notification.style.display = "block";

    let width = 100;
    progress.style.width = width + '%';

    // Cập nhật thanh thời gian chạy
    const interval = setInterval(() => {
        width -= (100 / (duration / 100));
        progress.style.width = width + '%';

        if (width <= 0) {
            clearInterval(interval);
            notification.style.display = "none";
        }
    }, 100);

    setTimeout(() => {
        clearInterval(interval);
        notification.style.display = "none";
    }, duration);
}

function closeNotification(notificationId) {
    const notification = document.getElementById(notificationId);
    notification.style.display = "none";
}


function changeQuantity(delta) {
    const quantityInput = document.getElementById("quantity");
    let quantity = parseInt(quantityInput.value) || 1;

    quantity += delta;
    if (quantity < 1)
        quantity = 1;

    quantityInput.value = quantity;
}

function getQuantity() {
    const quantityInput = document.getElementById("quantity");
    return parseInt(quantityInput.value) || 1;
}