# Scheduler

## Input format
```
taskId,taskDuration,dependencyTaskId
```
- `dependencyTaskId` should be zero if it does not depend on other tasks
- `taskId` and `taskDuration` cannot be less than zero

## Usage
### Input
```
1232,5,3
405,2,0
3,3,0
```
### Output
```
Task 405
Days to complete: 2
Start date: 2024-11-20
End date: 2024-11-21

Task 3
Days to complete: 3
Start date: 2024-11-22
End date: 2024-11-24

Task 1232
Days to complete: 5
Start date: 2024-11-25
End date: 2024-11-29
```
