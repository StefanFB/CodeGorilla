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
    if (event.target == document.getElementById("modal")) {
        closeWindow(projectNumber);
    }
});
