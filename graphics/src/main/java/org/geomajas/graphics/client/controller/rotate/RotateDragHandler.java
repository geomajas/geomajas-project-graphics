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
package org.geomajas.graphics.client.controller.rotate;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.GeometryService;
import org.geomajas.graphics.client.controller.UpdateHandlerGraphicsController;
import org.geomajas.graphics.client.controller.drag.AbstractDragHandler;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.base.BasePath;
import org.geomajas.graphics.client.object.role.CoordinateBased;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.operation.GraphicsOperation;
import org.geomajas.graphics.client.operation.RotateOperation;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.service.GraphicsService;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * DragHandler for rotation. Rotation origin is the user position.
 * 
 * @author Jan De Moerloose
 * 
 */
public class RotateDragHandler extends AbstractDragHandler {

	private BasePath invisbleMaskGraphicsObject;

	public RotateDragHandler(GraphicsObject object, GraphicsService service,
			UpdateHandlerGraphicsController graphicsController) {
		super(object, service, graphicsController);
	}

	@Override
	public void update() {
		invisbleMaskGraphicsObject.getRole(CoordinateBased.TYPE).setCoordinates(getCoordinateBased().getCoordinates());
	}

	@Override
	protected Renderable createInvisibleMask() {
		// make an invisible mask that is a rectangle of the bounds of the draggable.
		Bbox bbox = getDraggable().getBounds();
		Coordinate[] coords = GeometryService.toPolygon(bbox).getGeometries()[0].getCoordinates();
		invisbleMaskGraphicsObject = new BasePath(coords, true);
		invisbleMaskGraphicsObject.getRenderable().setOpacity(0.5);
		invisbleMaskGraphicsObject.getRenderable().setCursor(Cursor.MOVE.getCssName());
		return invisbleMaskGraphicsObject.getRenderable();
	}

	@Override
	protected GraphicsObject createDraggingMask() {
		GraphicsObject maskObject = (GraphicsObject) getObject().cloneObject();
		maskObject.getRenderable().setOpacity(0.5);
		maskObject.getRole(CoordinateBased.TYPE).setCoordinates(getCoordinateBased().getCoordinates());
		return maskObject;
	}

	@Override
	protected Coordinate getObjectPosition() {
		return getDraggable().getUserPosition();
	}

	@Override
	protected GraphicsOperation createGraphicsOperation(Coordinate before, Coordinate after) {
		double delta = getAngle(getBeginPosition(), before, after);
		return new RotateOperation(getObject(), getBeginPosition(), delta);
	}

	@Override
	protected void onDragContinue(Coordinate dragContinueUser) {
		if (getInvisbleMaskGraphicsObject() != null) {
			Coordinate origin = getBeginPosition();
			Coordinate[] coordinates = getCoordinateBased().getCoordinates();
			Coordinate[] rotated = rotate(coordinates, origin, getDragStartUser(), dragContinueUser);
			getDraggingMask().getRole(CoordinateBased.TYPE).setCoordinates(rotated);
		}
	}

	private double getAngle(Coordinate origin, Coordinate c1, Coordinate c2) {
		double angle1 = Math.atan2(c1.getY() - origin.getY(), c1.getX() - origin.getX());
		double angle2 = Math.atan2(c2.getY() - origin.getY(), c2.getX() - origin.getX());
		return angle2 - angle1;
	}

	private Coordinate[] rotate(Coordinate[] coordinates, Coordinate origin, Coordinate c1, Coordinate c2) {
		double delta = getAngle(origin, c1, c2);
		double cosDelta = Math.cos(delta);
		double sinDelta = Math.sin(delta);
		Coordinate[] rotated = new Coordinate[coordinates.length];
		// translate to origin
		int i = 0;
		for (Coordinate coordinate : coordinates) {
			rotated[i++] = new Coordinate(coordinate.getX() - origin.getX(), coordinate.getY() - origin.getY());
		}
		// rotate
		for (Coordinate r : rotated) {
			double x = r.getX();
			double y = r.getY();
			r.setX(x * cosDelta - y * sinDelta);
			r.setY(x * sinDelta + y * cosDelta);
		}
		// translate back
		for (Coordinate r : rotated) {
			r.setX(r.getX() + origin.getX());
			r.setY(r.getY() + origin.getY());
		}

		return rotated;
	}

	public CoordinateBased getCoordinateBased() {
		return getObject().getRole(CoordinateBased.TYPE);
	}

	public GraphicsObject getInvisbleMaskGraphicsObject() {
		return invisbleMaskGraphicsObject;
	}

	public Draggable getDraggable() {
		return getObject().getRole(Draggable.TYPE);
	}

}