document.addEventListener("keypress", function(event) {
    if (event.code == "KeyA") {
        console.log("The 'A' key is pressed.")
    }
    else if (event.code == "KeyS") {
        console.log("The 'S' key is pressed.")
    }
    else if (event.code == "KeyD") {
        console.log("The 'D' key is pressed.")
    }
    else if (event.code == "KeyF") {
        console.log("The 'F' key is pressed.")
    }
    else if (event.code == "KeyG") {
        console.log("The 'G' key is pressed.")
    }
    else if (event.code == "KeyH") {
        console.log("The 'H' key is pressed.")
    }
    else if (event.code == "KeyJ") {
        console.log("The 'J' key is pressed.")
    }
    else {
        console.log("Warning: unboundkey is pressed.")
    }
})
