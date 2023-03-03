// Initialize projectNumber. When popup is clicked, this is updated, so the correct window can be closed
let projectNumber = 0;

// Open corresponding window, using the n input given by the button
function openWindow(n) {
    projectNumber = n;
    document.getElementById("modal").style.display = "flex";
    document.getElementById("project" + n + "popup").style.display = "initial";
}

// Close corresponding window, using the n input given by the button
function closeWindow(n) {
    document.getElementById("project" + n + "popup").style.display = "none";
    document.getElementById("modal").style.display = "none";
}

// When clicked outside the textbox, close it
window.addEventListener("click", function(event) {
    if (event.target === document.getElementById("modal")) {
        closeWindow(projectNumber);
    }
});

// Toggle between adding / removing "responsive" class to navbar when hamburger-icon is clicked
function showMenu() {
    let navItem = document.getElementsByClassName("navbar-sm")
    let menuIcon = document.getElementById("menu-icon");
    let closeIcon = document.getElementById("close-icon");

    if (menuIcon.style.display === "block") {
        closeIcon.style.display = "block";
        menuIcon.style.display = "none";
        for (let i = 0; i < navItem.length; i++) {
            navItem[i].style.display = "block";
        }
    } else {
        closeIcon.style.display = "none";
        menuIcon.style.display = "block";
        for (let i = 0; i < navItem.length; i++) {
            navItem[i].style.display = "none";
        }
    }
}
