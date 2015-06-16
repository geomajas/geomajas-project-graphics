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
import org.geomajas.graphics.client.render.BaseImage;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.Image;

/**
 * Default implementation of {@link BaseImage}.
 * 
 * @author Jan De Moerloose
 *
 */
public class BaseImageImpl extends Image implements BaseImage {

	private VectorRenderable renderable;

	public BaseImageImpl(double userX, double userY, double width, double height, String href) {
		super(userX, userY, width, height, href);
		renderable = new VectorRenderable(this);
	}

	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and take the upper-left corner in screen space
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		getImpl().setX(getElement(), (int) b.getX(), isAttached());
		getImpl().setY(getElement(), (int) b.getY(), isAttached());
		setWidth((int) getUserWidth());
		setHeight((int) getUserHeight());
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
		setUserWidth(bounds.getWidth());
		setUserHeight(bounds.getHeight());
		Coordinate center = BboxService.getCenterPoint(bounds);
		setUserX(center.getX());
		setUserY(center.getY());
	}

	@Override
	public Bbox getUserBounds() {
		double centerX = getUserX();
		double centerY = getUserY();
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
}
