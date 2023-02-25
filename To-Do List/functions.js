// Load our saved tasks from a previous session
// Initiate a global <ul> task-list
let taskList = document.getElementById("task-list");

// Get the saved taskList from local storage
let savedList = JSON.parse(localStorage.getItem("tasks")) || [];

// Recreate our saved list as individual <li> nodes
for (let i = 0; i < savedList.length; i++) {
    let listItem = savedList[i].task;
    let newCheckbox = "";
    if (savedList[i].done === true) {
        newCheckbox = '<li><input checked type="checkbox" onclick="saveData()">'
    }
    else {
        newCheckbox = '<li><input type="checkbox" onclick="saveData()">'
    }
    let newTaskNode = newCheckbox + '<span class="task">' + listItem + '</span><button class="delete-btn" onclick="removeNode(this)">Remove</button></li>';
    taskList.insertAdjacentHTML("beforeend", newTaskNode);
}

// The addTask() will add a new task and update the stored task-list
// It wraps the inputted string in a new <li> with a checkbox and a remove-button
// It rejects if there is no input given
function addTask () {
    let newTaskInput = document.getElementById("input-task").value;
    if (newTaskInput !== "") {
        let taskListItem = document.getElementById("task-list");
        let newTaskNode = '<li><input type="checkbox"><span class="task">' + newTaskInput + '</span><button class="delete-btn" onclick="removeNode(this)">Remove</button></li>';
        taskListItem.insertAdjacentHTML("beforeend", newTaskNode);
        document.getElementById("input-task").value = "";
        saveData();
    }
}

function markAsDone(box) {
    saveData();
}

// This will remove an entire list-item and update the stored task-list
function removeNode(node) {
    node.parentNode.remove();
    saveData();
}

// To store our task-list on our local storage
// This recreates the entire array each time, so needs improvement when working with larger datasets
function saveData() {
    savedList = [];
    let taskList = document.getElementsByTagName("li");
    for (let i = 0; i < taskList.length; i++) {
        const listItem = {
            task: taskList[i].childNodes[1].textContent,
            done: taskList[i].childNodes[0].checked
        };
        savedList.push(listItem);
    }
    localStorage.setItem("tasks", JSON.stringify(savedList));
}
