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
package org.geomajas.graphics.client.object.updateable.wrapper;

import org.geomajas.geometry.Coordinate;
import org.geomajas.graphics.client.object.Updateable;
import org.geomajas.graphics.client.render.Marker;
import org.geomajas.graphics.client.render.Renderable;

/**
 * Wraps a {@link Marker} role to trigger {@link Updateable}.
 * 
 * @author Jan De Moerloose
 *
 */
public class MarkerWrapperForUpdateable implements Marker {

	private Marker delegate;

	private Updateable updateable;

	public MarkerWrapperForUpdateable(Marker delegate, Updateable updateable) {
		this.delegate = delegate;
		this.updateable = updateable;
	}

	public void setFillColor(String fillColor) {
		delegate.setFillColor(fillColor);
		updateable.onUpdate();
	}

	public String getStrokeColor() {
		return delegate.getStrokeColor();
	}

	public void setStrokeColor(String strokeColor) {
		delegate.setStrokeColor(strokeColor);
		updateable.onUpdate();
	}

	public void setFillOpacity(double fillOpacity) {
		delegate.setFillOpacity(fillOpacity);
		updateable.onUpdate();
	}

	public Coordinate getUserPosition() {
		return delegate.getUserPosition();
	}

	public int getStrokeWidth() {
		return delegate.getStrokeWidth();
	}

	public String getFillColor() {
		return delegate.getFillColor();
	}

	public void setStrokeWidth(int strokeWidth) {
		delegate.setStrokeWidth(strokeWidth);
		updateable.onUpdate();
	}

	public double getFillOpacity() {
		return delegate.getFillOpacity();
	}

	public Renderable getRenderable() {
		return delegate.getRenderable();
	}

	public double getStrokeOpacity() {
		return delegate.getStrokeOpacity();
	}

	public void setVisible(boolean visible) {
		delegate.setVisible(visible);
		updateable.onUpdate();
	}

	public void setStrokeOpacity(double strokeOpacity) {
		delegate.setStrokeOpacity(strokeOpacity);
		updateable.onUpdate();
	}

	public void setUserPosition(Coordinate position) {
		delegate.setUserPosition(position);
		updateable.onUpdate();
	}	
	

}
