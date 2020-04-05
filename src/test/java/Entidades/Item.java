package Entidades;

public abstract class Item {

	private int precio;
	
	public Item(int precio) {
		this.precio = precio;
	}
	public boolean esServicio()
	{
		return true;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	

}
