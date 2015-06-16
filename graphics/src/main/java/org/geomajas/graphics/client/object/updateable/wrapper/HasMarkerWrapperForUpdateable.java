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

import org.geomajas.graphics.client.object.Updateable;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.Marker;

/**
 * Wraps a {@link HasMarker} role to trigger {@link Updateable}.
 * 
 * @author Jan De Moerloose
 *
 */
public class HasMarkerWrapperForUpdateable implements HasMarker {

	private HasMarker delegate;

	private Marker markerDelegate;

	public HasMarkerWrapperForUpdateable(HasMarker delegate, Updateable updateable) {
		this.delegate = delegate;
		markerDelegate = new MarkerWrapperForUpdateable(delegate.getMarker(), updateable);
	}

	@Override
	public Strokable getMarkerLineStrokable() {
		return delegate.getMarkerLineStrokable();
	}

	@Override
	public Marker getMarker() {
		return markerDelegate;
	}

}
