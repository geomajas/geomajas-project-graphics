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
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.updateable.hasmarker.HasMarkerImpl;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
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

	private HasMarkerImpl anchored;

	public AnchoredIcon(Coordinate iconCoordinate, int iconWidth, int iconHeight,
						String iconHref, Coordinate anchorCoordinate, MarkerShape markerShape) {
		// create base graphics objects
		baseIcon = new BaseIcon(iconCoordinate.getX(), iconCoordinate.getY(), iconWidth, iconHeight, iconHref);
		anchored = new HasMarkerImpl(baseIcon, anchorCoordinate, markerShape);

		// register updateables
		addUpdateable(anchored);

		// register roles of group object
		addRole(Draggable.TYPE, new DraggableWrapperForUpdateable(baseIcon, this));
		addRole(HasMarker.TYPE, anchored);

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(anchored);
		renderContainer.add(baseIcon);
	}

	@Override
	public Object cloneObject() {
		AnchoredIcon clone = new AnchoredIcon(baseIcon.getUserPosition(), (int) baseIcon.getUserBounds().getWidth(),
				(int) baseIcon.getUserBounds().getHeight(), baseIcon.getHref(), anchored.getMarker().getUserPosition(),
				anchored.getMarker().getMarkerShape());
		CopyUtil.copyAnchoredProperties(this.getRole(HasMarker.TYPE), clone.getRole(HasMarker.TYPE));
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
