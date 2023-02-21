document.addEventListener("keypress", function(event) {
    if (event.code == "KeyA") {
        let audio = new Audio("white_keys/A.mp3");
        audio.play();
    }
    else if (event.code == "KeyS") {
        let audio = new Audio("white_keys/S.mp3");
        audio.play();
    }
    else if (event.code == "KeyD") {
        let audio = new Audio("white_keys/D.mp3");
        audio.play();
    }
    else if (event.code == "KeyF") {
        let audio = new Audio("white_keys/F.mp3");
        audio.play();
    }
    else if (event.code == "KeyG") {
        let audio = new Audio("white_keys/G.mp3");
        audio.play();
    }
    else if (event.code == "KeyH") {
        let audio = new Audio("white_keys/H.mp3");
        audio.play();
    }
    else if (event.code == "KeyJ") {
        let audio = new Audio("white_keys/J.mp3");
        audio.play();
    }
    else {
        console.log("Warning: unboundkey is pressed.")
    }
})
