package com.habitrpg.android.habitica.ui.adapter.tasks;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.habitrpg.android.habitica.HabiticaBaseApplication;
import com.habitrpg.android.habitica.components.AppComponent;
import com.habitrpg.android.habitica.helpers.TaskFilterHelper;
import com.habitrpg.android.habitica.models.tasks.Task;
import com.habitrpg.android.habitica.ui.viewHolders.tasks.DailyViewHolder;

import io.realm.OrderedRealmCollection;

public class DailiesRecyclerViewHolder extends RealmBaseTasksRecyclerViewAdapter<DailyViewHolder> {

    public int dailyResetOffset;

    public DailiesRecyclerViewHolder(@Nullable OrderedRealmCollection<Task> data, boolean autoUpdate, int layoutResource, int dailyResetOffset) {
        super(data, autoUpdate, layoutResource);
        this.dailyResetOffset = dailyResetOffset;
    }

    @Override
    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(getContentView(parent), dailyResetOffset);
    }
}
