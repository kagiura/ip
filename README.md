# Wen: Your assistant chatbot!

This is Wen, your personal assistant for school-related tasks. Keep track of all your scheduling needs here!

## User Guide

### `todo`
Create a new to-do list item.
#### Example
```sh
todo Get some milk
```

### `deadline`
Create a new deadline.
#### Parameters
- `/by` - The deadline to complete this task by.
#### Example
```sh
deadline Complete CS2113 weekly quiz /by 12pm
```

### `event`
Create a new event.
#### Parameters
- `/from` - The start time of the event.
- `/to` - The end time of the event.
#### Example
```sh
deadline Join group meeting /from 5pm /to 8pm
```

### `list`
Displays all of your tasks.
#### Example
```sh
list
```

### `find`
Find a task with the search query in the description.
#### Parameters
The word you want to search for
#### Example
```sh
find CS2113
```

### `mark` & `unmark`
Mark one of your tasks as done.
#### Parameters
The index of the task; you can find the task index by using `list` or `find`
#### Example
```sh
mark 5
unmark 3
```

### `delete`
Deletes one of your tasks
#### Parameters
The index of the task; you can find the task index by using `list` or `find`
#### Example
```sh
delete 4
```

