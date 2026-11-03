# StudyQuest
StudyQuest :  a Java Swing desktop app to manage study tasks and earn XP rewards, with progress saved locally.
# StudyQuest – Smart Study Planner

StudyQuest is a simple Java desktop application that helps students manage their study tasks and stay motivated through XP rewards. It was built as a BCA minor project to practise GUI development, event handling, list management and file handling in core Java.

## Problem Statement
Students often have several assignments and study activities at the same time. Pending tasks are easy to forget when they are kept only in memory, and a plain to-do list gives little motivation to finish work. StudyQuest keeps all study tasks in one visible list and adds a small reward system to encourage completion.

## Features
- Add new study tasks to a visible list
- Mark a task as complete and earn 10 XP
- Delete a task without earning XP
- Level display based on total XP
- Input validation with dialog messages (empty input, no task selected)
- Saves XP and pending tasks locally, so progress is restored the next time the app starts
- Works offline, with no internet connection needed

## How It Works
Add a task, complete it, earn 10 XP, and see your updated XP and level.

## Tech Stack
- **Language:** Java
- **GUI:** Java Swing
- **Storage:** Local text file using Java file handling
- **Editor:** Visual Studio Code

## Key Code Components
- `DefaultListModel<String>` and `JList<String>` for managing and displaying tasks
- `ActionListener` for handling button clicks
- `saveData()` and `loadData()` for saving and restoring XP and tasks

## How to Run
1. Install Java (JDK 8 or higher).
2. Download or clone this repository.
3. Open a terminal in the project folder.
4. Compile: `javac Main.java`
5. Run: `java Main`

Progress is saved in `StudyQuestData.txt` inside the user's home folder.

## Testing
| Test case | Expected result | Status |
|---|---|---|
| Add a valid task | Task appears in the list | Pass |
| Add blank input | Validation message appears | Pass |
| Complete selected task | Task removed, +10 XP | Pass |
| Delete selected task | Task removed, no XP added | Pass |
| Restart the application | Saved XP and pending tasks load | Pass |

## Limitations
- Single-user desktop application
- Data is stored locally on the same computer
- No online sync or account system

## Future Improvements
- Study timer and session tracking
- Progress report with completed-task statistics
- Task deadlines and
