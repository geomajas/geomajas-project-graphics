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
import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.GraphicsController;
import org.geomajas.graphics.client.render.RenderArea;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Controller for panning/zooming.
 * 
 * @author Jan De Moerloose
 * 
 */
public class NavigationController implements GraphicsController, MouseWheelHandler, MouseDownHandler, MouseUpHandler,
		MouseMoveHandler {

	private boolean active;

	private RenderArea renderArea;

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private GraphicsService service;

	private boolean dragging;

	private Coordinate dragOrigin;

	private double dx;

	private double dy;

	public NavigationController(GraphicsService service, RenderArea renderArea) {
		this.renderArea = renderArea;
		this.service = service;
	}

	@Override
	public void setActive(boolean active) {
		if (active != isActive()) {
			this.active = active;
			if (isActive()) {
				registrations.add(service.getObjectContainer().addMouseWheelHandler(this));
				registrations.add(service.getObjectContainer().addMouseDownHandler(this));
				registrations.add(service.getObjectContainer().addMouseUpHandler(this));
				registrations.add(service.getObjectContainer().addMouseMoveHandler(this));
			} else {
				for (HandlerRegistration registration : registrations) {
					registration.removeHandler();
				}
				service.getMetaController().setActive(true);
			}
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void onMouseWheel(MouseWheelEvent event) {
		Coordinate c0 = renderArea.getScreenCoordinate(event);
		double scaleFactor = event.isNorth() ? 2 : 0.5;
		double deltaX = renderArea.getDeltaX() + (1 - scaleFactor) * (c0.getX() - renderArea.getDeltaX());
		double deltaY = renderArea.getDeltaY() + (1 - scaleFactor) * (c0.getY() - renderArea.getDeltaY());
		renderArea.setScale(scaleFactor * renderArea.getScaleX(), scaleFactor * renderArea.getScaleY());
		renderArea.setTranslation(deltaX, deltaY);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		dragging = true;
		dragOrigin = renderArea.getScreenCoordinate(event);
		dx = renderArea.getDeltaX();
		dy = renderArea.getDeltaY();
		renderArea.setCursor("move");
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		if (dragging) {
			Coordinate end = renderArea.getScreenCoordinate(event);
			double newDx = dx + end.getX() - dragOrigin.getX();
			double newdy = dy + end.getY() - dragOrigin.getY();
			renderArea.setTranslation(newDx, newdy);
		}
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		renderArea.setCursor("default");
		dragging = false;
	}

}
