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
package org.geomajas.graphics.client.object.base;

import org.geomajas.geometry.Bbox;
import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.BaseRectangle;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;

/**
 * Extension of {@link BaseGraphicsObject} for a rectangle.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseRectangleObject extends BaseGraphicsObject implements BaseRectangle, Resizable, Draggable, Fillable,
		Strokable {

	private BaseRectangle rectangle;

	public BaseRectangleObject(double userX, double userY, double width, double height) {
		this(Graphics.getRenderElementFactory().createRectangle(userX, userY, width, height));
	}

	public BaseRectangleObject(BaseRectangle rectangle) {
		this.rectangle = rectangle;
		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		rectangle.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return rectangle.getUserPosition();
	}

	@Override
	public Object cloneObject() {
		BaseRectangleObject clone = new BaseRectangleObject(rectangle.getUserPosition().getX(), rectangle
				.getUserPosition().getY(), rectangle.getUserBounds().getWidth(), rectangle.getUserBounds().getHeight());
		CopyUtil.copyStrokableProperties(this, clone);
		CopyUtil.copyFillableProperties(this, clone);
		return clone;
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		rectangle.setUserBounds(bounds);
	}

	@Override
	public boolean isPreserveRatio() {
		return false;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public Bbox getUserBounds() {
		return rectangle.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return rectangle.getBounds();
	}

	@Override
	public Renderable getRenderable() {
		return rectangle.getRenderable();
	}

	@Override
	public String getFillColor() {
		return rectangle.getFillColor();
	}

	@Override
	public void setFillColor(String color) {
		rectangle.setFillColor(color);
	}

	@Override
	public double getFillOpacity() {
		return rectangle.getFillOpacity();
	}

	@Override
	public void setFillOpacity(double opacity) {
		rectangle.setFillOpacity(opacity);
	}

	@Override
	public String getStrokeColor() {
		return rectangle.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String color) {
		rectangle.setStrokeColor(color);
	}

	@Override
	public int getStrokeWidth() {
		return rectangle.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int width) {
		rectangle.setStrokeWidth(width);
	}

	@Override
	public double getStrokeOpacity() {
		return rectangle.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double opacity) {
		rectangle.setStrokeOpacity(opacity);
	}
}
