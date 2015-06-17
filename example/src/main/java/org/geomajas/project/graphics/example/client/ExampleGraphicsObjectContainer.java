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
package org.geomajas.project.graphics.example.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.base.BaseCircleObject;
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.object.updateable.AnchoredBorderedText;
import org.geomajas.graphics.client.object.updateable.AnchoredIcon;
import org.geomajas.graphics.client.object.updateable.LabeledEllipse;
import org.geomajas.graphics.client.object.updateable.LabeledImage;
import org.geomajas.graphics.client.object.updateable.LabeledPath;
import org.geomajas.graphics.client.object.updateable.LabeledRectangle;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.render.shape.VectorRenderArea;
import org.geomajas.graphics.client.service.objectcontainer.GraphicsObjectContainerImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Sample extension of {@link GraphicsObjectContainerImpl}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class ExampleGraphicsObjectContainer extends GraphicsObjectContainerImpl
		implements IsWidget, NativePreviewHandler {

	private AbsolutePanel rootPanel;
	
	private VectorRenderArea canvas = new VectorRenderArea(1400, 500);

	public ExampleGraphicsObjectContainer(EventBus eventBus) {
		super(eventBus);
		rootPanel = new AbsolutePanel();
		rootPanel.setPixelSize(canvas.getWidth(), canvas.getHeight());
		canvas.getDrawingArea().getElement().setId("TestContainer");
		setRenderArea(canvas);
		setBackGround(rootPanel);
		setWidgetContainer(rootPanel);
		rootPanel.add(canvas);
		rootPanel.setStyleName("graphics-testContainer-rootPanel");
		Event.addNativePreviewHandler(this);

		// graphics objects
		LabeledRectangle rect = new LabeledRectangle(700, 250, 100, 100, "Rectangle label");
		BaseCircleObject circle = new BaseCircleObject(300, 100, 50);
		LabeledEllipse ellipse = new LabeledEllipse(100, 300, 50, 80, "Ellipse");
		LabeledImage image = new LabeledImage(200, 200, 200, 235,
				"http://tuxpaint.org/stamps/stamps/animals/birds/cartoon/tux.png", "Image");
		LabeledPath path = new LabeledPath(new Coordinate[] { new Coordinate(300, 300), new Coordinate(500, 400) },
				false, "Path");

		// anchored bordered text
		Coordinate textCoordinate = new Coordinate(100, 200);
		Coordinate textMarkerCoordinate = new Coordinate(textCoordinate);
		textMarkerCoordinate.setY(textMarkerCoordinate.getY() - 40);
		AnchoredBorderedText text = new AnchoredBorderedText(textCoordinate, "test", 10, textMarkerCoordinate, null);
		text.getRole(Textable.TYPE).setFontColor("blue");

		// anchored icon
		Coordinate anchoredIconCoordinate = new Coordinate(500, 80);
		Coordinate anchoredIconMarkerCoordinate = new Coordinate(anchoredIconCoordinate);
		anchoredIconMarkerCoordinate.setY(anchoredIconMarkerCoordinate.getY() + 40);
		AnchoredIcon anchoredIcon = new AnchoredIcon(anchoredIconCoordinate, 20, 20, urls.get(2),
				anchoredIconMarkerCoordinate, MarkerShape.CIRCLE);
		add(text);
		add(rect);
		add(circle);
		add(ellipse);
		add(image);
		add(path);
		add(anchoredIcon);
	}

	private List<String> urls = new ArrayList<String>(Arrays.asList(GWT.getModuleBaseURL() + "image/slider.gif",
			GWT.getModuleBaseURL() + "image/cloud.png",
			GWT.getModuleBaseURL() + "image/sun.jpg"));

	@Override
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		Element relatedEventTarget = event.getNativeEvent().getEventTarget().cast();
		if (relatedEventTarget != null && canvas.getDrawingArea().isAttached()) {
			if (DOM.isOrHasChild(canvas.getDrawingArea().getElement(), relatedEventTarget)) {
				event.getNativeEvent().preventDefault();
			}
		}
	}

	@Override
	public Widget asWidget() {
		return rootPanel;
	}

}
