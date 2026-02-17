# Jimjam User Guide

Jimjam is a helpful chatbot tool that frees your mind of having to remember things you need to do.

![GUI](Ui.png)

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding Tasks](#adding-tasks)
    * [Managing Tasks](#managing-tasks)
    * [Exiting the App](#exiting-the-app)
* [Command Summary](#command-summary)

## Quick Start
1.  Ensure you have **Java 17** or above spinning on your machine.
2.  Download the latest `jimjam.jar` from our [releases page](https://github.com/jimjam-user/ip/releases).
3.  Open your terminal or double-click the jar to start the session.
4.  Type your command in the input bar and hit Enter. Jimjam will handle the rest.

## Features
> **Note:** Parameters in `<angle_brackets>` are mandatory. Parameters in `[square_brackets]` are optional.

### Adding Tasks

#### 1. Todo (`todo`)
For those simple tasks that just need to get done.
* **Format:** `todo <description>`
* **Example:** `todo Write song`

**Expected Output**:
```
Got it. I've added:
[T][ ] Write song
Now you have 1 task.
```
---

#### 2. Deadline (`deadline`)
For the tasks that have a hard cutoff.
* **Format:** `deadline <description> /by <yyyy-MM-dd`
* **Example:** `deadline Submit demo tape /by 2026-05-20`

**Expected Output**:
```
Got it. I've added:
[D][ ] Submit demo tape (by: May 20 2026)
Now you have 2 tasks.
```
---

#### 3. Event (`event`)
For the tasks that span a specific timeframe.
* **Format:** `event <description> /from <yyyy-MM-dd /to <yyyy-MM-dd>`
* **Example:** `event Jam Session /from 2026-06-12 /to 2026-06-12`

**Expected Output**:
```
Got it. I've added:
[E][ ] Jam Session (from: Jun 12 2026 to: Jun 12 2026)
Now you have 2 tasks.
```

### Managing Tasks

#### 4. List (`list`)
Displays all tasks currently stored in your list.
* **Format:** `list`
* **Example:** `list`

**Expected Output:**
```
1: [T][ ] Write song
2: [D][ ] Submit demo tape (by: May 20 2026)
3: [E][ ] Jam Session (from: Jun 12 2026 to: Jun 12 2026)
```

#### 5. Mark as Done (`mark`)
Marks a specific task as completed.
* **Format:** `mark <index>`
* **Example:** `mark 2`

**Expected Output:**
```
Nice! I've marked this task as done:
[D][X] Submit demo tape (by: May 20 2026)
```

#### 6. Mark as Not Done (`unmark`)
Marks a specific task as completed.
* **Format:** `unmark <index>`
* **Example:** `unmark 2`

**Expected Output:**
```
OK, I've marked this task as not done yet:
[D][ ] Submit demo tape (by: May 20 2026)
```

#### 7. Delete (`delete`)
Permanently removes a task from your list using its index number.
* **Format:** `delete <index>`
* **Example:** `delete 2`

**Expected Output:**
```
Got it. I've removed:
[D][ ] Submit demo tape (by: May 20 2026)
Now you have 2 tasks.
```

#### 8. Find (`find`)
Searches for tasks that contain a specific keyword in their description.
* **Format:** `find [keyword]`
* **Example:** `find song`

**Expected Output:**
```
Here are the matching tasks in your list:
1: [T][ ] Write song
```

#### 9. Remind (`remind`)
Displays all tasks occurring within the next `n` days.
* **Format:** `remind <n>`
* **Example:** `remind 7`

**Expected Output:**
```
Here are your reminders:
1: [D][ ] Submit demo tape (by: May 20 2026)
```

### Exiting the App

#### 10. Exit (`bye`)
Closes the application. Your data is automatically saved before the session ends.
* **Format:** `bye`
* **Example:** `bye`

**Expected Output:**
```
Bye. Hope to see you again soon!
```

## Command Summary

| Action           | Format, Examples                                                                                                          |
|------------------|---------------------------------------------------------------------------------------------------------------------------|
| **Add Todo**     | `todo <description>`  <br> e.g., `todo Write song`                                                                        |
| **Add Deadline** | `deadline <description> /by <yyyy-MM-dd>`  <br> e.g., `deadline Submit demo tape /by 2026-05-20`                          |
| **Add Event**    | `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`  <br> e.g., `event Jam Session /from 2026-06-12 /to 2026-06-12` |
| **List**         | `list`                                                                                                                    |
| **Mark**         | `mark <index>`  <br> e.g., `mark 1`                                                                                       |
| **Unmark**       | `unmark <index>`  <br> e.g., `unmark 1`                                                                                   |
| **Delete**       | `delete <index>`  <br> e.g., `delete 3`                                                                                   |
| **Find**         | `find <keyword>`  <br> e.g., `find song`                                                                                  |
| **Remind**       | `remind <days>`  <br> e.g., `remind 7`                                                                                    |
| **Exit**         | `bye`                                                                                                                     |
