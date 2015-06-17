/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2015 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the Apache
 * License, Version 2.0. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.graphics.client.editor;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.operation.MarkerPositionOperation;
import org.geomajas.graphics.client.operation.MarkerStyleOperation;
import org.geomajas.graphics.client.resource.GraphicsResource;
import org.geomajas.graphics.client.service.GraphicsService;
import org.geomajas.graphics.client.util.textbox.ColorTextBoxValidator;
import org.geomajas.graphics.client.util.textbox.DoubleTextBoxValidator;
import org.geomajas.graphics.client.util.textbox.IntegerTextBoxValidator;
import org.geomajas.graphics.client.widget.TransparencySliderBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.mogaleaf.client.common.widgets.ColorHandler;
import com.mogaleaf.client.common.widgets.SimpleColorPicker;

/**
 * {@link org.geomajas.graphics.client.editor.Editor} for the
 * {@link org.geomajas.graphics.client.object.role.HasMarker} role.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class AnchoredEditor implements Editor {

	private static final Binder UIBINDER = GWT.create(Binder.class);

	/**
	 * UI binder.
	 *
	 */
	interface Binder extends UiBinder<HTMLPanel, AnchoredEditor> {

	}

	private GraphicsService service;

	private HTMLPanel widget;

	@UiField
	protected Label strokeLabel;

	@UiField
	protected IntegerTextBoxValidator strokeWidthBox;

	@UiField
	protected Button strokeColorButton;

	@UiField
	protected ColorTextBoxValidator strokeColorBox;

	@UiField
	protected TransparencySliderBar strokeOpacitySlider;

	@UiField
	protected DoubleTextBoxValidator pointPositionX;

	@UiField
	protected DoubleTextBoxValidator pointPositionY;

	@UiField
	protected Label pointLabel;

	@UiField
	protected Button pointColorButton;

	@UiField
	protected ColorTextBoxValidator pointColorBox;

	@UiField
	protected TransparencySliderBar pointOpacitySlider;

	private GraphicsObject object;

	private String iconUrl;

	private SimpleColorPicker colorPicker;

	public void setService(GraphicsService service) {
		this.service = service;
	}

	public AnchoredEditor() {
		widget = UIBINDER.createAndBindUi(this);
		widget.setStyleName("anchorPointPopup");
		widget.setStyleName("popupWindow", true);
		pointPositionX.setStyleName("anchorPopupPositionTextBox");
		pointPositionY.setStyleName("anchorPopupPositionTextBox");
//		strokeWidthBox.setStyleName("textBoxCell",true);
//		pointColorBox.setStyleName("textBoxCell",true);
	}

	 @UiHandler("strokeColorButton")
	 public void showStrokeColorChoice(ClickEvent e) {
		 colorPicker = new SimpleColorPicker();
		 colorPicker.addListner(new ColorHandler() {
			 @Override
			 public void newColorSelected(String color) {
				 strokeColorBox.setLabel(color);
			 }
		 });
		 int left = widget.getAbsoluteLeft() + widget.getOffsetWidth();
		 int top = widget.getAbsoluteTop();

		 colorPicker.setPopupPosition(left, top);
		 colorPicker.show();
	 }

	@UiHandler("pointColorButton")
	public void showpointColorChoice(ClickEvent e) {
		colorPicker = new SimpleColorPicker();
		colorPicker.addListner(new ColorHandler() {

			@Override
			public void newColorSelected(String color) {
				pointColorBox.setLabel(color);
			}
		});
		int left = widget.getAbsoluteLeft() + widget.getOffsetWidth();
		int top = widget.getAbsoluteTop() + widget.getOffsetHeight() / 2;
		colorPicker.setPopupPosition(left, top);
		colorPicker.show();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public boolean supports(GraphicsObject object) {
		return object.hasRole(HasMarker.TYPE) ;
	}

	@Override
	public void setObject(GraphicsObject object) {
		this.object = object;
		//line
		strokeWidthBox.setLabel(getAnchorLineStrokable().getStrokeWidth() + "");
		strokeColorBox.setLabel(getAnchorLineStrokable().getStrokeColor());
		strokeOpacitySlider.setCurrentValue(1 - getAnchorLineStrokable().getStrokeOpacity());
		strokeLabel.setText(GraphicsResource.MESSAGES.editorLabelAnchorStrokeDefault());

		//point style
		pointColorBox.setLabel(getAnchored().getMarker().getStrokeColor());
		pointOpacitySlider.setCurrentValue(1 - getAnchored().getMarker().getStrokeOpacity());
		pointLabel.setText(GraphicsResource.MESSAGES.editorLabelAnchorPointDefault());
	
		//point coordinates
		pointPositionX.setLabel(getAnchored().getMarker().getUserPosition().getX() + "");
		pointPositionY.setLabel(getAnchored().getMarker().getUserPosition().getY() + "");
		
	}

	@Override
	public void onOk() {
		int beforeStrokeWidth = getAnchorLineStrokable().getStrokeWidth();
		String beforeStrokeColor = getAnchorLineStrokable().getStrokeColor();
		double beforeStrokeOpacity = getAnchorLineStrokable().getStrokeOpacity();
		String beforePointColor = getAnchored().getMarker().getStrokeColor();
		double beforePointOpacity = getAnchored().getMarker().getStrokeOpacity();
		service.execute(new MarkerStyleOperation(object, beforeStrokeWidth,
				beforeStrokeColor, beforeStrokeOpacity, beforePointColor, 
					beforePointOpacity, strokeWidthBox.getInteger(),
						strokeColorBox.getLabel(), 1 - strokeOpacitySlider
						.getCurrentValue(), pointColorBox.getLabel(), 1 - pointOpacitySlider
						.getCurrentValue()));

		//location
		Coordinate beforePosition  = getAnchored().getMarker().getUserPosition();
		service.execute(new MarkerPositionOperation(object, beforePosition, new Coordinate(
				pointPositionX.getDouble(), pointPositionY.getDouble())));

	}

	@Override
	public String getLabel() {
		return GraphicsResource.MESSAGES.editorLabelResizableAnchorStyle();
	}

	@Override
	public boolean validate() {
		boolean valid = true;
		// validate all text boxes individually. This will result in 
		// display of error message of each individual text box.
		if (!strokeColorBox.isValid()) {
			valid = false;
		}
		if (!pointColorBox.isValid()) {
			valid = false;
		}
		if (!strokeWidthBox.isValid()) {
			valid = false;
		}
		if (!pointPositionX.isValid()) {
			valid = false;
		}
		if (!pointPositionY.isValid()) {
		}
		return valid;
	}

	@Override
	public void undo() {
		service.undo();
	}

	@Override
	public void setIconUrl(String url) {
		this.iconUrl = url;
	}

	@Override
	public String getIconUrl() {
		return iconUrl;
	}

	private Strokable getAnchorLineStrokable() {
		return getAnchored().getMarkerLineStrokable();
	}

	private HasMarker getAnchored() {
		return object.getRole(HasMarker.TYPE);
	}
}
