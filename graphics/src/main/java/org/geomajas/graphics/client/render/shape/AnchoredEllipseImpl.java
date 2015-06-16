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
import org.geomajas.graphics.client.render.AnchoredEllipse;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

/**
 * A non-scaling ellipse that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 * 
 * @author Jan De Moerloose
 * 
 */
public class AnchoredEllipseImpl extends Ellipse implements AnchoredEllipse {

	private double anchorX;

	private double anchorY;

	private VectorRenderable renderable;

	/**
	 * Creates a ellipse with an anchor point at the specified world location. We need the world location of the anchor
	 * point and the relative position of the anchor point w.r.t. the bounding box of the ellipse. Relative positions
	 * are between -0.5 and 0.5, so (anchorX, anchorY) = (0, 0) means the center of the ellipse, (anchorX, anchorY) =
	 * (0.5, 0.5) means the upper right corner of the ellipse, etc...
	 * 
	 * @param userX x-location of anchor point in world coordinates
	 * @param userY y-location of anchor point in world coordinates
	 * @param userRadiusX half-width in pixels
	 * @param userRadiusY half-height in pixels
	 * @param anchorX relative x-location of the anchor point w.r.t user bounds
	 * @param anchorY relative y-location of the anchor point w.r.t user bounds
	 */
	public AnchoredEllipseImpl(double userX, double userY, double userRadiusX, double userRadiusY, double anchorX,
			double anchorY) {
		super(userX, userY, userRadiusX, userRadiusY);
		this.anchorX = anchorX;
		this.anchorY = anchorY;
		drawTransformed();
		this.renderable = new VectorRenderable(this);
	}

	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and take the center
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		Coordinate center = BboxService.getCenterPoint(b);
		getImpl().setX(getElement(), (int) center.getX(), isAttached());
		getImpl().setY(getElement(), (int) center.getY(), isAttached());
		// don't scale, but have to set width/height here !
		setRadiusX((int) getUserRadiusX());
		setRadiusY((int) getUserRadiusY());
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	@Override
	public Coordinate getAnchor() {
		return new Coordinate(anchorX, anchorY);
	}

	@Override
	public void setAnchor(Coordinate anchor) {
		this.anchorX = anchor.getX();
		this.anchorY = anchor.getY();
		drawTransformed();
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
		setUserX(center.getX() + 2 * anchorX * getUserRadiusX());
		setUserY(center.getY() + 2 * anchorY * getUserRadiusY());
	}

	@Override
	public Bbox getUserBounds() {
		double centerX = getUserX() - 2 * anchorX * getUserRadiusX();
		double centerY = getUserY() - 2 * anchorY * getUserRadiusY();
		return new Bbox(centerX - getUserRadiusX(), centerY - getUserRadiusY(), 2 * getUserRadiusX(),
				2 * getUserRadiusY());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(getX() - getRadiusX(), getY() - getRadiusY(), 2 * getRadiusX(), 2 * getRadiusY());
	}

}