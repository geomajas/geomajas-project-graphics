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
package org.geomajas.graphics.client.render.shape;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.render.BaseEllipse;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

/**
 * Default implementation of {@link BaseEllipse}.
 * 
 * @author Jan De Moerloose
 *
 */
public class BaseEllipseImpl extends Ellipse implements BaseEllipse {

	private VectorRenderable renderable;

	public BaseEllipseImpl(double userX, double userY, double userRadiusX, double userRadiusY) {
		super(userX, userY, userRadiusX, userRadiusY);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	@Override
	public void setUserPosition(Coordinate position) {
		setUserX(position.getX());
		setUserY(position.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(getUserX(), getUserY());
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		setUserRadiusX(bounds.getWidth() / 2);
		setUserRadiusY(bounds.getHeight() / 2);
		Coordinate center = BboxService.getCenterPoint(bounds);
		setUserX(center.getX());
		setUserY(center.getY());
	}

	@Override
	public Bbox getUserBounds() {
		double centerX = getUserX();
		double centerY = getUserY();
		return new Bbox(centerX - getUserRadiusX(), centerY - getUserRadiusY(), 2 * getUserRadiusX(),
				2 * getUserRadiusY());
	}

	@Override
	public Bbox getBounds() {
		double centerX = getX();
		double centerY = getY();
		return new Bbox(centerX - getRadiusX(), centerY - getRadiusY(), 2 * getRadiusX(), 2 * getRadiusY());
	}

}
