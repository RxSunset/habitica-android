package com.habitrpg.android.habitica.data.local;

import com.habitrpg.android.habitica.models.tasks.ChecklistItem;
import com.habitrpg.android.habitica.models.tasks.RemindersItem;
import com.habitrpg.android.habitica.models.tasks.Task;
import com.habitrpg.android.habitica.models.tasks.TaskList;
import com.habitrpg.android.habitica.models.tasks.TaskTag;
import com.habitrpg.android.habitica.models.tasks.TasksOrder;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

public interface TaskLocalRepository extends BaseLocalRepository {

    Observable<RealmResults<Task>> getTasks(String taskType, String userID);
    Observable<RealmResults<Task>> getTasks(String userId);

    void saveTasks(TasksOrder tasksOrder, TaskList tasks);

    void saveTask(Task task);

    void removeOldTasks(String userID, List<Task> onlineTaskList);
    void removeOldChecklists(List<ChecklistItem> onlineChecklistItems);
    void removeOldTaskTags(List<TaskTag> onlineTaskTags);
    void removeOldReminders(List<RemindersItem> onlineReminders);

    void deleteTask(String taskID);

    Observable<Task> getTask(String taskId);
    Observable<Task> getTaskCopy(String taskId);

    void markTaskCompleted(String taskId, boolean isCompleted);

    void saveReminder(RemindersItem remindersItem);
}
