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
import org.geomajas.graphics.client.Graphics;
import org.geomajas.graphics.client.object.BaseGraphicsObject;
import org.geomajas.graphics.client.object.role.Fillable;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.Marker;
import org.geomajas.graphics.client.render.RenderContainer;
import org.geomajas.graphics.client.render.Renderable;

/**
 * Implementation of marker for anchored role.
 *
 * @author Jan De Moerloose
 * @author Jan Venstermans
 *
 */
public class MarkerObject extends BaseGraphicsObject implements Marker, Strokable, Fillable {

	private Marker anchor;

	private Marker background;

	private MarkerShape markerShape = MarkerShape.SQUARE;

	private RenderContainer markerGroup;

	public MarkerObject(Coordinate markerPosition, MarkerShape markerShape) {
		this.markerShape = (markerShape != null) ? markerShape : MarkerShape.SQUARE;
		anchor = createAnchor(this.markerShape.getMarkerShape());
		markerGroup = Graphics.getRenderElementFactory().createRenderContainer();
		// add a transparant but clickable background object in case of Cross Markershape
		// The cross itself is not clickable/draggable enough
		if (this.markerShape.equals(MarkerShape.CROSS)) {
			background = MarkerShape.SQUARE.getMarkerShape();
			background.setFillOpacity(0);
			background.setStrokeOpacity(0);
			markerGroup.add(background);
		}
		markerGroup.add(anchor);
		setPosition(markerPosition);

		addRole(Strokable.TYPE, this);
		addRole(Fillable.TYPE, this);
	}

	public void setPosition(Coordinate markerPosition) {
		anchor.setUserPosition(markerPosition);
		if (background != null) {
			background.setUserPosition(markerPosition);
		}
	}

	public Coordinate getPosition() {
		return anchor.getUserPosition();
	}

	public MarkerShape getMarkerShape() {
		return markerShape;
	}

	protected Marker createAnchor(Marker shape) {
		shape.setFillColor("#FF6600");
		shape.setStrokeColor("#FF6600");
		shape.setFillOpacity(0.7);
		return shape;
	}

	@Override
	public Object cloneObject() {
		return null;
	}

	// ------------------------
	// fillable methods
	// ------------------------

	@Override
	public void setFillColor(String fillColor) {
		anchor.setFillColor(fillColor);
	}

	@Override
	public void setFillOpacity(double fillOpacity) {
		anchor.setFillOpacity(fillOpacity);
	}

	@Override
	public String getFillColor() {
		return anchor.getFillColor();
	}

	@Override
	public double getFillOpacity() {
		return anchor.getFillOpacity();
	}

	// ------------------------
	// strokable methods
	// ------------------------

	@Override
	public String getStrokeColor() {
		return anchor.getStrokeColor();
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		anchor.setStrokeColor(strokeColor);
	}

	@Override
	public int getStrokeWidth() {
		return anchor.getStrokeWidth();
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		anchor.setStrokeWidth(strokeWidth);
	}

	@Override
	public double getStrokeOpacity() {
		return anchor.getStrokeOpacity();
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		anchor.setStrokeOpacity(strokeOpacity);
	}

	public Coordinate getUserPosition() {
		return anchor.getUserPosition();
	}

	public void setUserPosition(Coordinate position) {
		anchor.setUserPosition(position);
	}

	// ------------------------
	// render
	// ------------------------

	@Override
	public Renderable getRenderable() {
		return markerGroup;
	}

	public void setVisible(boolean visible) {
		anchor.setVisible(visible);
	}
}
