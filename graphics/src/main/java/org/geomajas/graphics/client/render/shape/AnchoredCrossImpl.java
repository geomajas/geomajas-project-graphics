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
package org.geomajas.graphics.client.render.shape;

import java.util.ArrayList;
import java.util.List;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.updateable.hasmarker.MarkerShape;
import org.geomajas.graphics.client.render.Marker;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Path;
import org.vaadin.gwtgraphics.client.shape.path.LineTo;
import org.vaadin.gwtgraphics.client.shape.path.MoveTo;
import org.vaadin.gwtgraphics.client.shape.path.PathStep;
import org.vaadin.gwtgraphics.client.shape.path.ScaleHelper;

/**
 * A non-scaling cross (diagonals of a square) that is anchored to its world space location on a specific pixel or
 * anchor location (useful for location markers). Using own veriosn of the steps code from {@link Path} internally in
 * this class.
 * 
 * @author Jan Venstermans
 * 
 */
public class AnchoredCrossImpl extends Path implements Marker {

	private int crossHeightPixels;

	private List<PathStep> steps = new ArrayList<PathStep>();

	private VectorRenderable renderable;

	/**
	 * Creates a cross (diagonals of a square) at the specified world location with a specified size and anchor point.
	 * E.g., if (anchorX,anchorY)=(0, 0), the center of the cross will be positioned at the world location.
	 * 
	 * @param userX x-location in world coordinates of the cross intersection point
	 * @param userY y-location in world coordinates of the cross intersection point
	 * @param crossHeightPixels height of the in pixels
	 */
	public AnchoredCrossImpl(double userX, double userY, int crossHeightPixels) {
		super(userX, userY);
		this.renderable = new VectorRenderable(this);
		this.crossHeightPixels = crossHeightPixels;
	}

	protected void drawTransformed() {
		if (crossHeightPixels > 0) {
			int heigth = (int) (crossHeightPixels / getScaleX());
			if (steps != null) {
				steps.clear();
			} else {
				steps = new ArrayList<PathStep>();
			}
			steps.add(new MoveTo(false, getUserX(), getUserY()));
			moveRelativelyTo(-heigth / 2, -heigth / 2);
			lineRelativelyTo(heigth, heigth);
			moveRelativelyTo(0, -heigth);
			lineRelativelyTo(-heigth, heigth);
			MoveTo moveTo = (MoveTo) steps.get(0);
			steps.set(0, new MoveTo(moveTo.isRelativeCoords(), moveTo.getUserX() + getDeltaX(), moveTo.getUserY()
					+ getDeltaY()));
			// apply scale
			ScaleHelper scaleHelper = new ScaleHelper(getScaleX(), getScaleY());
			for (PathStep step : steps) {
				step.scale(scaleHelper);
			}
			getImpl().drawPath(getElement(), steps);
		}
	}

	public void moveRelativelyTo(int x, int y) {
		steps.add(new MoveTo(true, x, y));
	}

	public void moveRelativelyTo(double x, double y) {
		steps.add(new MoveTo(true, x, y));
	}

	public void lineRelativelyTo(int x, int y) {
		steps.add(new LineTo(true, x, y));
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	@Override
	public Coordinate getUserPosition() {
		return new Coordinate(getUserX(), getUserY());
	}

	@Override
	public void setUserPosition(Coordinate position) {
		setUserX(position.getX());
		setUserY(position.getY());
	}

	public MarkerShape getMarkerShape() {
		return MarkerShape.CROSS;
	}

}