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
package org.geomajas.graphics.client.operation;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.render.Marker;

/**
 * Operation that anchors an object.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class MarkerPositionOperation implements GraphicsOperation {

	private Coordinate beforePosition;

	private Coordinate afterPosition;

	private GraphicsObject anchored;

	public MarkerPositionOperation(GraphicsObject anchored, Coordinate beforePosition,
									 Coordinate afterPosition) {
		this.beforePosition = beforePosition;
		this.afterPosition = afterPosition;
		this.anchored = anchored;
	}

	@Override
	public void execute() {
		asMarker().setUserPosition(afterPosition);
	}

	@Override
	public void undo() {
		asMarker().setUserPosition(beforePosition);
	}

	private Marker asMarker() {
		return anchored.getRole(HasMarker.TYPE).getMarker();
	}

	@Override
	public GraphicsObject getObject() {
		return anchored;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
