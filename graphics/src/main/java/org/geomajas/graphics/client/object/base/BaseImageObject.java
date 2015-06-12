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
import org.geomajas.graphics.client.object.role.Resizable;
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.BaseImage;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.FlipState;
import org.vaadin.gwtgraphics.client.Image;

/**
 * Extension of {@link BaseGraphicsObject} for an image.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseImageObject extends BaseGraphicsObject implements BaseImage, Resizable, Draggable {

	private BaseImage image;

	public BaseImageObject(double userX, double userY, int width, int height, String href, boolean preserveRatio) {
		this(Graphics.getRenderElementFactory().createImage(userX, userY, width, height, href, preserveRatio));
	}

	public BaseImageObject(BaseImage image) {
		this.image = image;
		addRole(Resizable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public void setUserPosition(Coordinate position) {
		image.setUserX(position.getX());
		image.setUserY(position.getY());
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(image.getUserX(), image.getUserY());
	}

	@Override
	public Object cloneObject() {
		return new BaseImageObject(image.getUserX(), image.getUserY(), (int) image.getUserWidth(),
				(int) image.getUserHeight(), image.getHref(), image.isPreserveAspectRatio());
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		image.setUserX(bounds.getX());
		image.setUserY(bounds.getY());
		image.setUserWidth(bounds.getWidth());
		image.setUserHeight(bounds.getHeight());
	}

	@Override
	public boolean isPreserveRatio() {
		return image.isPreserveAspectRatio();
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	public void setUserX(double x) {
		image.setUserX(x);
	}

	public void setUserY(double y) {
		image.setUserY(y);
	}

	public double getUserX() {
		return image.getUserX();
	}

	public double getUserY() {
		return image.getUserY();
	}

	public double getUserWidth() {
		return image.getUserWidth();
	}

	public double getUserHeight() {
		return image.getUserHeight();
	}

	public int getX() {
		return image.getX();
	}

	public int getY() {
		return image.getY();
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public void setUserWidth(double width) {
		image.setUserWidth(width);
	}

	public void setUserHeight(double height) {
		image.setUserHeight(height);
	}

	public boolean isPreserveAspectRatio() {
		return image.isPreserveAspectRatio();
	}

	@Override
	public Bbox getUserBounds() {
		return new Bbox(image.getUserX(), image.getUserY(), image.getUserWidth(), image.getUserHeight());
	}

	@Override
	public Bbox getBounds() {
		return new Bbox(image.getX(), image.getY(), image.getWidth(), image.getHeight());
	}

	@Override
	public Renderable getRenderable() {
		return image.getRenderable();
	}

	public String getHref() {
		return image.getHref();
	}
}