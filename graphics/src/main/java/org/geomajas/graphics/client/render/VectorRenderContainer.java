package org.geomajas.graphics.client.render;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.VectorObjectContainer;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class VectorRenderContainer implements RenderContainer, RenderableList {

	private List<Renderable> renderableList = new ArrayList<Renderable>();

	private VectorRenderable renderable;

	public VectorRenderContainer() {
		this(new Group());
	}

	public VectorRenderContainer(VectorObjectContainer container) {
		this.renderable = new VectorRenderable((VectorObject) container);
	}

	public VectorObjectContainer getContainer() {
		return (VectorObjectContainer) renderable.getObject();
	}

	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return renderable.addMouseDownHandler(handler);
	}

	public void fireEvent(GwtEvent<?> event) {
		renderable.fireEvent(event);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	@Override
	public void addRenderable(Renderable renderable) {
		renderableList.add(renderable);
		renderable.renderInContainer(this);
	}

	@Override
	public void addRenderable(IsRenderable renderable) {
		addRenderable(renderable.getRenderable());
	}

	@Override
	public List<Renderable> getRenderableList() {
		return renderableList;
	}

	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return renderable.addMouseUpHandler(handler);
	}

	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return renderable.addMouseOutHandler(handler);
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return renderable.addMouseOverHandler(handler);
	}

	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return renderable.addMouseMoveHandler(handler);
	}

	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return renderable.addMouseWheelHandler(handler);
	}

	public void renderInContainer(RenderContainer container) {
		renderable.renderInContainer(container);
	}

	public void removeFromParent() {
		renderable.removeFromParent();
	}

	public void capture() {
		renderable.capture();
	}

	public void releaseCapture() {
		renderable.releaseCapture();
	}

	public void setOpacity(double opacity) {
		renderable.setOpacity(opacity);
	}

	@Override
	public void clear() {
		getContainer().clear();
		renderableList.clear();
	}

	@Override
	public <H extends EventHandler> HandlerRegistration addDomHandler(H handler, Type<H> type) {
		return renderable.addDomHandler(handler, type);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return renderable.addClickHandler(handler);
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return renderable.addDoubleClickHandler(handler);
	}

	@Override
	public void setVisible(boolean visible) {
		renderable.setVisible(visible);
	}

}
