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
package org.geomajas.graphics.client.object.updateable;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.base.BaseIcon;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.updateable.anchored.Anchored;
import org.geomajas.graphics.client.object.updateable.anchored.AnchoredImpl;
import org.geomajas.graphics.client.object.updateable.anchored.MarkerShape;
import org.geomajas.graphics.client.object.updateable.wrapper.DraggableWrapperForUpdateable;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.CopyUtil;

/**
 * Extension of {@link UpdateableGroupGraphicsObject}
 * that shows an anchor linked to a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan Venstermans
 *
 */
public class AnchoredIcon extends UpdateableGroupGraphicsObject {

	private RenderContainer renderContainer;

	private BaseIcon baseIcon;

	private AnchoredImpl anchored;

	public AnchoredIcon(Coordinate iconCoordinate, int iconWidth, int iconHeight,
						String iconHref, Coordinate anchorCoordinate, MarkerShape markerShape) {
		// create base graphics objects
		baseIcon = new BaseIcon(iconCoordinate.getX(), iconCoordinate.getY(), iconWidth, iconHeight, iconHref);
		anchored = new AnchoredImpl(baseIcon, anchorCoordinate, markerShape);

		// register updateables
		addUpdateable(anchored);

		// register roles of group object
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseIcon, this));
		addRole(Anchored.TYPE, anchored);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.addRenderable(anchored);
		renderContainer.addRenderable(baseIcon);
	}

	@Override
	public Object cloneObject() {
		AnchoredIcon clone = new AnchoredIcon(baseIcon.getUserPosition(), (int) baseIcon.getBounds().getWidth(),
				(int) baseIcon.getBounds().getHeight(), baseIcon.getHref(), anchored.getAnchorPosition(),
				anchored.getMarkerShape());
		CopyUtil.copyAnchoredProperties(this.getRole(Anchored.TYPE), clone.getRole(Anchored.TYPE));
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------


	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

}
