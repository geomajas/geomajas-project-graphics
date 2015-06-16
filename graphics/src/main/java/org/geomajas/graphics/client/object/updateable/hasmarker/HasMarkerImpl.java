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
package org.geomajas.graphics.client.object.updateable.hasmarker;

import org.geomajas.geometry.Coordinate;
import org.geomajas.geometry.service.BboxService;
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.Updateable;
import org.geomajas.graphics.client.object.role.Draggable;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.object.updateable.UpdateableGroupGraphicsObject;
import org.geomajas.graphics.client.object.updateable.wrapper.HasMarkerWrapperForUpdateable;
import org.geomajas.graphics.client.render.Marker;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;

/**
 * Implementation of {@link HasMarker} with the {@link Updateable} interface.
 * that shows an anchor linked to a {@link org.geomajas.graphics.client.object.base.BaseIcon}.
 *
 * @author Jan Venstermans
 *
 */
public class HasMarkerImpl extends UpdateableGroupGraphicsObject implements HasMarker, Updateable {

	private RenderContainer renderContainer;

	private MarkerObject marker;

	private TwoPointsLine anchorLine;

	private Draggable draggable; // subject

	public HasMarkerImpl(Draggable draggable, Coordinate anchorPosition, MarkerShape markerShape) {
		this.draggable = draggable;

		// create base graphics objects
		marker = new MarkerObject(anchorPosition, markerShape);
		anchorLine = new TwoPointsLine(BboxService.getCenterPoint(draggable.getUserBounds()), anchorPosition);

		// register roles of group object
		addRole(HasMarker.TYPE, new HasMarkerWrapperForUpdateable(this, this));

		// register render order
		renderContainer = Graphics.getRenderElementFactory().createRenderContainer();
		renderContainer.add(anchorLine);
		renderContainer.add(marker);
	}

	@Override
	public Object cloneObject() {
		HasMarkerImpl clone = new HasMarkerImpl(draggable, marker.getPosition(), marker.getMarkerShape());
		return clone;
	}

	//---------------------------------
	// render section
	//---------------------------------

	@Override
	public Renderable getRenderable() {
		return renderContainer;
	}

	@Override
	public Strokable getMarkerLineStrokable() {
		return anchorLine.getRole(Strokable.TYPE);
	}

	@Override
	public void onUpdate() {
		Coordinate anchoredPosition = BboxService.getCenterPoint(draggable.getUserBounds());
		anchorLine.setCoordinates(anchoredPosition, marker.getPosition());
	}

	@Override
	public Marker getMarker() {
		return marker;
	}
}
