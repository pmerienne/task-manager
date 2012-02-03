package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ TaskStatusPlace.Tokenizer.class, TaskListPlace.Tokenizer.class, TaskDetailPlace.Tokenizer.class,
		EditTaskPlace.Tokenizer.class, HomePlace.Tokenizer.class, ProjectListPlace.Tokenizer.class,
		EditProjectPlace.Tokenizer.class, ProjectDetailPlace.Tokenizer.class, RegisterPlace.Tokenizer.class })
public interface MobilePlaceHistoryMapper extends PlaceHistoryMapper {
}