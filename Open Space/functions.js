function password() {
    let pass = document.getElementById("password").value;
    if (pass === "TrustNo1") {
        let inputList = document.getElementsByTagName("input");
        for (let i = 1; i < inputList.length; i++) {
            inputList[i].removeAttribute("disabled");
        }
        inputList[0].setAttribute("disabled","");
        inputList[1].setAttribute("disabled","");
    }
}

function launch() {
    console.log("Launched!")
}
