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
import org.geomajas.graphics.client.object.role.Textable;
import org.geomajas.graphics.client.render.BaseText;
import org.geomajas.graphics.client.render.Renderable;
import org.geomajas.graphics.client.util.FlipState;

/**
 * Extension of {@link BaseGraphicsObject} for a text.
 * 
 * @author Jan De Moerloose
 * @author Jan Venstermans
 */
public class BaseTextObject extends BaseGraphicsObject implements BaseText, Draggable, Textable, Resizable {

	private BaseText text;

	public BaseTextObject(double userX, double userY, String text) {
		this(Graphics.getRenderElementFactory().createText(userX, userY, text));
	}

	public BaseTextObject(BaseText text) {
		this(text, true);
	}

	public BaseTextObject(BaseText text, boolean setDefaultFontStyle) {
		this.text = text;
		if (setDefaultFontStyle) {
			text.setStrokeWidth(0);
			text.setStrokeColor(text.getFillColor());
			text.setStrokeOpacity(text.getFillOpacity());
			text.setFillColor("black");
			text.setFillOpacity(1.0);
		}
		addRole(Textable.TYPE, this);
		addRole(Draggable.TYPE, this);
	}

	@Override
	public Renderable getRenderable() {
		return text.getRenderable();
	}

	@Override
	public void setUserPosition(Coordinate position) {
		text.setUserPosition(position);
	}

	@Override
	public Coordinate getUserPosition() {
		return text.getUserPosition();
	}

	@Override
	public Object cloneObject() {
		BaseTextObject clone = new BaseTextObject(text.getUserPosition().getX(), text.getUserPosition().getY(),
				text.getText());
		clone.setFontFamily(text.getFontFamily());
		clone.setFontSize(text.getFontSize());
		return new BaseTextObject(clone, false);
	}

	@Override
	public void flip(FlipState state) {
		// no-op
	}

	@Override
	public void setUserBounds(Bbox bounds) {
		// no-op
	}

	@Override
	public Bbox getUserBounds() {
		return text.getUserBounds();
	}

	@Override
	public Bbox getBounds() {
		return text.getBounds();
	}

	@Override
	public boolean isPreserveRatio() {
		return false;
	}

	@Override
	public boolean isAutoHeight() {
		return false;
	}

	@Override
	public void setFontColor(String color) {
		text.setFontColor(color);
	}

	@Override
	public String getFontColor() {
		return text.getFillColor();
	}

	@Override
	public void setFontSize(int size) {
		text.setFontSize(size);
	}

	@Override
	public int getFontSize() {
		return text.getFontSize();
	}

	@Override
	public void setFontFamily(String font) {
		text.setFontFamily(font);
	}

	@Override
	public String getFontFamily() {
		return text.getFontFamily();
	}

	public void setFillColor(String fillColor) {
		text.setFillColor(fillColor);
	}

	public String getStrokeColor() {
		return text.getStrokeColor();
	}

	public void setStrokeColor(String strokeColor) {
		text.setStrokeColor(strokeColor);
	}

	public void setFillOpacity(double fillOpacity) {
		text.setFillOpacity(fillOpacity);
	}

	public int getStrokeWidth() {
		return text.getStrokeWidth();
	}

	public String getFillColor() {
		return text.getFillColor();
	}

	public void setStrokeWidth(int strokeWidth) {
		text.setStrokeWidth(strokeWidth);
	}

	public double getFillOpacity() {
		return text.getFillOpacity();
	}

	public double getStrokeOpacity() {
		return text.getStrokeOpacity();
	}

	public void setStrokeOpacity(double strokeOpacity) {
		text.setStrokeOpacity(strokeOpacity);
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String label) {
		text.setText(label);
	}

}
