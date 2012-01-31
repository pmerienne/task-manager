package com.pmerienne.taskmanager.client.widget.mobile.chart;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class ProjectTaskChart extends Composite {

	private HTMLPanel mainPanel;
	private PieChart pie;

	public ProjectTaskChart() {
		this.mainPanel = new HTMLPanel("");
		initWidget(this.mainPanel);
	}

	public void setProjectStatistics(final ProjectStatistics stats) {
		this.mainPanel.clear();
		Runnable onVisualizationReady = new Runnable() {
			public void run() {
				// Create data
				final DataTable data = DataTable.create();
				data.addColumn(ColumnType.STRING, "Statut");
				data.addColumn(ColumnType.NUMBER, "Nombre de tâches");
				data.addRows(4);
				data.setValue(0, 0, "Tâches à faire");
				data.setValue(0, 1, stats.getTasksCount().get(TaskStatus.TODO));
				data.setValue(1, 0, "Tâches en cours");
				data.setValue(1, 1, stats.getTasksCount().get(TaskStatus.DOING));
				data.setValue(2, 0, "Tâches finies");
				data.setValue(2, 1, stats.getTasksCount().get(TaskStatus.DONE));
				data.setValue(3, 0, "Tâches archivées");
				data.setValue(3, 1, stats.getTasksCount().get(TaskStatus.ARCHIVED));

				// Create option
				final Options options = Options.create();
				options.setWidth(mainPanel.getOffsetWidth());
				options.setHeight(240);
				options.setEnableTooltip(true);
				options.set3D(true);
				options.setBackgroundColor("#E0E1E5");

				pie = new PieChart(data, options);
				mainPanel.add(pie);
			}
		};
		VisualizationUtils.loadVisualizationApi(onVisualizationReady, PieChart.PACKAGE);
	}
}
