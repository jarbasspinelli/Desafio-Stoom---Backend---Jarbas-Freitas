package com.jarbas.teste.entities;

public class Geometry {

	 public Location location;
	 public String location_type;
	 public Viewport viewport;
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public Viewport getViewport() {
		return viewport;
	}
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
}
