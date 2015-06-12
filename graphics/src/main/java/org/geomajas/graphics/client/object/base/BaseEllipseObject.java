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
import org.geomajas.graphics.client.render.shape.VectorRenderable;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

/**
 * Extension of {@link BaseGraphicsObject} for a ellipse.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseEllipseObject extends BaseGraphicsObject implements BaseEllipse, Resizable, Draggable, Strokable, Fillable {

	private BaseEllipse ellipse;

	public BaseEllipseObject(Bbox boundingBox) {
		this(BboxService.getCenterPoint(boundingBox), boundingBox.getWidth() / 2, boundingBox.getHeight() / 2);
	}

	public BaseEllipseObject(Coordinate ellipseCenter, double userRadiusX, double userRadiusY) {
		this(ellipseCenter.getX(), ellipseCenter.getY(), userRadiusX, userRadiusY);
	}

	public BaseEllipseObject(double ellipseCenterX, double ellipseCenterY, double userRadiusX, double userRadiusY) {
		this(Graphics.getRenderElementFactory().createEllipse(ellipseCenterX, ellipseCenterY, userRadiusX, userRadiusY));
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
		// shift to center
		ellipse.setUserX(position.getX() + ellipse.getRadiusX());
		ellipse.setUserY(position.getY() + ellipse.getRadiusY());
	}

	@Override
	public Coordinate getUserPosition() {
		// shift to lower left corner
		return new Coordinate(ellipse.getUserX() - ellipse.getUserRadiusX(), ellipse.getUserY()
				- ellipse.getUserRadiusY());
	}

	public Object cloneObject() {
		BaseEllipseObject clone = new BaseEllipseObject(ellipse.getUserX(), ellipse.getUserY(),
				ellipse.getUserRadiusX(), ellipse.getUserRadiusY());
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
		Coordinate center = BboxService.getCenterPoint(bounds);
		ellipse.setUserX(center.getX());
		ellipse.setUserY(center.getY());
		ellipse.setUserRadiusX(bounds.getWidth() / 2);
		ellipse.setUserRadiusY(bounds.getHeight() / 2);
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
		return new Bbox(ellipse.getUserX() - ellipse.getUserRadiusX(), ellipse.getUserY()
				- ellipse.getUserRadiusY(), 2 * ellipse.getUserRadiusX(),
				2 * ellipse.getUserRadiusY());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(ellipse.getX() - ellipse.getRadiusX(), ellipse.getY()
				- ellipse.getRadiusY(), 2 * ellipse.getRadiusX(), 2 * ellipse.getRadiusY());
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

	public int getX() {
		return ellipse.getX();
	}

	public int getRadiusX() {
		return ellipse.getRadiusX();
	}

	public int getRadiusY() {
		return ellipse.getRadiusY();
	}

	public int getY() {
		return ellipse.getY();
	}

	public double getUserX() {
		return ellipse.getUserX();
	}

	public double getUserRadiusX() {
		return ellipse.getUserRadiusX();
	}

	public void setUserX(double userX) {
		ellipse.setUserX(userX);
	}

	public void setUserRadiusX(double userRadiusX) {
		ellipse.setUserRadiusX(userRadiusX);
	}

	public double getUserY() {
		return ellipse.getUserY();
	}

	public void setUserY(double userY) {
		ellipse.setUserY(userY);
	}

	public double getUserRadiusY() {
		return ellipse.getUserRadiusY();
	}

	public void setUserRadiusY(double userRadiusY) {
		ellipse.setUserRadiusY(userRadiusY);
	}
	
	
}
