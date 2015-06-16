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
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.BaseEllipse;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;

/**
 * Extension of {@link BaseGraphicsObject} for a ellipse.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseEllipseObject extends BaseGraphicsObject implements BaseEllipse, Resizable, Draggable, Strokable,
		Fillable {

	private BaseEllipse ellipse;

	public BaseEllipseObject(Bbox boundingBox) {
		this(BboxService.getCenterPoint(boundingBox), boundingBox.getWidth() / 2, boundingBox.getHeight() / 2);
	}

	public BaseEllipseObject(Coordinate center, double userRadiusX, double userRadiusY) {
		this(center.getX(), center.getY(), userRadiusX, userRadiusY);
	}

	public BaseEllipseObject(double centerX, double centerY, double userRadiusX, double userRadiusY) {
		this(Graphics.getRenderElementFactory().createEllipse(centerX, centerY, userRadiusX, userRadiusY));
	}

	public BaseEllipseObject(BaseEllipse ellipse) {
		this.ellipse = ellipse;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		ellipse.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return ellipse.getUserPosition();
	}

	public Object cloneObject() {
		BaseEllipseObject clone = new BaseEllipseObject(ellipse.getUserPosition().getX(), ellipse.getUserPosition()
				.getY(), ellipse.getUserRadiusX(), ellipse.getUserRadiusY());
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
		ellipse.setUserBounds(bounds);
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
		return ellipse.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return ellipse.getBounds();
	}

	@Override
	public Renderable getRenderable() {
		return ellipse.getRenderable();
	}

	@Override
	public void setFillColor(String fillColor) {
		ellipse.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		ellipse.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return ellipse.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return ellipse.getFillOpacity();
	}

	@Override
	public String getStrokeColor() {
		return ellipse.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		ellipse.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return ellipse.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		ellipse.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return ellipse.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		ellipse.setStrokeOpacity(strokeOpacity);
	}

	public int getRadiusX() {
		return ellipse.getRadiusX();
	}

	public int getRadiusY() {
		return ellipse.getRadiusY();
	}

	public double getUserRadiusX() {
		return ellipse.getUserRadiusX();
	}

	public void setUserRadiusX(double userRadiusX) {
		ellipse.setUserRadiusX(userRadiusX);
	}

	public double getUserRadiusY() {
		return ellipse.getUserRadiusY();
	}

	public void setUserRadiusY(double userRadiusY) {
		ellipse.setUserRadiusY(userRadiusY);
	}

}
