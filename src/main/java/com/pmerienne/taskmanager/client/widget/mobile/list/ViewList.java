package com.pmerienne.taskmanager.client.widget.mobile.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import com.pmerienne.taskmanager.client.widget.mobile.list.ViewList.Item;

public class ViewList extends CellListWithHeader<Item> implements HasCellSelectedHandler {

	private final static ItemCell ITEM_CELL = new ItemCell();

	private List<Item> items = new ArrayList<Item>();

	public ViewList() {
		super(ITEM_CELL);
		this.getCellList().setRound(true);
		this.setItems(Arrays.asList(Item.values()));
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
		this.getCellList().render(items);
	}

	public Item getItem(int index) {
		return this.items.get(index);
	}

	private static class ItemCell extends BasicCell<Item> {
		@Override
		public String getDisplayString(Item model) {
			return model.getName();
		}
	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}

	public static enum Item {

		PROJECTS("Projets"), TASKS("Mes tâches"), OPTIONS("Options");

		private String name;

		Item(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
}
