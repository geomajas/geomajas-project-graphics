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
package org.geomajas.graphics.client.service;

import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * {@link RenderContainer} wrapper that can stop event propagation and act as handler registrar.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CapturingRenderContainer implements RenderContainer {

	private RenderContainer container;

	private boolean stopPropagation;

	public CapturingRenderContainer(RenderContainer container, boolean stopPropagation) {
		this.container = container;
		this.stopPropagation = stopPropagation;
		StopPropagation s = new StopPropagation();
		addMouseDownHandler(s);
		addMouseUpHandler(s);
		addMouseWheelHandler(s);
		addMouseMoveHandler(s);
		addMouseOverHandler(s);
		addMouseOutHandler(s);
		addClickHandler(s);
		addDoubleClickHandler(s);
	}

	public boolean isStopPropagation() {
		return stopPropagation;
	}

	public void setStopPropagation(boolean stopPropagation) {
		this.stopPropagation = stopPropagation;
	}

	public RenderContainer getParent() {
		return container.getParent();
	}

	public void removeFromParent() {
		container.removeFromParent();
	}

	public boolean remove(Renderable renderable) {
		return container.remove(renderable);
	}

	public boolean remove(IsRenderable renderable) {
		return container.remove(renderable);
	}

	public void bringToFront(Renderable renderable) {
		container.bringToFront(renderable);
	}

	public void bringToFront(IsRenderable renderable) {
		container.bringToFront(renderable);
	}

	public void insert(Renderable renderable, int index) {
		container.insert(renderable, index);
	}

	public void insert(IsRenderable renderable, int index) {
		container.insert(renderable, index);
	}

	public int indexOf(Renderable renderable) {
		return container.indexOf(renderable);
	}

	public int indexOf(IsRenderable renderable) {
		return container.indexOf(renderable);
	}

	public void add(Renderable renderable) {
		container.add(renderable);
	}

	public void add(IsRenderable renderable) {
		container.add(renderable);
	}

	public void clear() {
		container.clear();
	}

	public boolean isEmpty() {
		return container.isEmpty();
	}

	public void setCursor(String css) {
		container.setCursor(css);
	}

	public void capture() {
		container.capture();
	}

	public void releaseCapture() {
		container.releaseCapture();
	}

	public void setOpacity(double opacity) {
		container.setOpacity(opacity);
	}

	public void setVisible(boolean visible) {
		container.setVisible(visible);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return container.addMouseDownHandler(handler);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		container.fireEvent(event);
	}

	public boolean isSourceOf(GwtEvent<?> event) {
		return container.isSourceOf(event);
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return container.addClickHandler(handler);
	}

	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return container.addMouseUpHandler(handler);
	}

	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return container.addMouseOutHandler(handler);
	}

	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return container.addMouseMoveHandler(handler);
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return container.addMouseOverHandler(handler);
	}

	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return container.addMouseWheelHandler(handler);
	}

	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return container.addDoubleClickHandler(handler);
	}

	public <H extends EventHandler> HandlerRegistration addDomHandler(H handler, Type<H> type) {
		return container.addDomHandler(handler, type);
	}

	/**
	 * Stops event propagation.
	 * 
	 * @author Jan De Moerloose
	 * 
	 */
	class StopPropagation implements MouseDownHandler, MouseUpHandler, MouseWheelHandler, MouseMoveHandler,
			MouseOutHandler, MouseOverHandler, ClickHandler, DoubleClickHandler {

		@Override
		public void onDoubleClick(DoubleClickEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onClick(ClickEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseOver(MouseOverEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseOut(MouseOutEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseMove(MouseMoveEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseWheel(MouseWheelEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			checkPropagation(event);
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			checkPropagation(event);
		}

		private void checkPropagation(MouseEvent<?> event) {
			if (stopPropagation) {
				event.stopPropagation();
			}
		}

	}

}
