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
import org.geomajas.graphics.client.object.role.CoordinateBased;

/**
 * Operation that rotates an object.
 * 
 * @author Jan De Moerloose
 * 
 */
public class RotateOperation implements GraphicsOperation {

	private Coordinate origin;

	private double angle;

	private GraphicsObject coordinateBased;

	public RotateOperation(GraphicsObject coordinateBased, Coordinate origin, double angle) {
		this.origin = origin;
		this.angle = angle;
		this.coordinateBased = coordinateBased;
	}

	@Override
	public void execute() {
		Coordinate[] coords = asCoordinateBased().getCoordinates();
		translate(coords, -origin.getX(), -origin.getY());
		rotate(coords, angle);
		translate(coords, origin.getX(), origin.getY());
		asCoordinateBased().setCoordinates(coords);
	}

	@Override
	public void undo() {
		Coordinate[] coords = asCoordinateBased().getCoordinates();
		translate(coords, -origin.getX(), -origin.getY());
		rotate(coords, -angle);
		translate(coords, origin.getX(), origin.getY());
		asCoordinateBased().setCoordinates(coords);
	}

	private void translate(Coordinate[] coords, double x, double y) {
		for (Coordinate coordinate : coords) {
			coordinate.setX(coordinate.getX() + x);
			coordinate.setY(coordinate.getY() + y);
		}
	}

	private void rotate(Coordinate[] coords, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		for (Coordinate coordinate : coords) {
			double x = coordinate.getX();
			double y = coordinate.getY();
			coordinate.setX(x * cos - y * sin);
			coordinate.setY(x * sin + y * cos);
		}
	}

	private CoordinateBased asCoordinateBased() {
		return coordinateBased.getRole(CoordinateBased.TYPE);
	}

	@Override
	public GraphicsObject getObject() {
		return coordinateBased;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
