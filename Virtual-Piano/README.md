# Virtual Piano
## Learning outcomes
You will learn how to write HTML documents and work with the most popular CSS properties. You will also learn how to use JavaScript to make event handlers for browser events and create and insert new elements into documents.

## About
One of the many things that a front-end developer has to take care of is binding the functionality of a web application to the keys on the keyboard. In order to develop and strengthen the important skills of working with keyboard keys, we will make a virtual piano in this project.

---

## [Stage 1](https://hyperskill.org/projects/101/stages/556/implement)
### Objectives

* Change the page title to "Virtual Piano".
* Add 7 `<kbd>` tags to the document body and enclose letters `A`, `S`, `D`, `F`, `G`, `H`, and `J` in them. Tags `<kbd>` must be direct descendants of body.

---

## [Stage 2](https://hyperskill.org/projects/101/stages/557/implement)
### Objectives

* Change the page background color.
* Change the key background color to white.
* Set the size of the keys.
* Wrap the `<kbd>` tags in `<div>` with the `container` class.
* Remove the distance between the keys. For example, you can do it with the `margin-left` property.
* Set boundaries for `<kbd>` elements.
* Place the piano in the center of the screen.

---

## [Stage 3](https://hyperskill.org/projects/101/stages/558/implement)
### Objectives

* Create event handlers that will output messages in the console when you press the previously assigned keys. Your message should contain the name of the key in single quotes.
* If any other key is pressed, output a warning in the console.

---

## [Stage 4](https://hyperskill.org/projects/101/stages/559/implement)
### Objectives

Modify the event handlers so that when you press the assigned keys, audio objects are created and played instead of messages displayed in the console.

---

## [Stage 5](https://hyperskill.org/projects/101/stages/560/implement)
### Objectives

* Add 5 `<kbd>` tags to the HTML document body that will be responsible for the black keys: `W`, `E`, `T`, `Y`, and `U`.
* Create event handlers that play audio when keys with these characters are pressed.
* Wrap all the white keys in one `<div>` tag with the class white-keys.
* Wrap all the black keys in one `<div>` tag with the class black-keys.
* Set the color of the black keys to black.
* Resize the black keys, remembering to set the `display` property to `inline-block` value.
* Change the location of the black keys using positioning properties.
* Change the color of the letters displayed on the black keys.
