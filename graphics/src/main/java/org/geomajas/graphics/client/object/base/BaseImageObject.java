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
import org.geomajas.graphics.client.render.BaseImage;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.FlipState;

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
		image.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return image.getUserPosition();
	}

	@Override
	public Object cloneObject() {
		return new BaseImageObject(image.getUserPosition().getX(), image.getUserPosition().getY(), (int) image
				.getUserBounds().getWidth(), (int) image.getUserBounds().getHeight(), image.getHref(),
				image.isPreserveAspectRatio());
	}

	@Override
	public void flip(FlipState state) {
		// symmetric
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		image.setUserBounds(bounds);
	}

	@Override
	public boolean isPreserveRatio() {
		return image.isPreserveAspectRatio();
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	public boolean isPreserveAspectRatio() {
		return image.isPreserveAspectRatio();
	}

	@Override
	public Bbox getUserBounds() {
		return image.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return image.getBounds();
	}

	@Override
	public Renderable getRenderable() {
		return image.getRenderable();
	}

	public String getHref() {
		return image.getHref();
	}
}