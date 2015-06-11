package org.geomajas.graphics.client.render;


public interface RenderContainer extends Renderable {

	void clear();

	boolean isEmpty();
	
	RenderContainer createContainer();
	
	void addRenderable(Renderable renderable);
	
	void addRenderable(IsRenderable renderable);
}
