package com.pmerienne.taskmanager.shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {

	private static final long serialVersionUID = 6971606516945765287L;

	private String id;

	private String name;

	private String description;

	private Date creationDate = new Date();

	private List<User> users = new ArrayList<User>();

	private Project project;

	private TaskStatus status = TaskStatus.TODO;

	public Task() {
		this.creationDate = new Date();
	}

	public Task(String id, TaskStatus status, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Task(TaskStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", creationDate=" + creationDate
				+ ", project=" + project + ", status=" + status + "]";
	}

}
