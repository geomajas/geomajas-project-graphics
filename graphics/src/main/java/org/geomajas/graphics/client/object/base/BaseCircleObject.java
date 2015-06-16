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
import org.geomajas.graphics.client.render.BaseCircle;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;

/**
 * Extension of {@link BaseGraphicsObject} for a circle.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseCircleObject extends BaseGraphicsObject implements BaseCircle, Resizable, Draggable, Strokable, 
		Fillable {

	private BaseCircle circle;

	public BaseCircleObject(double x, double y, double radius) {
		this(Graphics.getRenderElementFactory().createCircle(x, y, radius));
	}

	public BaseCircleObject(BaseCircle circle) {
		this.circle = circle;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		circle.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return circle.getUserPosition();
	}

	public BaseCircleObject cloneObject() {
		BaseCircleObject clone = new BaseCircleObject(circle.getUserPosition().getX(), circle.getUserPosition().getY(),
				circle.getUserRadius());
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
		circle.setUserBounds(bounds);
	}

	@Override
	public boolean isPreserveRatio() {
		return true;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public Bbox getUserBounds() {
		return circle.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return circle.getBounds();
	}

	@Override
	public Renderable getRenderable() {
		return circle.getRenderable();
	}

	@Override
	public void setFillColor(String fillColor) {
		circle.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		circle.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return circle.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return circle.getFillOpacity();
	}

	@Override
	public String getStrokeColor() {
		return circle.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		circle.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return circle.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		circle.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return circle.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		circle.setStrokeOpacity(strokeOpacity);
	}

	public double getUserRadius() {
		return circle.getUserRadius();
	}

	public void setUserRadius(double userRadius) {
		circle.setUserRadius(userRadius);
	}

	public int getRadius() {
		return circle.getRadius();
	}

}
