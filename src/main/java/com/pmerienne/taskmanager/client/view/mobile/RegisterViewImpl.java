package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.pmerienne.taskmanager.shared.model.User;

public class RegisterViewImpl extends Composite implements RegisterView {

	private static RegisterViewImplUiBinder uiBinder = GWT.create(RegisterViewImplUiBinder.class);

	interface RegisterViewImplUiBinder extends UiBinder<Widget, RegisterViewImpl> {}

	private Presenter presenter;

	@UiField
	HeaderButton back;
	
	@UiField
	MTextBox login;

	@UiField
	MPasswordTextBox password;

	@UiField
	MPasswordTextBox passwordAgain;
	
	@UiField
	Button register;
	
	private boolean pending =  false;
	
	public RegisterViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("register")
	protected void onRegisterTaped(TapEvent event) {
		this.register();
	}

	private void register() {
		if(!this.pending) {
			if (this.login.getValue().isEmpty()) {
				Dialogs.alert("Erreur", "Veuillez renseigner votre login", null);
			} else if (this.password.getValue().isEmpty()) {
				Dialogs.alert("Erreur", "Veuillez renseigner votre mot de passe", null);
			} else if (!this.password.getValue().equals(this.passwordAgain.getValue())) {
				Dialogs.confirm("Erreur", "Les mots de passe ne sont pas les même", null);
				this.password.setValue("");
				this.passwordAgain.setValue("");
			} else {
				this.presenter.save(new User(this.login.getValue(), this.password.getValue()));
			}
		}
	}
	
	@Override
	public void clear() {
		this.login.setValue("");
		this.password.setValue("");
		this.passwordAgain.setValue("");
		this.setPending(false);
	}
	
	@Override
	public void setPending(boolean pending) {
		this.pending = pending;
		if(pending) {
			this.register.setText("Inscription en cours ...");
		} else {
			this.register.setText("S'inscrire");
		}
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
