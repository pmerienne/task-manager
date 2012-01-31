package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.pmerienne.taskmanager.client.place.mobile.EditTaskPlace;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskDetailViewImpl extends Composite implements TaskDetailView {

	private static TaskDetailViewImplUiBinder uiBinder = GWT.create(TaskDetailViewImplUiBinder.class);

	interface TaskDetailViewImplUiBinder extends UiBinder<Widget, TaskDetailViewImpl> {
	}

	@UiField
	HeaderButton back;

	@UiField
	HTML title;

	@UiField
	Label informations;

	@UiField
	Label description;

	@UiField
	Label project;

	@UiField
	Button changeStatus;

	private Task task;

	private Presenter presenter;

	public TaskDetailViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("edit")
	protected void onEditTaped(TapEvent event) {
		this.presenter.goTo(new EditTaskPlace(this.task.getId()));
	}

	@UiHandler("changeStatus")
	protected void onChangeStatusTapped(TapEvent event) {
		switch (task.getStatus()) {
		case TODO:
			this.task.setStatus(TaskStatus.DOING);
			this.presenter.save(this.task);
			break;
		case DOING:
			this.task.setStatus(TaskStatus.DONE);
			this.presenter.save(this.task);
			break;
		case DONE:
			this.task.setStatus(TaskStatus.ARCHIVED);
			this.presenter.save(this.task);
			break;
		case ARCHIVED:
			this.presenter.delete(this.task);
			break;
		default:
			break;
		}
	}

	@UiHandler("delete")
	protected void onDeleteTapped(TapEvent event) {
		this.presenter.delete(this.task);
	}

	@Override
	public void setTask(Task task) {
		this.task = task;
		this.title.setText(task.getName());
		this.informations.setText(this.getInformation(task));
		this.description.setText(task.getDescription());
		if (task.getProject() != null) {
			this.project.setText("Cette tâche appartient au projet " + task.getProject().getName());
		} else {
			this.project.setText("");
		}
		this.changeStatus.setVisible(true);
		switch (task.getStatus()) {
		case TODO:
			this.changeStatus.setText("Commencer");
			break;
		case DOING:
			this.changeStatus.setText("Finir");
			break;
		case DONE:
			this.changeStatus.setText("Archiver");
			break;
		case ARCHIVED:
			this.changeStatus.setText("Supprimer");
			this.changeStatus.setVisible(false);
			break;
		default:
			break;
		}
	}

	private String getInformation(Task task) {
		StringBuilder sb = new StringBuilder();
		sb.append("Tâche ");
		switch (task.getStatus()) {
		case TODO:
			sb.append("à faire");
			break;
		case DOING:
			sb.append(" en cours");
			break;
		case DONE:
			sb.append("finie");
			break;
		case ARCHIVED:
			sb.append("archivée");
			break;
		default:
			break;
		}
		sb.append(" (créée le ");
		sb.append(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(task.getCreationDate()));
		sb.append(")");
		return sb.toString();

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
