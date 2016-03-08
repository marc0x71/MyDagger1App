package com.marc0x71.mydagger1app.presenter;

import android.view.View;

import com.marc0x71.mydagger1app.mock.component.MockTestModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.ObjectGraph;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by marc0x71 on 08/03/2016.
 */
public class BasePresenterTest {

    @Mock
    View view;

    MyPresenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new MyPresenter();
        ObjectGraph objectGraph = ObjectGraph.create(new MockTestModule());
        objectGraph.inject(presenter);
        presenter.onViewAttached(view);
    }

    @Test
    public void normalBehaviour() {
        assertEquals(presenter.getView(), view);
        presenter.onViewDetached();
        assertNull(presenter.getView());
        presenter.onViewAttached(view);
        assertEquals(presenter.getView(), view);

        // reset flag
        presenter.setTaskExecuted(false);
        // start task
        presenter.addPendingTask();
        // now the task must be executed
        assertTrue(presenter.isTaskExecuted());
    }

    @Test
	public void whenViewAvailable() {
		presenter.onViewDetached();
        // reset flag
        presenter.setTaskExecuted(false);

        // start task
        presenter.addPendingTask();

        // check no task already executed
        assertFalse(presenter.isTaskExecuted());

        // attach the view
        presenter.onViewAttached(view);
        assertEquals(presenter.getView(), view);

        // now the task must be executed
        assertTrue(presenter.isTaskExecuted());
    }

	@Test
	public void ifViewAvailable() {
		presenter.onViewDetached();
		// reset flag
		presenter.setTaskExecuted(false);

		// start task
		presenter.executeTask();

		// check no task already executed
		assertFalse(presenter.isTaskExecuted());

		// attach the view
		presenter.onViewAttached(view);
		assertEquals(presenter.getView(), view);

		// now the task must be executed
		assertFalse(presenter.isTaskExecuted());
	}

    public class MyPresenter extends BasePresenter {

        boolean taskExecuted;

        public boolean isTaskExecuted() {
            return taskExecuted;
        }

        public void setTaskExecuted(boolean taskExecuted) {
            this.taskExecuted = taskExecuted;
        }

        @Override
        public void onDestroyed() {

        }

        public void addPendingTask() {
			whenViewAvailable(new UITask() {
				@Override
                public void updateUI() {
                    taskExecuted = true;
                }
            });
        }

		public void executeTask() {
			ifViewAvailable(new UITask() {
				@Override
				public void updateUI() {
					taskExecuted = true;
				}
			});
		}
	}

}
