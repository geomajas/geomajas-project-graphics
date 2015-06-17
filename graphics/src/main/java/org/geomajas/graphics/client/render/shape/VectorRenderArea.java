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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderArea;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;

import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Wraps {@link DrawingArea} as {@link RenderArea}. The initial transformation is set to non-scaled y-axis inversion,
 * meaning that the lower-left corner is (0,0).
 * 
 * @author Jan De Moerloose
 *
 */
public class VectorRenderArea implements RenderArea {

	private DrawingArea drawingArea;

	private RenderContainer rootContainer;

	private TransformingGroup rootGroup;

	public VectorRenderArea(int width, int height) {
		drawingArea = new DrawingArea(width, height);
		rootGroup = new TransformingGroup();
		setScale(1, -1);
		setTranslation(0, height);
		drawingArea.add(rootGroup);
		rootContainer = new VectorRenderContainer(rootGroup);
	}

	@Override
	public void setTranslation(double deltaX, double deltaY) {
		rootGroup.setTranslation(deltaX, deltaY);
	}

	@Override
	public void setScale(double scaleX, double scaleY) {
		rootGroup.setScale(scaleX, scaleY);
	}

	@Override
	public double getDeltaX() {
		return rootGroup.getDeltaX();
	}

	@Override
	public double getDeltaY() {
		return rootGroup.getDeltaY();
	}

	@Override
	public double getScaleX() {
		return rootGroup.getScaleX();
	}

	@Override
	public double getScaleY() {
		return rootGroup.getScaleY();
	}

	public DrawingArea getDrawingArea() {
		return drawingArea;
	}

	public RenderContainer getRootContainer() {
		return rootContainer;
	}

	@Override
	public int getWidth() {
		return drawingArea.getWidth();
	}

	@Override
	public int getHeight() {
		return drawingArea.getHeight();
	}

	@Override
	public Coordinate getScreenCoordinate(HumanInputEvent<?> event) {
		if (event instanceof MouseEvent<?>) {
			MouseEvent<?> m = (MouseEvent<?>) event;
			return new Coordinate(m.getRelativeX(drawingArea.getElement()), m.getRelativeY(drawingArea.getElement()));
		} else if (event instanceof TouchEvent<?>) {
			Touch touch = null;
			if (event instanceof TouchEndEvent) {
				touch = ((TouchEvent<?>) event).getChangedTouches().get(0);
			} else {
				touch = ((TouchEvent<?>) event).getTouches().get(0);
			}
			double offsetX = touch.getRelativeX(drawingArea.getElement());
			double offsetY = touch.getRelativeY(drawingArea.getElement());
			return new Coordinate(offsetX, offsetY);
		} else {
			throw new IllegalArgumentException("Cannot get screen coordinate from event class " + event.getClass());
		}
	}

	@Override
	public Coordinate transform(Coordinate coordinate, RenderSpace from, RenderSpace to) {
		if (from == to) {
			return (Coordinate) coordinate.clone();
		}
		double x = coordinate.getX();
		double y = coordinate.getY();
		switch (to) {
			case SCREEN:
				double xs = x * rootGroup.getScaleX() + rootGroup.getDeltaX();
				double ys = y * rootGroup.getScaleY() + rootGroup.getDeltaY();
				return new Coordinate(xs, ys);
			case USER:
			default:
				double xu = (x - rootGroup.getDeltaX()) / rootGroup.getScaleX();
				double yu = (y - rootGroup.getDeltaY()) / rootGroup.getScaleY();
				return new Coordinate(xu, yu);
		}
	}

	@Override
	public void setCursor(String css) {
		drawingArea.getElement().getStyle().setProperty("cursor", css);
	}

	@Override
	public void capture() {
		DOM.setCapture(drawingArea.getElement());
	}

	@Override
	public void releaseCapture() {
		DOM.releaseCapture(drawingArea.getElement());
	}

	@Override
	public void setOpacity(double opacity) {
		drawingArea.getElement().getStyle().setOpacity(opacity);
	}

	@Override
	public void setVisible(boolean visible) {
		drawingArea.setVisible(visible);
	}

	@Override
	public boolean isSourceOf(GwtEvent<?> event) {
		return drawingArea == event.getSource();
	}

	@Override
	public <H extends EventHandler> HandlerRegistration addDomHandler(H handler, Type<H> type) {
		return drawingArea.addDomHandler(handler, type);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return drawingArea.addMouseDownHandler(handler);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		drawingArea.fireEvent(event);
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return drawingArea.addMouseUpHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return drawingArea.addMouseOutHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return drawingArea.addMouseOverHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return drawingArea.addMouseMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return drawingArea.addMouseWheelHandler(handler);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return drawingArea.addClickHandler(handler);
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return drawingArea.addDoubleClickHandler(handler);
	}

	@Override
	public Widget asWidget() {
		return drawingArea;
	}

	@Override
	public void clear() {
		drawingArea.clear();
	}

	@Override
	public boolean isEmpty() {
		return drawingArea.getVectorObjectCount() != 0;
	}

	@Override
	public void add(Renderable renderable) {
		drawingArea.add(((VectorRenderable) renderable).getObject());
	}

	@Override
	public void add(IsRenderable renderable) {
		add(renderable.getRenderable());
	}

	@Override
	public boolean remove(Renderable renderable) {
		return drawingArea.remove(((VectorRenderable) renderable).getObject()) != null;
	}

	@Override
	public boolean remove(IsRenderable renderable) {
		return remove(renderable.getRenderable());
	}

	@Override
	public void bringToFront(Renderable renderable) {
		drawingArea.bringToFront(((VectorRenderable) renderable).getObject());
	}

	@Override
	public void bringToFront(IsRenderable renderable) {
		bringToFront(renderable.getRenderable());
	}

	@Override
	public void insert(Renderable renderable, int index) {
		drawingArea.insert(((VectorRenderable) renderable).getObject(), index);
	}

	@Override
	public void insert(IsRenderable renderable, int index) {
		insert(renderable.getRenderable(), index);
	}

	@Override
	public int indexOf(Renderable renderable) {
		return drawingArea.indexOf(((VectorRenderable) renderable).getObject());
	}

	@Override
	public int indexOf(IsRenderable renderable) {
		return indexOf(renderable.getRenderable());
	}

	@Override
	public RenderContainer getParent() {
		return null;
	}

	@Override
	public void removeFromParent() {
	}

	/**
	 * Exposes getter for external transformation.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	class TransformingGroup extends Group {

		public double getDeltaX() {
			return super.getDeltaX();
		}

		public double getDeltaY() {
			return super.getDeltaY();
		}

		public double getScaleX() {
			return super.getScaleX();
		}

		public double getScaleY() {
			return super.getScaleY();
		}

	}

}
