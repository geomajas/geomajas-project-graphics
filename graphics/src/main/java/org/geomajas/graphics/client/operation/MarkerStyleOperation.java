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
package org.geomajas.graphics.client.operation;

import org.geomajas.graphics.client.object.GraphicsObject;
import org.geomajas.graphics.client.object.role.HasMarker;
import org.geomajas.graphics.client.object.role.Strokable;
import org.geomajas.graphics.client.render.Marker;

/**
 * Operation that anchors an object.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 * 
 */
public class MarkerStyleOperation implements GraphicsOperation {

	private int beforeStrokeWidth;

	private String beforeStrokeColor;

	private double beforeStrokeOpacity;

	private String beforePointColor;

	private double beforePointOpacity;

	private int afterStrokeWidth;

	private String afterStrokeColor;

	private double afterStrokeOpacity;

	private String afterPointColor;

	private double afterPointOpacity;

	private GraphicsObject anchored;

	public MarkerStyleOperation(GraphicsObject anchored, int beforeStrokeWidth,
		  String beforeStrokeColor, double beforeStrokeOpacity, String beforePointColor, double beforePointOpacity,
		  int afterStrokeWidth, String afterStrokeColor, double afterStrokeOpacity, String afterPointColor,
		  double afterPointOpacity) {
		this.anchored = anchored;
		this.beforeStrokeWidth = beforeStrokeWidth;
		this.beforeStrokeColor = beforeStrokeColor;
		this.beforeStrokeOpacity = beforeStrokeOpacity;
		this.beforePointColor = beforePointColor;
		this.beforePointOpacity = beforePointOpacity;
		this.afterStrokeWidth = afterStrokeWidth;
		this.afterStrokeColor = afterStrokeColor;
		this.afterStrokeOpacity = afterStrokeOpacity;
		this.afterPointColor = afterPointColor;
		this.afterPointOpacity = afterPointOpacity;
	}

	@Override
	public void execute() {
		asLineStrokable().setStrokeWidth(afterStrokeWidth);
		asLineStrokable().setStrokeColor(afterStrokeColor);
		asLineStrokable().setStrokeOpacity(afterStrokeOpacity);
		asMarker().setStrokeColor(afterPointColor);
		asMarker().setFillColor(afterPointColor);
		asMarker().setStrokeOpacity(afterPointOpacity);
		asMarker().setFillOpacity(afterPointOpacity);
	}

	@Override
	public void undo() {
		asLineStrokable().setStrokeWidth(beforeStrokeWidth);
		asLineStrokable().setStrokeColor(beforeStrokeColor);
		asLineStrokable().setStrokeOpacity(beforeStrokeOpacity);
		asMarker().setStrokeColor(beforePointColor);
		asMarker().setFillColor(beforePointColor);
		asMarker().setStrokeOpacity(beforePointOpacity);
		asMarker().setFillOpacity(beforePointOpacity);
	}

	private Marker asMarker() {
		return anchored.getRole(HasMarker.TYPE).getMarker();
	}

	private Strokable asLineStrokable() {
		return anchored.getRole(HasMarker.TYPE).getMarkerLineStrokable();
	}

	@Override
	public GraphicsObject getObject() {
		return anchored;
	}

	@Override
	public Type getType() {
		return Type.UPDATE;
	}

}
