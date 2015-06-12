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
package org.geomajas.graphics.client.service.objectcontainer;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.ActionType;
import org.geomajas.graphics.client.event.GraphicsObjectContainerEvent.Handler;
import org.geomajas.graphics.client.event.GraphicsObjectSelectedEvent;
import org.geomajas.graphics.client.event.GraphicsOperationEvent;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HtmlRenderable;
import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderArea;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.RenderSpace;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.service.CapturingRenderContainer;
import org.geomajas.graphics.client.service.CapturingWidget;
import org.geomajas.graphics.client.service.HasAllMouseAndClickHandlers;
import org.geomajas.graphics.client.util.BboxPosition;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HumanInputEvent;
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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

/**
 * 
 * Implementation of {@link GraphicsObjectContainer} that is backed by a root {@link RenderContainer}. The
 * implementation provides catch-all mouse event handlers by setting a background widget. All objects are added to a
 * sub-container of the root container that can be obtained from {@link #getObjectContainer()}.
 * 
 * @author Jan De Moerloose
 * 
 */
public class GraphicsObjectContainerImpl implements GraphicsObjectContainer {

	private CapturingRenderContainer rootContainer;

	private CapturingWidget backGround;

	private RenderContainer objectContainer = Graphics.getRenderElementFactory().createRenderContainer();

	private EventBus eventBus;

	private List<GraphicsObject> objects = new ArrayList<GraphicsObject>();

	private HasWidgets.ForIsWidget widgetContainer;

	private RenderArea renderArea;

	public GraphicsObjectContainerImpl(EventBus eventBus) {
		this(eventBus, null, null);
	}

	public GraphicsObjectContainerImpl(EventBus eventBus, RenderArea area,
			Widget backGroundWidget) {
		this.eventBus = eventBus;
		setRenderArea(area);
		setBackGround(backGroundWidget);
	}

	public void setBackGround(Widget backGroundWidget) {
		if (backGroundWidget != null) {
			backGround = new CapturingWidget(backGroundWidget, true);
		}
	}

	@Override
	public void setRenderArea(RenderArea renderArea) {
		if (renderArea != null) {
			this.rootContainer = new CapturingRenderContainer(renderArea.getRootContainer(), true);
			rootContainer.add(objectContainer);
		}
		this.renderArea = renderArea;
	}
	
	@Override
	public RenderArea getRenderArea() {
		return renderArea;
	}

	public void setWidgetContainer(HasWidgets.ForIsWidget widgetContainer) {
		this.widgetContainer = widgetContainer;
	}

	@Override
	public HasAllMouseAndClickHandlers getObjectContainer() {
		return objectContainer;
	}

	@Override
	public void add(Renderable renderable) {
		objectContainer.add(renderable);
	}

	@Override
	public void add(IsRenderable renderable) {
		objectContainer.add(renderable);
	}

	@Override
	public CapturingWidget getBackGround() {
		return backGround;
	}

	@Override
	public boolean isObject(MouseEvent<?> event) {
		for (GraphicsObject object : objects) {
			if (object.getRenderable().isSourceOf(event)) {
				return true;
			} else if (object.hasRole(HtmlRenderable.TYPE)) {
				if (object.getRole(HtmlRenderable.TYPE).asWidget() == event.getSource()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public GraphicsObject getObject(MouseEvent<?> event) {
		for (GraphicsObject object : objects) {
			if (object.getRenderable().isSourceOf(event)) {
				return object;
			} else if (object.hasRole(HtmlRenderable.TYPE)) {
				if (object.getRole(HtmlRenderable.TYPE).asWidget() == event.getSource()) {
					return object;
				}
			}
		}
		return null;
	}

	@Override
	public boolean isBackGround(MouseEvent<?> event) {
		return backGround.asWidget() == event.getSource();
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		backGround.fireEvent(event);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
		return registerRootAndBackGround(handler, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return registerRootAndBackGround(handler, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return registerRootAndBackGround(handler, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return registerRootAndBackGround(handler, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return registerRootAndBackGround(handler, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return registerRootAndBackGround(handler, MouseWheelEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return registerRootAndBackGround(handler, ClickEvent.getType());
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return registerRootAndBackGround(handler, DoubleClickEvent.getType());
	}

	@Override
	public List<GraphicsObject> getObjects() {
		return objects;
	}

	@Override
	public void add(GraphicsObject object) {
		objectContainer.add(object);
		if (object.hasRole(HtmlRenderable.TYPE)) {
			if (widgetContainer != null) {
				widgetContainer.add(object.getRole(HtmlRenderable.TYPE).asWidget());
			}
		}
		objects.add(object);
		eventBus.fireEvent(new GraphicsObjectContainerEvent(object, ActionType.ADD));
	}

	@Override
	public void remove(GraphicsObject object) {
		objectContainer.remove(object.getRenderable());
		objects.remove(object);
		eventBus.fireEvent(new GraphicsObjectContainerEvent(object, ActionType.REMOVE));
	}

	@Override
	public void clear() {
		objectContainer.clear();
		objects.clear();
		eventBus.fireEvent(new GraphicsObjectContainerEvent(ActionType.CLEAR));
	}

	@Override
	public void update(GraphicsObject object) {
		eventBus.fireEvent(new GraphicsObjectContainerEvent(object, ActionType.UPDATE));
	}

	@Override
	public void setSelected(GraphicsObject object, boolean selected) {
		eventBus.fireEvent(new GraphicsObjectSelectedEvent(object, selected));
	}

	@Override
	public void deselectAll() {
		eventBus.fireEvent(new GraphicsObjectSelectedEvent(null, false));
	}

	@Override
	public com.google.web.bindery.event.shared.HandlerRegistration addGraphicsObjectContainerHandler(Handler handler) {
		return eventBus.addHandler(GraphicsObjectContainerEvent.getType(), handler);
	}

	@Override
	public com.google.web.bindery.event.shared.HandlerRegistration addGraphicsOperationEventHandler(
			org.geomajas.graphics.client.event.GraphicsOperationEvent.Handler handler) {
		return eventBus.addHandler(GraphicsOperationEvent.getType(), handler);
	}

	@Override
	public void setStopPropagation(boolean stopPropagation) {
		backGround.setStopPropagation(stopPropagation);
		rootContainer.setStopPropagation(stopPropagation);
	}

	@Override
	public boolean isEmpty() {
		return objectContainer.isEmpty();
	}

	@Override
	public void setCursor(String css) {
		objectContainer.setCursor(css);
	}

	@Override
	public void capture() {
		objectContainer.capture();
	}

	@Override
	public void releaseCapture() {
		objectContainer.releaseCapture();
	}

	@Override
	public void setOpacity(double opacity) {
		objectContainer.setOpacity(opacity);
	}

	@Override
	public void setVisible(boolean visible) {
		objectContainer.setVisible(visible);
	}

	@Override
	public boolean isSourceOf(GwtEvent<?> event) {
		return objectContainer.isSourceOf(event);
	}

	@Override
	public boolean remove(Renderable renderable) {
		return objectContainer.remove(renderable);
	}

	@Override
	public boolean remove(IsRenderable renderable) {
		return objectContainer.remove(renderable);
	}

	@Override
	public void bringToFront(Renderable renderable) {
		objectContainer.bringToFront(renderable);
	}

	@Override
	public void bringToFront(IsRenderable renderable) {
		objectContainer.bringToFront(renderable);
	}

	@Override
	public RenderContainer getParent() {
		return objectContainer.getParent();
	}

	@Override
	public void removeFromParent() {
		objectContainer.removeFromParent();
	}

	@Override
	public void insert(Renderable renderable, int index) {
		objectContainer.insert(renderable, index);
	}

	@Override
	public void insert(IsRenderable renderable, int index) {
		objectContainer.insert(renderable, index);
	}

	@Override
	public int indexOf(Renderable renderable) {
		return objectContainer.indexOf(renderable);
	}

	@Override
	public int indexOf(IsRenderable renderable) {
		return objectContainer.indexOf(renderable);
	}

	@Override
	public <H extends EventHandler> HandlerRegistration addDomHandler(H handler, Type<H> type) {
		return objectContainer.addDomHandler(handler, type);
	}

	private <H extends EventHandler> HandlerRegistration registerRootAndBackGround(H handler, DomEvent.Type<H> type) {
		final HandlerRegistration rootRegistration = rootContainer.addDomHandler(handler, type);
		final HandlerRegistration bgRegistration = backGround.addDomHandler(handler, type);
		return new HandlerRegistration() {

			@Override
			public void removeHandler() {
				rootRegistration.removeHandler();
				bgRegistration.removeHandler();
			}
		};
	}

	@Override
	public RenderContainer createContainer() {
		return Graphics.getRenderElementFactory().createRenderContainer();
	}

	@Override
	public Coordinate getScreenCoordinate(HumanInputEvent<?> event) {
		return renderArea.getScreenCoordinate(event);
	}

	@Override
	public Coordinate transform(Coordinate coordinate, RenderSpace from, RenderSpace to) {
		return renderArea.transform(coordinate, from, to);
	}

	@Override
	public Bbox transform(Bbox bounds, RenderSpace from, RenderSpace to) {
		Coordinate p1 = transform(BboxService.getOrigin(bounds), from, to);
		Coordinate p2 = transform(BboxService.getEndPoint(bounds), from, to);
		return new Bbox(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(), p2.getY()),
				Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
	}

	@Override
	public BboxPosition transform(BboxPosition position, RenderSpace from, RenderSpace to) {
		switch (position) {
			case CORNER_LL:
				return BboxPosition.CORNER_UL;
			case CORNER_LR:
				return BboxPosition.CORNER_UR;
			case CORNER_UL:
				return BboxPosition.CORNER_LL;
			case CORNER_UR:
				return BboxPosition.CORNER_LR;
			case MIDDLE_LEFT:
				return BboxPosition.MIDDLE_LEFT;
			case MIDDLE_LOW:
				return BboxPosition.MIDDLE_UP;
			case MIDDLE_RIGHT:
				return BboxPosition.MIDDLE_RIGHT;
			case MIDDLE_UP:
			default:
				return BboxPosition.MIDDLE_LOW;
		}
	}

}
