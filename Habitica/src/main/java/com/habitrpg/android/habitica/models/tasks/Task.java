package com.habitrpg.android.habitica.models.tasks;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.events.TaskDeleteEvent;
import com.habitrpg.android.habitica.ui.helpers.MarkdownParser;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {
    public static final String TYPE_HABIT = "habit";
    public static final String TYPE_TODO = "todo";
    public static final String TYPE_DAILY = "daily";
    public static final String TYPE_REWARD = "reward";
    public static final String FILTER_ALL = "all";
    public static final String FILTER_WEAK = "weak";
    public static final String FILTER_STRONG = "strong";
    public static final String FILTER_ACTIVE = "active";
    public static final String FILTER_GRAY = "gray";
    public static final String FILTER_DATED = "dated";
    public static final String FILTER_COMPLETED = "completed";
    public static final String FREQUENCY_WEEKLY = "weekly";
    public static final String FREQUENCY_DAILY = "daily";
    public static final String ATTRIBUTE_STRENGTH = "str";
    public static final String ATTRIBUTE_CONSTITUTION = "con";
    public static final String ATTRIBUTE_INTELLIGENCE = "int";
    public static final String ATTRIBUTE_PERCEPTION = "per";
    public String userId;
    public Float priority;
    public String text, notes, attribute, type;
    public double value;
    public RealmList<TaskTag> tags;
    public Date dateCreated;
    public int position;
    public TaskGroupPlan group;
    //Habits
    public Boolean up, down;
    //todos/dailies
    public boolean completed;
    public RealmList<ChecklistItem> checklist;
    public RealmList<RemindersItem> reminders;
    //dailies
    public String frequency;
    public Integer everyX, streak;
    public Date startDate;
    public Days repeat;
    //todos
    @SerializedName("date")
    public Date duedate;
    //TODO: private String lastCompleted;
    // used for buyable items
    public String specialTag;
    @Ignore
    public CharSequence parsedText;
    @Ignore
    public CharSequence parsedNotes;
    @PrimaryKey
    @SerializedName("_id")
    String id;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the priority
     */
    public Float getPriority() {
        return priority;
    }

    /**
     * @param i the priority to set
     */
    public void setPriority(Float i) {
        this.priority = i;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * To be allowed to set int value without problems
     *
     * @param value the value to set
     */
    public void setValue(double value) {
        this.setValue(Double.valueOf(value));
    }

    /**
     * Returns a string of the type of the Task
     *
     * @return the string of the Item type
     */
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TaskTag> getTags() {
        return tags;
    }

    public void setTags(RealmList<TaskTag> tags) {
        this.tags = tags;
    }

    public boolean containsAnyTagId(List<String> tagIdList) {
        getTags();

        for (TaskTag t : tags) {
            if (tagIdList.contains(t.getTag().getId())) {
                return true;
            }
        }

        return false;
    }

    public boolean containsAllTagIds(List<String> tagIdList) {
        getTags();

        List<String> allTagIds = new ArrayList<String>();

        for (TaskTag t : tags) {
            allTagIds.add(t.getTag().getId());
        }

        return allTagIds.containsAll(tagIdList);
    }

    /**
     * @return whether or not the habit can be "upped"
     */
    public boolean getUp() {
        if (up == null) {
            return false;
        }
        return up;
    }

    /**
     * Set the Up value
     */
    public void setUp(Boolean up) {
        this.up = up;
    }

    /**
     * @return whether or not the habit can be "down"
     */
    public boolean getDown() {
        if (down == null) {
            return false;
        }
        return down;
    }

    /**
     * Set the Down value
     */
    public void setDown(Boolean down) {
        this.down = down;
    }


    public boolean getCompleted() {
        return completed;
    }

    /**
     * Set whether or not the daily is completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    public List<ChecklistItem> getChecklist() {
        return this.checklist;
    }

    public void setChecklist(RealmList<ChecklistItem> checklist) {
        this.checklist = checklist;
    }

    public List<RemindersItem> getReminders() {
        return this.reminders;
    }

    public void setReminders(RealmList<RemindersItem> reminders) {
        this.reminders = reminders;
    }

    public Integer getCompletedChecklistCount() {
        Integer count = 0;
        for (ChecklistItem item : this.getChecklist()) {
            if (item.getCompleted()) {
                count++;
            }
        }
        return count;
    }

    public String getFrequency() {
        if (frequency == null) {
            return FREQUENCY_WEEKLY;
        }
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getEveryX() {
        if (everyX == null) {
            return 1;
        }
        return everyX;
    }

    public void setEveryX(Integer everyX) {
        this.everyX = everyX;
    }

    public Date getStartDate() {
        if (startDate == null) {
            return new Date();
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the repeat array.<br/>
     * This array contains 7 values, one for each days, starting from monday.
     */
    public Days getRepeat() {
        if (repeat == null) {
            repeat = new Days();
        }
        return repeat;
    }

    /**
     * @param repeat the repeat array to set
     */
    public void setRepeat(Days repeat) {
        this.repeat = repeat;
    }

    /**
     * @return the streak
     */
    public int getStreak() {
        if (streak == null) {
            return 0;
        }
        return streak;
    }

    /**
     * @param streak the streak to set
     */
    public void setStreak(Integer streak) {
        this.streak = streak;
    }


    /**
     * @return the due date
     */
    public Date getDueDate() {
        return this.duedate;
    }

    /**
     * Set the due date
     *
     * @param duedate the date to set
     */
    public void setDueDate(@Nullable Date duedate) {
        this.duedate = duedate;
    }

    /**
     * @return the attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param attribute the attribute to set
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getLightTaskColor() {
        if (this.value < -20)
            return R.color.worst_100;
        if (this.value < -10)
            return R.color.worse_100;
        if (this.value < -1)
            return R.color.bad_100;
        if (this.value < 1)
            return R.color.neutral_100;
        if (this.value < 5)
            return R.color.good_100;
        if (this.value < 10)
            return R.color.better_100;
        return R.color.best_100;
    }

    /**
     * Get the button color resources depending on a certain score
     *
     * @return the color resource id
     */
    public int getMediumTaskColor() {
        if (this.value < -20)
            return R.color.worst_50;
        if (this.value < -10)
            return R.color.worse_50;
        if (this.value < -1)
            return R.color.bad_50;
        if (this.value < 1)
            return R.color.neutral_50;
        if (this.value < 5)
            return R.color.good_50;
        if (this.value < 10)
            return R.color.better_50;

        return R.color.best_50;
    }

    /**
     * Get the button color resources depending on a certain score
     *
     * @return the color resource id
     */
    public int getDarkTaskColor() {
        if (this.value < -20)
            return R.color.worst_10;
        if (this.value < -10)
            return R.color.worse_10;
        if (this.value < -1)
            return R.color.bad_10;
        if (this.value < 1)
            return R.color.neutral_10;
        if (this.value < 5)
            return R.color.good_10;
        if (this.value < 10)
            return R.color.better_10;

        return R.color.best_10;
    }

    public Boolean isDue(int offset) {
        if (this.getCompleted()) {
            return true;
        }

        Calendar today = new GregorianCalendar();
        today.add(Calendar.HOUR, -offset);

        Calendar startDate = new GregorianCalendar();
        Calendar startDateAtMidnight;
        if (this.getStartDate() != null) {
            startDate.setTime(this.getStartDate());
            startDateAtMidnight = new GregorianCalendar(startDate.get(Calendar.YEAR),
                    startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH));

            if (startDateAtMidnight.after(today)) {
                return false;
            }
        } else {
            startDateAtMidnight = new GregorianCalendar(startDate.get(Calendar.YEAR),
                    startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH));
        }

        if (this.getFrequency().equals(FREQUENCY_DAILY)) {
            if (getEveryX() == 0) {
                return false;
            }

            TimeUnit timeUnit = TimeUnit.DAYS;
            long diffInMillies = startDateAtMidnight.getTimeInMillis() - today.getTimeInMillis();
            long daySinceStart = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
            return (daySinceStart % this.getEveryX() == 0);
        } else {
            return this.getRepeat().getForDay(today.get(Calendar.DAY_OF_WEEK));
        }
    }

    public Boolean isDisplayedActive(int offset) {
        return this.isDue(offset) && !this.completed;
    }

    public Boolean isChecklistDisplayActive(int offset) {
        return this.isDisplayedActive(offset) && (this.checklist.size() != this.getCompletedChecklistCount());
    }

    public Date getNextReminderOccurence(Date oldTime) {
        Calendar today = Calendar.getInstance();

        Calendar newTime = new GregorianCalendar();
        newTime.setTime(oldTime);
        newTime.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        if (today.before(newTime)) {
            today.add(Calendar.DAY_OF_MONTH, -1);
        }

        if (this.getFrequency().equals(FREQUENCY_DAILY)) {
            Calendar startDate = new GregorianCalendar();
            startDate.setTime(this.getStartDate());

            TimeUnit timeUnit = TimeUnit.DAYS;
            long diffInMillies = today.getTimeInMillis() - startDate.getTimeInMillis();
            long daySinceStart = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long daysUntilNextReminder = this.getEveryX() - (daySinceStart % this.getEveryX());

            today.add(Calendar.DATE, (int) daysUntilNextReminder);
            newTime.setTime(today.getTime());
        } else {
            int nextActiveDayOfTheWeek = newTime.get(Calendar.DAY_OF_WEEK);
            while (!this.getRepeat().getForDay(nextActiveDayOfTheWeek) || newTime.before(today) || newTime.equals(today)) {
                if (nextActiveDayOfTheWeek == 6) nextActiveDayOfTheWeek = 0;
                nextActiveDayOfTheWeek += 1;
                newTime.add(Calendar.DATE, 1);
            }
        }

        return newTime.getTime();
    }

    public boolean isGroupTask() {
        if (group != null) {
            if (group.approvalRequired) {
                return true;
            }
        }
        return false;
    }

    public boolean isPendingApproval() {
        if (group != null) {
            if (group.approvalRequired && group.approvalRequested && !group.approvalApproved) {
                return true;
            }
        }
        return false;
    }

    public void parseMarkdown() {
        try {
            this.parsedText = MarkdownParser.parseMarkdown(this.getText());
        } catch (NullPointerException e) {
            this.parsedText = this.getText();
        }
        try {
            this.parsedNotes = MarkdownParser.parseMarkdown(this.getNotes());
        } catch (NullPointerException e) {
            this.parsedNotes = this.getNotes();
        }
    }
}
