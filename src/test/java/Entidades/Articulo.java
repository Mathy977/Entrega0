package Entidades;

public class Articulo extends Item {
	
	public Articulo(int precio) 
	{
		super(precio);
	}
	@Override
	public boolean esServicio()
	{
		return false;
	}
}
