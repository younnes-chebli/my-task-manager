const readline = require('readline');
var rl = readline.createInterface(process.stdin, process.stdout);

const fs = require('fs');
var data = fs.readFileSync('./JavaScript/tasks.json');
var tasks = JSON.parse(data);

const saveData = (newData) => {
    newData = JSON.stringify(newData);
    fs.writeFile('./JavaScript/tasks.json', newData, 'utf-8', (error) => {
        if(error)
            console.log(error);
    });
};

const displayTasks = (array) => {
    console.log("---")
    for(element of array) {
        console.log(element);
    }
}

const continueOrExit = (option) => {
    switch (option) {
        case "0":
            exit();
            break;
        default:
            showMain();
            break;
    };
};

const isEmpty = (array) => {
    return array.length == 0;
};

const noTasks = () => {
    rl.question("You have no tasks yet! (Continue [any key] - Exit [0])\n", (option) => {
        continueOrExit(option);
    });
};

const showTasks = () => {
    if(isEmpty(tasks.tasks)) {
        noTasks();
    } else {
        displayTasks(tasks.tasks);
        rl.question(`Here are your tasks (Continue [any key] - Exit [0]):\n`, (option) => {
            continueOrExit(option);
        });    
    }
};

const askForNewAddTaskChoice = (option) => {
    switch (option) {
        case "1":
            addTask();
            break;
        default:
            showMain();
            break;
    }
};

const addTask = () => {
    displayTasks(tasks.tasks);
    rl.question(`Which task do you want to add? (Cancel [0])\n`, (task) => {
        if(task === "0") {
            showMain();
        } else if (task === "") {
            addTask();
        } else {
            tasks.tasks.push(`${task}`);
            saveData(tasks);
            displayTasks(tasks.tasks);
            rl.question(`Add another task? (Yes [1] - No [any key])\n`, (option) => {
                askForNewAddTaskChoice(option);
            });
        }
    });
};

const askForNewDeleteTaskChoice = (option) => {
    switch (option) {
        case "1":
            deleteTask();
            break;
        default:
            showMain();
            break;
    }
};

const outOfArray = (array, index) => {
    return index < 0 || index > array.length;
};

const deleteTask = () => {
    if(isEmpty(tasks.tasks)) {
        noTasks();
    } else {
        displayTasks(tasks.tasks);
        rl.question(`Which task do you want to delete? (give an index begginning from 1 - Cancel [0])\n`, (option) => {
            option = parseInt(option);
            if(option == 0) {
                showMain();
            } else if(outOfArray(tasks.tasks, option)) {
                deleteTask();
            } else {
                tasks.tasks.splice(option - 1, 1);
                saveData(tasks);
                if(isEmpty(tasks.tasks)) {
                    showMain();
                } else {
                    displayTasks(tasks.tasks);
                    rl.question(`Delete another task? (yes [1] - no [any key])\n`, (option) => {
                        askForNewDeleteTaskChoice(option);
                    });        
                }
            }
        });    
    }
};

const askForNewMarkTaskDoneChoice = (option) => {
    switch (option) {
        case "1":
            markTaskDone();
            break;
        default:
            showMain();
            break;
    }
};

const markTaskDone = () => {
    displayTasks(tasks.tasks);
    rl.question(`Which task do you want to mark as done? (give an index begginning from 1 - Cancel [0])\n`, (option) => {
        option = parseInt(option);
        if(option == 0) {
            showMain();
        } else if(outOfArray(tasks.tasks, option)) {
            markTaskDone();
        } else if(tasks.tasks[option - 1].includes("done")) {
            markTaskDone();
        } else {
            tasks.tasks[option - 1] = tasks.tasks[option - 1].concat(" (done)");
            saveData(tasks);
            displayTasks(tasks.tasks);
            rl.question(`Mark another task as done? (yes [1] - no [any key])\n`, (option) => {
                askForNewMarkTaskDoneChoice(option);
            });        
        }
    });
};

const exit = () => {
    console.log("Bye bye ;-)");
    rl.close();
};

const showMain = () => {
    rl.question("Welcome to your task manager, Press:\n 1. to see all your tasks\n 2. to add a task\n 3. to delete a task\n 4. to mark a task as done\n 5. to Exit the task manager\n", (option) => {
        switch (option) {
            case "1":
                showTasks();
                break;
            case "2":
                addTask();
                break;
            case "3":
                deleteTask();
                break;
            case "4":
                markTaskDone();
                break;
            case "5":
                exit();
                break;
            default:
                showMain();
        }
    });
};

showMain();