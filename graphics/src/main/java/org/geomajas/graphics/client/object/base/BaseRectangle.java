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
import org.geomajas.graphics.client.render.AnchoredRectangle;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.render.VectorRenderable;
import org.geomajas.graphics.client.util.CopyUtil;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.VectorObject;

/**
 * Extension of {@link BaseGraphicsObject} for a rectangle.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseRectangle extends BaseGraphicsObject implements Resizable, Draggable, Fillable, Strokable {

	private AnchoredRectangle rectangle;

	public BaseRectangle(double userX, double userY, double width, double height) {
		this(Graphics.getRenderElementFactory().createAnchoredRectangle(userX, userY, width, height, 0, 0));
	}

	public BaseRectangle(AnchoredRectangle rectangle) {
		this.rectangle = rectangle;
		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		rectangle.setUserX(position.getX());
		rectangle.setUserY(position.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(rectangle.getUserX(), rectangle.getUserY());
	}

	@Override
	public Object cloneObject() {
		AnchoredRectangle mask = Graphics.getRenderElementFactory().createAnchoredRectangle(
				rectangle.getUserX(), rectangle.getUserY(), rectangle.getUserWidth(), rectangle.getUserHeight(), 0, 0);
		BaseRectangle clone =  new BaseRectangle(mask);
		CopyUtil.copyStrokableProperties(this, clone);
		CopyUtil.copyFillableProperties(this, clone);
		return new BaseRectangle(mask);
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		rectangle.setUserX(bounds.getX());
		rectangle.setUserY(bounds.getY());
		rectangle.setUserWidth(bounds.getWidth());
		rectangle.setUserHeight(bounds.getHeight());
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
		return new Bbox(rectangle.getUserX(), rectangle.getUserY(), rectangle.getUserWidth(),
				rectangle.getUserHeight());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
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

	@Override
	public void setOpacity(double opacity) {
		setFillOpacity(opacity);
		setStrokeOpacity(opacity);
	}

	public VectorObject getVectorObject() {
		return ((VectorRenderable) getRenderable()).getObject();
	}
}
