document.querySelectorAll('.nav-link').forEach(link => {
    link.addEventListener('click', function (event) {
//        event.preventDefault();
        const url = this.getAttribute('href');

        fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(data, 'text/html');
                    const mainContent = doc.querySelector('main').innerHTML;

                    document.getElementById('content').innerHTML = mainContent;
                    history.pushState(null, '', url);
                })
                .catch(error => console.error('Error fetching data:', error));
    });
});
let productName = "";
let userName = "";

document.getElementById("submit").addEventListener("click", function () {
    document.querySelector(".updateForm").classList.remove("active");
    document.querySelector(".overlay").classList.remove("active");
    document.querySelector(".form-container").classList.remove("active");
});
function openForm(event, userID, firstName, lastName, email, phoneNumber, address, role, imgURL) {
    event.preventDefault();
    userName = firstName + " " + lastName;
    document.getElementById("userID").value = userID;
    document.getElementById("firstname").value = firstName;
    document.getElementById("lastname").value = lastName;
    document.getElementById("email").value = email;
    document.getElementById("phonenumber").value = phoneNumber;
    document.getElementById("address").value = address;
    document.getElementById("role").value = role;

    document.getElementById("imgURL").value = '';
    document.querySelector(".updateForm").classList.add("active");
    document.querySelector(".overlay").classList.add("active");
}

function openProductForm(event, productID, sellerID, name, currentPrice, originalPrice, description, categoryID, imgURL) {
    event.preventDefault();
    productName = productID;
    document.getElementById('productID').value = productID;
    document.getElementById('sellerID').value = sellerID;
    document.getElementById('name').value = name;
    document.getElementById('originalPrice').value = originalPrice;
    document.getElementById('currentPrice').value = currentPrice;
    document.getElementById('description').value = description;
    document.getElementById('categoryID').value = categoryID;

    document.getElementById("imgURL").value = '';
    document.querySelector(".updateForm").classList.add("active");
    document.querySelector(".overlay").classList.add("active");
}
function openProductFormOfSeller(productID, name, currentPrice, originalPrice, description, categoryID, imgURL) {
    productName = name;
    document.getElementById('productIDAdd').value = productID;
    document.getElementById('nameAdd').value = productName;
    document.getElementById('currentPriceAdd').value = currentPrice;
    document.getElementById('originalPriceAdd').value = originalPrice;
    const safeDescription = description.replace(/'/g, "&#39;").replace(/"/g, "&quot;");
    document.getElementById('descriptionAdd').value = safeDescription;
    document.getElementById('categoryIDAdd').value = categoryID;

    document.getElementById("imgURLAdd").value = '';
    document.querySelector(".updateForm").classList.add("active");
    document.querySelector(".overlay").classList.add("active");
}
function addProductBySeller() {
    document.querySelector(".form-container").classList.add("active");
    document.querySelector(".overlay").classList.add("active");
    document.getElementById("close1").classList.add("active");
}
function confirmAddProduct() {
    return confirm("Bạn có chắc chắn đăng sản phẩm này lên không?");
}
function confirmUpdateUser() {
    return confirm("Bạn có chắc chắn cập nhật " + userName + " không?");
}
function confirmUpdateProduct() {
    return confirm("Bạn có chắc chắn cập nhật " + productName + " không?");
}
function closeForm() {
    document.querySelector(".updateForm").classList.remove("active");
    document.querySelector(".overlay").classList.remove("active");
    document.querySelector(".form-container").classList.remove("active");
}

function deleteUser(id, fullname) {
    if (confirm("Bạn có chắc chắn xóa khách hàng " + fullname + " không?")) {
        window.location.href = "deleteUser?id=" + id;
    }
}
function deleteProduct(productID, name) {
    if (confirm("Bạn có chắc chắn xóa sản phẩm  " + name + " không?")) {
        window.location.href = "deleteProduct?id=" + productID;
    }
}
function deleteproductofseller(sellerID, productID, name) {
    if (confirm("Bạn có chắc chắn xóa sản phẩm  " + name + " không?")) {
        window.location.href = "deleteproductofseller?sellerID=" + sellerID + "&id=" + productID;
    }
}



//====================Cart Chart JS======================
const labels = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
const data = {
    labels: labels,
    datasets: [
        {
            label: "Số lượng đơn",
            data: [65, 59, 80, 81, 56, 55, 40, 60, 30, 20, 100, 80],
            fill: false,
            borderColor: "rgb(75, 192, 192)",
            tension: 0.1,
        },
    ],
};

const config = {
    type: "line",
    data: data,
    options: {
        responsive: true,
        maintainAspectRatio: false,
    },
};

const ctx = document.getElementById("myChart").getContext("2d");
new Chart(ctx, config);