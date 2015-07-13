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
package org.geomajas.graphics.client.controller.role;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsControllerWithVisibleElement;
import org.geomajas.graphics.client.controller.drag.AbstractDragHandler;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.operation.MarkerPositionOperation;
import org.geomajas.graphics.client.render.Marker;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Controller to drag the anchor of an {@link HasMarker} role.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class AnchoredDragController extends UpdateHandlerGraphicsControllerWithVisibleElement {

	/**
	 * Object under control.
	 */
	private HasMarker anchorPointObject;

	/**
	 * Handler to drag the anchor.
	 */
	private AnchorDragHandler dragHandler;

	public AnchoredDragController(GraphicsObject object, GraphicsService service) {
		super(service, object);
		this.anchorPointObject = object.getRole(HasMarker.TYPE);
	}



	@Override
	protected void init() {
		setHandlerGroup(getService().getObjectContainer().createContainer());
		// create the drag handler and attach it
		dragHandler = new AnchorDragHandler(getObject(), getService(), this);
		dragHandler.renderInContainer(getHandlerGroup());
		// update positions
		updateHandlers();
		// add the group
		getContainer().add(getHandlerGroup());
	}

	@Override
	public void updateHandlers() {
		if (dragHandler != null) {
			dragHandler.update();
		}
	}

	@Override
	public void setControllerElementsVisible(boolean visible) {
		getObject().getRole(HasMarker.TYPE).getMarker().setVisible(visible);
	}

	/**
	 * Implementation of {@link org.geomajas.graphics.client.controller.drag.AbstractDragHandler}
	 * for {@link AnchoredDragController}.
	 * 
	 * @author Jan De Moerloose
	 * @author Jan Venstermans
	 * 
	 */
	class AnchorDragHandler extends AbstractDragHandler {
		
		private Marker invisibleSquareAnchor;

		public AnchorDragHandler(GraphicsObject object, GraphicsService service,
				UpdateHandlerGraphicsController graphicsHandler) {
			super(object, service, graphicsHandler);
		}

		@Override
		public void update() {
			invisibleSquareAnchor.setUserPosition(anchorPointObject.getMarker().getUserPosition());
		}

		public void renderInContainer(RenderContainer group) {	
			group.add(invisibleSquareAnchor.getRenderable());
		}
		
		@Override
		protected Renderable createInvisibleMask() {
			invisibleSquareAnchor = MarkerShape.SQUARE.getMarkerShape();
			invisibleSquareAnchor.setFillOpacity(0);
			invisibleSquareAnchor.setStrokeOpacity(0);
			invisibleSquareAnchor.getRenderable().setCursor(Cursor.MOVE.getCssName());
			return invisibleSquareAnchor.getRenderable();
		}

		@Override
		protected GraphicsObject createDraggingMask() {
			GraphicsObject maskObject = (GraphicsObject) getObject().cloneObject();
			maskObject.getRenderable().setOpacity(0.5);
			maskObject.getRole(HasMarker.TYPE).getMarker().setUserPosition(getBeginPosition());
			return maskObject;
		}

		@Override
		protected Coordinate getObjectPosition() {
			return anchorPointObject.getMarker().getUserPosition();
		}

		@Override
		protected GraphicsOperation createGraphicsOperation(Coordinate dragStartUser, Coordinate dragStopUser) {
			Coordinate newPosition = shiftPosition(dragStartUser, dragStopUser);
			return new MarkerPositionOperation(getObject(), getBeginPosition(), newPosition);
		}

		@Override
		protected void onDragContinue(Coordinate dragContinueUser) {
			Coordinate newPosition = shiftPosition(getDragStartUser(), dragContinueUser);
			getDraggingMask().getRole(HasMarker.TYPE).getMarker().setUserPosition(newPosition);
		}

	}
}
