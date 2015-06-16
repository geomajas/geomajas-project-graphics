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
package org.geomajas.graphics.client.controller.drag;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.base.BaseRectangleObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.operation.DragOperation;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.MouseMoveEvent;

/**
 * Extension of {@link AbstractDragHandler} with a {@link BaseRectangleObject} for an invisible mask.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class GraphicsObjectDragHandler extends AbstractDragHandler {
	
	private BaseRectangleObject invisbleMaskGraphicsObject;

	public GraphicsObjectDragHandler(GraphicsObject object, GraphicsService service,
			UpdateHandlerGraphicsController graphicsController) {
		super(object, service, graphicsController);
	}

	@Override
	public void update() {
		invisbleMaskGraphicsObject.getRole(Draggable.TYPE).setUserPosition(getDraggable().getUserPosition());
	}

	@Override
	protected Renderable createInvisibleMask() {
		// make an invisible mask that is a rectangle of the bounds of the draggable.
		Bbox bbox = getDraggable().getBounds();
		invisbleMaskGraphicsObject = new BaseRectangleObject(bbox.getX(), bbox.getY(), bbox.getWidth(),
				bbox.getHeight());
		invisbleMaskGraphicsObject.getRenderable().setOpacity(0);
		invisbleMaskGraphicsObject.getRenderable().setCursor(Cursor.MOVE.getCssName());	
		return invisbleMaskGraphicsObject.getRenderable();
	}

	@Override
	protected GraphicsObject createDraggingMask() {
		GraphicsObject maskObject = (GraphicsObject) getObject().cloneObject();
		maskObject.getRenderable().setOpacity(0.5);
		maskObject.getRole(Draggable.TYPE).setUserPosition(getBeginPositionUser());
		return maskObject;
	}

	@Override
	protected Coordinate getObjectPosition() {
		return getDraggable().getUserPosition();
	}

	@Override
	protected GraphicsOperation createGraphicsOperation(Coordinate before,
			Coordinate after) {
		return new DragOperation(getObject(), before, after);
	}

	@Override
	protected void mouseMoveContent(MouseMoveEvent event) {
		getDraggingMask().getRole(Draggable.TYPE).setUserPosition(
				getNewPosition(event.getClientX(), event.getClientY()));
	}

	public GraphicsObject getInvisbleMaskGraphicsObject() {
		return invisbleMaskGraphicsObject;
	}

	public Draggable getDraggable() {
		return getObject().getRole(Draggable.TYPE);
	}

}


