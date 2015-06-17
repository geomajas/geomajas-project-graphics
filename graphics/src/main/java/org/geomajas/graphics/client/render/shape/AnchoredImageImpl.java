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
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.Image;

/**
 * A non-scaling image that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 * 
 * @author Jan De Moerloose
 * 
 */
public class AnchoredImageImpl extends Image implements AnchoredImage {

	private VectorRenderable renderable;

	// value between 0 (left) and 1 (right)
	private double anchorX;

	// value between 0 (top) and 1 (bottom)
	private double anchorY;

	/**
	 * Creates an image at the specified world location with a specified size and anchor point. E.g., if
	 * (anchorX,anchorY)=(0.5, 0.5), the center of the image will be positioned at the world location.
	 * 
	 * @param userX x-location in world coordinates
	 * @param userY y-location in world coordinates
	 * @param width width in pixels
	 * @param height height in pixels
	 * @param href URL of the image (use absolute URLs, not GWT-based !)
	 * @param anchorX x-location of the anchor point (rectangle-relative)
	 * @param anchorY y-location of the anchor point (rectangle-relative)
	 */
	public AnchoredImageImpl(double userX, double userY, int width, int height, String href, double anchorX,
			double anchorY) {
		super(userX, userY, width, height, href);
		this.anchorX = anchorX;
		this.anchorY = anchorY;
		drawTransformed();
		this.renderable = new VectorRenderable(this);
	}

	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and upper-left corner in screen space
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		getImpl().setX(getElement(), (int) b.getX(), isAttached());
		getImpl().setY(getElement(), (int) b.getY(), isAttached());
		setWidth((int) getUserWidth());
		setHeight((int) getUserHeight());
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
		setUserWidth(bounds.getWidth());
		setUserHeight(bounds.getHeight());
		Coordinate center = BboxService.getCenterPoint(bounds);
		setUserX(center.getX() + anchorX * getUserWidth());
		setUserY(center.getY() + anchorY * getUserHeight());
	}

	@Override
	public Bbox getUserBounds() {
		double centerX = getUserX() - anchorX * getUserWidth();
		double centerY = getUserY() - anchorY * getUserHeight();
		return new Bbox(centerX - 0.5 * getUserWidth(), centerY - 0.5 * getUserHeight(), getUserWidth(),
				getUserHeight());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(getX(), getY(), getWidth(), getHeight());
	}

	public void setPreserveAspectRatio(boolean preserve) {
		getElement().setAttribute("preserveAspectRatio", preserve ? "true" : "none");
	}

	public boolean isPreserveAspectRatio() {
		return Boolean.parseBoolean(getElement().getAttribute("preserveAspectRatio"));
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

}