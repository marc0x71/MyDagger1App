package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.provider.IResourceProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    @Inject
    IResourceProvider resourceProvider;

    @Inject
    @Named("observer")
    rx.Scheduler observerScheduler;

    @Inject
    @Named("publisher")
    rx.Scheduler publisherScheduler;

	List<UITask> pendingTasks = new ArrayList<>();
	private V view;

    @Override
    public void onViewAttached(V view) {
        this.view = view;
        checkPending();
    }

	/**
	 * Execute the task when the View is available
	 *
	 * @param task Task to execute on View. Use getView() to retrieve the attached view
	 */
	protected void whenViewAvailable(UITask task) {
		if (view != null) {
            task.updateUI();
        } else {
            Timber.d("add to pending task...");
            pendingTasks.add(task);
		}
	}

	/**
	 * Execute the task only if the View is available
	 *
	 * @param task Task to execute on View. Use getView() to retrieve the attached view
	 */
	protected void ifViewAvailable(UITask task) {
		if (view != null) {
			task.updateUI();
		}
	}

    private void checkPending() {
		if (pendingTasks.isEmpty()) return;
		Timber.d("check pending...");
		for (int i = 0; i < pendingTasks.size(); i++) {
			pendingTasks.get(i).updateUI();
		}
    }

    @Override
    public void onViewDetached() {
		view = null;
	}

	@Override
	public void onDestroyed() {
		pendingTasks.clear();
	}

	protected V getView() {
		return view;
	}

	protected interface UITask {
		void updateUI();
    }
}
