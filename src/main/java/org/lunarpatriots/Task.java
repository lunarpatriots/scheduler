package org.lunarpatriots;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private long taskId;
    private int taskDuration;
    private long dependencyTaskId;
    private Date startDate;
    private Date endDate;

    public Task(final String[] taskDetails) throws InvalidInputException {
        this.taskId = Integer.parseInt(taskDetails[0]);
        this.taskDuration = Integer.parseInt(taskDetails[1]);
        this.dependencyTaskId = Long.parseLong(taskDetails[2]);

        if (this.taskId < 0 || this.taskDuration < 1 || this.dependencyTaskId < 0) {
            throw new InvalidInputException("Invalid input format.");
        }

    }
}
