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
import org.geomajas.graphics.client.render.BaseText;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.shape.Text;

/**
 * Default implementation of {@link BaseText}.
 * 
 * @author Jan De Moerloose
 *
 */
public class BaseTextImpl extends Text implements BaseText {

	private VectorRenderable renderable;

	public BaseTextImpl(double userX, double userY, String text) {
		super(userX, userY, text);
		renderable = new VectorRenderable(this);
	}

	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and take the lower-left corner in screen space
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		getImpl().setX(getElement(), (int) b.getX(), isAttached());
		getImpl().setY(getElement(), (int) (b.getY() + b.getHeight()), isAttached());
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
		throw new UnsupportedOperationException("Can't set user bounds on text");
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
		return new Bbox(getX(), getY() - getTextHeight(), getTextWidth(), getTextHeight());
	}

	@Override
	public void setFontColor(String color) {
		setFillColor(color);
		setStrokeColor(color);
	}

	@Override
	public String getFontColor() {
		return getFillColor();
	}

	public double getUserWidth() {
		return Math.abs(getTextWidth() / getScaleX());
	}

	public double getUserHeight() {
		return Math.abs(getTextHeight() / getScaleY());
	}

}
