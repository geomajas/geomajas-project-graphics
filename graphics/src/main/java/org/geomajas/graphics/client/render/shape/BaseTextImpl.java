package org.geomajas.graphics.client.render.shape;

import org.geomajas.graphics.client.render.BaseText;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.shape.Text;

public class BaseTextImpl extends Text implements BaseText {

	private VectorRenderable renderable;

	public BaseTextImpl(double userX, double userY, String text) {
		super(userX, userY, text);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}
	
	@Override
	public void setFontColor(String color) {
		setFillColor(color);
	}

	@Override
	public String getFontColor() {
		return getFillColor();
	}

	public double getUserWidth() {
		return Math.abs(getTextWidth() / getScaleX());
	}

	public double getUserHeight() {
		return Math.abs(getTextHeight() / getScaleY());
	}

}
