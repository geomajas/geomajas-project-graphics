package org.geomajas.graphics.client.render.shape;

import org.geomajas.geometry.Bbox;
import org.geomajas.graphics.client.render.BaseImage;
import org.geomajas.graphics.client.render.Renderable;
import org.vaadin.gwtgraphics.client.Image;

public class BaseImageImpl extends Image implements BaseImage {

	private VectorRenderable renderable;

	public BaseImageImpl(double userX, double userY, double width, double height, String href) {
		super(userX, userY, width, height, href);
		renderable = new VectorRenderable(this);
	}

	@Override
	public Renderable getRenderable() {
		return renderable;
	}

	/**
	 * Get the bounds in user space.
	 * 
	 * @return the bounds in user space (takes anchor shift into account).
	 */
	public Bbox getUserBounds() {
		return new Bbox(getUserX() - getUserWidth(), getUserY() - getUserHeight(), getUserWidth(),
				getUserHeight());
	}

	public void setPreserveAspectRatio(boolean preserve) {
		getElement().setAttribute("preserveAspectRatio", preserve ? "true" : "none");
	}

	public boolean isPreserveAspectRatio() {
		return Boolean.parseBoolean(getElement().getAttribute("preserveAspectRatio"));
	}
}
