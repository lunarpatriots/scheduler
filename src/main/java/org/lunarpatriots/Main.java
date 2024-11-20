package org.lunarpatriots;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Main {
    public static void main(String[] args) {

        try {
            final List<Task> tasksWithoutDependency = new ArrayList<>();
            final List<Task> tasksWithDependency = new ArrayList<>();

            for (final String arg : args) {
                final String[] taskDetails = arg.split(",");

                final Task task = new Task(taskDetails);
                if (task.getDependencyTaskId() == 0L) {
                    tasksWithoutDependency.add(task);
                } else if (task.getDependencyTaskId() < 0L) {
                    throw new InvalidInputException("Dependency task Id should be greater than 0");
                } else {
                    tasksWithDependency.add(task);
                }
            }

            final List<Task> consolidatedList = createConsolidatedList(tasksWithoutDependency, tasksWithDependency);
            createSchedule(consolidatedList);
        } catch (final PatternSyntaxException | InvalidInputException ex) {
            System.out.println("Input format is invalid.");
        } catch (final Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static List<Task> createConsolidatedList(final List<Task> tasks, final List<Task> taskWithDependencies) {
        final List<Task> consolidatedList = new ArrayList<>();

        for(final Task task : tasks) {
            if (task.getDependencyTaskId() == 0L) {
                consolidatedList.add(task);

                final List<Task> dependentTasks = taskWithDependencies.stream()
                        .filter(task1 -> task1.getDependencyTaskId() == task.getTaskId())
                        .toList();

                if (!dependentTasks.isEmpty()) {
                    consolidatedList.addAll(dependentTasks);
                }
            }
        }

        return consolidatedList;
    }

    private static void createSchedule(final List<Task> taskList) {
        LocalDate date = LocalDate.now();
        for (final Task task: taskList) {
            task.setStartDate(Date.valueOf(date));
            date = date.plusDays(task.getTaskDuration() - 1);
            task.setEndDate(Date.valueOf(date));
            date = date.plusDays(1);

            System.out.println("Task " + task.getTaskId());
            System.out.println("Days to complete: " + task.getTaskDuration());
            System.out.println("Start date: " + task.getStartDate());
            System.out.println("End date: " + task.getEndDate());
            System.out.println();
        }
    }
}