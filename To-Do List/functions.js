function addTask () {
    let newTask = document.getElementById("input-task").value;
    if (newTask !== "") {
        let taskList = document.getElementById("task-list");
        let newTaskNode = '<li><input type="checkbox"><span class="task">' + newTask + '</span><button class="delete-btn" onclick="this.parentNode.remove()">Remove</button></li>';
        taskList.insertAdjacentHTML("beforeend", newTaskNode);
        document.getElementById("input-task").value = "";
    }
}
