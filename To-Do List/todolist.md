# Project To-Do List

This is the directory for my To-Do List project!

## Stage 1

### Objectives

Your HTML file should contain the following elements:

* <input> element with the #input-task id that indicates the task name;
* <button> with the #add-task-button id. Add a task by clicking on this button.
* <ul> element with the #task-list id. Store the tasks in this element.
  
Create three hardcoded tasks inside the <ul> element. Wrap each task in <li> tags. Each <li> tag should have the following elements:

* <input> element of the checkbox type. Mark the task as complete by clicking on it.
* <span> element with the task class. Store the task name inside this element.
* <button> element with the delete-btn class. Leave it as it is. In this stage, when users click on it, this button should do nothing.
  
There is no need to implement the business logic of adding new tasks, marking them as complete, and deleting them. You can use any design you want.

  
## Stage 2
  
### Objectives
  
If a user enters a task name to the input field and presses the button with the #add-task-button id, the program should wrap the task in <li> tags and add it to the end of the <ul> tag.

The task should have the same elements as in the previous stage:

<input> element of the checkbox type. The checkbox should be unchecked by default.
<span> element with the task class. It contains the task name.
<button> element with the delete-btn class. Remove the task from the list by clicking on this button.
Once the task has been added, clear the input field so that a user can add another task.

A task must have a name to be successfully added to a task list.

Also, do not remove the hardcoded tasks from the previous stage.

  
## Stage 3
  
### Objectives
  
A task should be marked as complete when a user clicks on the task checkbox. When the checkbox is checked, the <span> element with the task class should have the following properties:

`text-decoration: line-through;`
If the checkbox is unchecked, remove these properties from the <span> element.

  
## Stage 4
  
### Objectives
  
We need to make our program fetch the to-do list from local storage and display the tasks with their states. Also, we need to make the program update the task list in local storage in the following cases:

* A task is created.
* A task is deleted.
* A task is marked as complete.
  
We don't need the hardcoded tasks anymore, so you can delete them. When users reload the page, the program should store the tasks.
