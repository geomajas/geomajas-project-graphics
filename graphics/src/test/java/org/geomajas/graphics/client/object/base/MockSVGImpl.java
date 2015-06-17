package org.geomajas.graphics.client.object.base;

import org.vaadin.gwtgraphics.client.impl.SVGImpl;

import com.google.gwt.dom.client.Element;

public class MockSVGImpl extends SVGImpl {

	private int x;

	private int y;

	private int width;

	private int height;

	private int circleRadius;

	private int ellipseRadiusX;

	private int ellipseRadiusY;

	private String text;

	@Override
	public int getX(Element element) {
		return x;
	}

	@Override
	public void setX(Element element, int x, boolean attached) {
		this.x = x;
	}

	@Override
	public int getY(Element element) {
		return y;
	}

	@Override
	public void setY(Element element, int y, boolean attached) {
		this.y = y;
	}

	@Override
	public int getWidth(Element element) {
		return width;
	}

	@Override
	public void setWidth(Element element, int width) {
		this.width = width;
	}

	@Override
	public int getHeight(Element element) {
		return height;
	}

	@Override
	public void setHeight(Element element, int height) {
		this.height = height;
	}

	@Override
	public void setCircleRadius(Element element, int circleRadius) {
		this.circleRadius = circleRadius;
	}

	@Override
	public int getCircleRadius(Element element) {
		return circleRadius;
	}

	public int getEllipseRadiusX(Element element) {
		return ellipseRadiusX;
	}

	public void setEllipseRadiusX(Element element, int ellipseRadiusX) {
		this.ellipseRadiusX = ellipseRadiusX;
	}

	public int getEllipseRadiusY(Element element) {
		return ellipseRadiusY;
	}

	public void setEllipseRadiusY(Element element, int ellipseRadiusY) {
		this.ellipseRadiusY = ellipseRadiusY;
	}

	@Override
	public String getText(Element element) {
		return text;
	}

	@Override
	public void setText(Element element, String text, boolean attached) {
		this.text = text;
	}

	@Override
	public int getTextWidth(Element element) {
		return text != null ? 10 * text.length() : 0;
	}

	@Override
	public int getTextHeight(Element element) {
		return 10;
	}

}