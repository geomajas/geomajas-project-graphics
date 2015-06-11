package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.IsRenderable;
import org.geomajas.graphics.client.render.RenderArea;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.DrawingArea;

import com.google.gwt.dom.client.Style.Unit;
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
import com.google.gwt.user.client.ui.Widget;


public class ShapeRenderArea implements RenderArea {
	
	private DrawingArea drawingArea;
	
	public ShapeRenderArea(int width, int height) {
		drawingArea = new DrawingArea(width, height);
		drawingArea.getElement().getStyle().setMargin(5, Unit.PX);
	}

	public int getWidth() {
		return drawingArea.getWidth();
	}

	public int getHeight() {
		return drawingArea.getHeight();
	}

	@Override
	public void setCursor(String css) {
		drawingArea.getElement().getStyle().setProperty("cursor", css);
	}

	@Override
	public void removeFromParent() {
		drawingArea.removeFromParent();
	}

	@Override
	public void bringToFront() {
	}

	@Override
	public void sendToPosition(int index) {
	}

	@Override
	public int getPosition() {
		return 0;
	}

	@Override
	public void capture() {		
	}

	@Override
	public void releaseCapture() {
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
	public RenderContainer createContainer() {
		return new VectorRenderContainer();
	}

	@Override
	public void addRenderable(Renderable renderable) {
		drawingArea.add(((VectorRenderable)renderable).getObject());
	}

	@Override
	public void addRenderable(IsRenderable renderable) {
		addRenderable(renderable.getRenderable());
	}


}
