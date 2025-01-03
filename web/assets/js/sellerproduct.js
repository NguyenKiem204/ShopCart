 document.addEventListener("DOMContentLoaded", function () {
        let products = document.querySelectorAll("#product-list li");
        let showMoreButton = document.getElementById("show-more");
        let productsToShow = 18;

        function showProducts() {
            for (let i = 0; i < productsToShow && i < products.length; i++) {
                products[i].classList.add("visible");
            }
        }

        showMoreButton.addEventListener("click", function () {
            let currentlyVisibleProducts = document.querySelectorAll("#product-list li.visible").length;
            let newLimit = currentlyVisibleProducts + 6;

            for (let i = currentlyVisibleProducts; i < newLimit && i < products.length; i++) {
                products[i].classList.add("visible");
            }
            if (newLimit >= products.length) {
                showMoreButton.style.display = "none";
            }
        });
        showProducts();
    });