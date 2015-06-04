package org.geomajas.graphics.client.render;

import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.VectorObject;

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
import com.google.gwt.user.client.DOM;

public class VectorRenderable implements Renderable {

	private VectorObject vectorObject;

	public VectorRenderable(VectorObject vectorObject) {
		this.vectorObject = vectorObject;
	}

	public VectorObject getObject() {
		return vectorObject;
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return vectorObject.addMouseDownHandler(handler);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		vectorObject.fireEvent(event);
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return vectorObject.addMouseUpHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return vectorObject.addMouseOutHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return vectorObject.addMouseOverHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return vectorObject.addMouseMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return vectorObject.addMouseWheelHandler(handler);
	}

	@Override
	public void renderInContainer(RenderContainer container) {
		VectorRenderContainer vectorRenderContainer = (VectorRenderContainer) container;
		vectorRenderContainer.getContainer().add(vectorObject);
	}

	@Override
	public void removeFromParent() {
		vectorObject.removeFromParent();
	}

	@Override
	public void capture() {
		DOM.setCapture(vectorObject.getElement());
	}

	@Override
	public void releaseCapture() {
		DOM.releaseCapture(vectorObject.getElement());
	}

	@Override
	public void setOpacity(double opacity) {
		if (vectorObject instanceof Group) {
			((Group) vectorObject).setOpacity(opacity);
		}
		if (vectorObject instanceof Fillable) {
			((Fillable) vectorObject).setFillOpacity(opacity);
		}
		if (vectorObject instanceof Strokable) {
			((Strokable) vectorObject).setStrokeOpacity(opacity);
		}

	}

	@Override
	public <H extends EventHandler> HandlerRegistration addDomHandler(H handler, Type<H> type) {
		return vectorObject.addDomHandler(handler, type);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return vectorObject.addClickHandler(handler);
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return vectorObject.addDoubleClickHandler(handler);
	}

	@Override
	public void setVisible(boolean visible) {
		vectorObject.setVisible(visible);
	}

}
