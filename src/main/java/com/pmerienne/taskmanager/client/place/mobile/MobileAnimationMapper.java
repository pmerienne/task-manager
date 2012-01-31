package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class MobileAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace == null) {
			return Animation.FADE;
		}
		// Home / project list
		if (oldPlace instanceof HomePlace && newPlace instanceof ProjectListPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof ProjectListPlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}
		// project list / edit project
		if (oldPlace instanceof ProjectListPlace && newPlace instanceof EditProjectPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof EditProjectPlace && newPlace instanceof ProjectListPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// project list / project detail
		if (oldPlace instanceof ProjectListPlace && newPlace instanceof ProjectDetailPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof ProjectDetailPlace && newPlace instanceof ProjectListPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// project detail / edit project
		if (oldPlace instanceof ProjectDetailPlace && newPlace instanceof EditProjectPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof EditProjectPlace && newPlace instanceof ProjectDetailPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// project detail / task status
		if (oldPlace instanceof ProjectDetailPlace && newPlace instanceof TaskStatusPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof TaskStatusPlace && newPlace instanceof ProjectDetailPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Home / task status
		if (oldPlace instanceof HomePlace && newPlace instanceof TaskStatusPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof TaskStatusPlace && newPlace instanceof HomePlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Task status / Task list
		if (oldPlace instanceof TaskStatusPlace && newPlace instanceof TaskListPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof TaskListPlace && newPlace instanceof TaskStatusPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Task detail / Task list
		if (oldPlace instanceof TaskListPlace && newPlace instanceof TaskDetailPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof TaskDetailPlace && newPlace instanceof TaskListPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Task detail / Edit task
		if (oldPlace instanceof TaskDetailPlace && newPlace instanceof EditTaskPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof EditTaskPlace && newPlace instanceof TaskDetailPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Edit task / Task list
		if (oldPlace instanceof TaskListPlace && newPlace instanceof EditTaskPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof EditTaskPlace && newPlace instanceof TaskListPlace) {
			return Animation.SLIDE_REVERSE;
		}
		// Task status / Edit task
		if (oldPlace instanceof TaskStatusPlace && newPlace instanceof EditTaskPlace) {
			return Animation.SLIDE;
		}
		if (oldPlace instanceof EditTaskPlace && newPlace instanceof TaskStatusPlace) {
			return Animation.SLIDE_REVERSE;
		}

		return Animation.FADE;
	}

}
