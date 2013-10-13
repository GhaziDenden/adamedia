package com.gdconsulting.adamedia.model;

public class MenuItem {

	private String label;
	private boolean section;
	private int barColor;
	private boolean selectable;
	
	public MenuItem(String label, boolean section, int barColor, boolean selectable) {
		super();
		this.label = label;
		this.section = section;
		this.barColor = barColor;
		this.selectable = selectable;
	}

	public boolean isSelectable() {
		return selectable;
	}
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isSection() {
		return section;
	}
	public void setSection(boolean section) {
		this.section = section;
	}
	public int getBarColor() {
		return barColor;
	}
	public void setBarColor(int barColor) {
		this.barColor = barColor;
	}
}
