package com.marc0x71.mydagger1app.presenter;

import com.marc0x71.mydagger1app.R;
import com.marc0x71.mydagger1app.contract.LoginContract;
import com.marc0x71.mydagger1app.component.MockTestModule;
import com.marc0x71.mydagger1app.provider.IResourceProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.ObjectGraph;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by marc0x71 on 03/03/2016.
 */
public class LoginPresenterTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO_MESSAGE = "ok";
    private static final String ERROR_MESSAGE = "fail";

    @Mock
    LoginContract.View view;

    LoginContract.Model model;
    IResourceProvider resourceProvider;
    LoginPresenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view);
        ObjectGraph objectGraph = ObjectGraph.create(new MockTestModule());
        objectGraph.inject(presenter);
        model = presenter.model;
        resourceProvider = presenter.resourceProvider;
        presenter.dump();
    }

    @Test
    public void notNull() {
        assertNotNull(presenter);
        assertNotNull(presenter.model);
        assertNotNull(presenter.view);
        assertNotNull(presenter.resourceProvider);
    }

    @Test
    public void loginSuccess() {
        when(resourceProvider.getString(R.string.login_success_message)).thenReturn(INFO_MESSAGE);
        when(model.validate(eq(USERNAME), eq(PASSWORD))).thenReturn(true);
        presenter.userLogin(USERNAME, PASSWORD);
        verify(view).showInfoMessage(eq(INFO_MESSAGE));
        verify(view).goToMainActivity();
    }

    @Test
    public void loginFailure() {
        when(resourceProvider.getString(R.string.login_failure_message)).thenReturn(ERROR_MESSAGE);
        when(model.validate(eq(USERNAME), eq(PASSWORD))).thenReturn(false);
        presenter.userLogin(USERNAME, PASSWORD);
        verify(view, never()).showInfoMessage(eq(INFO_MESSAGE));
        verify(view, never()).goToMainActivity();
        verify(view).showErrorMessage(eq(ERROR_MESSAGE));
    }

}
