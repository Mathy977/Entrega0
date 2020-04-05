package Entidades;

import java.util.ArrayList;

import Exepciones.*;

public class OperacionDeEgreso {
		
		private boolean estaCerrado = false;
		private int precioCerrado;
		private DocumentoComercial documento;
		private ArrayList<Item> listaDeItems = new ArrayList<Item>();
		
public void agregarItem (Item item) throws noSePuedeAgregarItemAOpCerrada
{
	if(!estaCerrado)
	{
		listaDeItems.add(item);
	}
	else
	{
		throw new noSePuedeAgregarItemAOpCerrada();
	}
}

public int calcularPrecio()
{
	if(!estaCerrado)
	{
		return listaDeItems.stream().mapToInt(t -> t.getPrecio()).sum();
	}
	else
	{
		return precioCerrado;
	}
}

public void generalRemito() throws laOperacionIncluyeServicios,OperaccionEstaCerrada
{
	if(!estaCerrado)
	{
		if (!this.listaDeItems.stream().anyMatch(item -> item.esServicio())) 
		{
		 documento = new Remito();
		}
		else
		{
			throw new laOperacionIncluyeServicios();
		}
	}
	else
	{
		throw new OperaccionEstaCerrada();
	}
}

public void cerrarOperacion () throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, OperaccionEstaCerrada
{
	if(!estaCerrado) 
	{
		precioCerrado = this.calcularPrecio();
		this.generalRemito();
		estaCerrado = true;
	}
	else 
	{
		throw new noSePuedeCerrarUnaOperacionCerrada(); 
	}
}

public boolean isEstaCerrado() {
	return estaCerrado;
}

public void setEstaCerrado(boolean estaCerrado) {
	this.estaCerrado = estaCerrado;
}

public int getPrecioCerrado() {
	return precioCerrado;
}

public void setPrecioCerrado(int precioCerrado) {
	this.precioCerrado = precioCerrado;
}

public DocumentoComercial getDocumento() {
	return documento;
}

public void setDocumento(DocumentoComercial documento) {
	this.documento = documento;
}

public ArrayList<Item> getListaDeItems() {
	return listaDeItems;
}

public void setListaDeItems(ArrayList<Item> listaDeItems) {
	this.listaDeItems = listaDeItems;
}

}