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
import org.geomajas.graphics.client.render.BaseCircle;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Circle;

/**
 * Default implementation of {@link BaseCircle}.
 * 
 * @author Jan De Moerloose
 *
 */
public class BaseCircleImpl extends Circle implements BaseCircle {

	private VectorRenderable renderable;

	public BaseCircleImpl(double userX, double userY, double userRadius) {
		super(userX, userY, userRadius);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(getUserX(), getUserY());
	}

	@Override
	public void setUserPosition(Coordinate position) {
		setUserX(position.getX());
		setUserY(position.getY());
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		Coordinate center = BboxService.getCenterPoint(bounds);
		setUserX(center.getX());
		setUserY(center.getY());
		setUserRadius(bounds.getWidth() / 2);
	}

	@Override
	public Bbox getUserBounds() {
		return new Bbox(getUserX() - getUserRadius(), getUserY() - getUserRadius(), 2 * getUserRadius(),
				2 * getUserRadius());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(getX() - getRadius(), getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
	}

}
