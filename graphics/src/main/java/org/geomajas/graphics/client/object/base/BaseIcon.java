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
import org.geomajas.graphics.client.render.AnchoredImage;
import org.geomajas.graphics.client.render.Renderable;

/**
 * Extension of {@link BaseGraphicsObject} for an icon.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class BaseIcon extends BaseGraphicsObject implements Draggable {

	private AnchoredImage anchoredImage;

	public BaseIcon(double userX, double userY, int width, int height, String href) {
		this(Graphics.getRenderElementFactory().createAnchoredImage(userX, userY, width, height, href, true, 0.5, 0.5));
	}

	public BaseIcon(AnchoredImage anchoredImage) {
		this.anchoredImage = anchoredImage;
		addRole(Draggable.TYPE, this);
	}

	@Override
	public Renderable getRenderable() {
		return anchoredImage.getRenderable();
	}

	@Override
	public void setUserPosition(Coordinate imageAnchorPosition) {
		anchoredImage.setUserPosition(imageAnchorPosition);
	}

	@Override
	public Coordinate getUserPosition() {
		return anchoredImage.getUserPosition();
	}

	@Override
	public Bbox getUserBounds() {
		return anchoredImage.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return anchoredImage.getBounds();
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		// can't do this
	}

	public Object cloneObject() {
		return new BaseIcon(anchoredImage.getUserPosition().getX(), anchoredImage.getUserPosition().getY(),
				(int) anchoredImage.getUserBounds().getWidth(), (int) anchoredImage.getUserBounds().getHeight(),
				anchoredImage.getHref());
	}

	public String getHref() {
		return anchoredImage.getHref();
	}

}
