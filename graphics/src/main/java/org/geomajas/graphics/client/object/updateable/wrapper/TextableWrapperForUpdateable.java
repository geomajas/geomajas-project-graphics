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
import org.geomajas.graphics.client.object.role.Textable;

/**
 * Wrapper around a {@link Textable} that will trigger
 * {@link org.geomajas.graphics.client.object.Updateable#onUpdate()}
 * on certain {@link Textable} method calls.
 *
 * @author Jan Venstermans
 *
 */
public class TextableWrapperForUpdateable implements Textable {

	private Textable delegate;

	private Updateable updateable;

	public TextableWrapperForUpdateable(Textable delegate, Updateable updateable) {
		this.delegate = delegate;
		this.updateable = updateable;
	}

	@Override
	public void setText(String label) {
		delegate.setText(label);
		updateable.onUpdate();
	}

	@Override
	public String getText() {
		return delegate.getText();
	}

	@Override
	public void setFontColor(String color) {
		delegate.setFontColor(color);
		updateable.onUpdate();
	}

	@Override
	public String getFontColor() {
		return delegate.getFontColor();
	}

	@Override
	public void setFontSize(int size) {
		delegate.setFontSize(size);
		updateable.onUpdate();
	}

	@Override
	public int getFontSize() {
		return delegate.getFontSize();
	}

	@Override
	public void setFontFamily(String font) {
		delegate.setFontFamily(font);
		updateable.onUpdate();
	}

	@Override
	public String getFontFamily() {
		return delegate.getFontFamily();
	}
}
