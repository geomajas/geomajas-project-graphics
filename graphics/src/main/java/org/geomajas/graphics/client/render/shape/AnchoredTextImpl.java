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
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.render.AnchoredText;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.GraphicsUtil;
import org.vaadin.gwtgraphics.client.shape.Text;

/**
 * A non-scaling text that is anchored to its world space location on a specific pixel or anchor location (useful for
 * location markers).
 * 
 * @author Jan De Moerloose
 * 
 */
public class AnchoredTextImpl extends Text implements AnchoredText, Draggable {

	private VectorRenderable renderable;

	private double anchorX;

	private double anchorY;

	/**
	 * Creates an text at the specified world location with a specified size and anchor point. E.g., if
	 * (anchorX,anchorY)=(0.5, 0.5), the center of the text will be positioned at the world location.
	 * 
	 * @param userX lower-left x-location in world coordinates
	 * @param userY lower-left y-location in world coordinates
	 * @param text width in pixels
	 * @param anchorX x-location of the anchor point (text-relative)
	 * @param anchorY y-location of the anchor point (text-relative)
	 */
	public AnchoredTextImpl(double userX, double userY, String text, double anchorX, double anchorY) {
		super(userX, userY, text);
		setStrokeWidth(0);
		this.anchorX = anchorX;
		this.anchorY = anchorY;
		this.renderable = new VectorRenderable(this);
		drawTransformed();
	}
	
	@Override
	protected void drawTransformed() {
		// calculate the screen bounds and take the lower-left corner (!) in screen space
		Bbox b = GraphicsUtil.transform(getUserBounds(), getScaleX(), getScaleY(), getDeltaX(), getDeltaY());
		getImpl().setX(getElement(), (int) b.getX(), isAttached());
		getImpl().setY(getElement(), (int) (b.getY() + b.getHeight()), isAttached());
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

	public void setText(String text) {
		super.setText(text);
		drawTransformed();
	}

	public double getUserWidth() {
		return Math.abs(getTextWidth() / getScaleX());
	}

	public double getUserHeight() {
		return Math.abs(getTextHeight() / getScaleY());
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
		throw new UnsupportedOperationException("Can't set user bounds on text");
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
		return new Bbox(getX(), getY() - getTextHeight(), getTextWidth(), getTextHeight());
	}

	public void update() {
		drawTransformed();
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

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

}