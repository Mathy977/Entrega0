package Tests;

import static org.junit.Assert.*;

import org.junit.Before;

import Entidades.*;
import Exepciones.OperaccionEstaCerrada;
import Exepciones.laOperacionIncluyeServicios;
import Exepciones.noSePuedeAgregarItemAOpCerrada;
import Exepciones.noSePuedeCerrarUnaOperacionCerrada;

import org.junit.Test;

public class TestEntrega0 {
	
	Articulo art1;
	Articulo art2;
	Servicio serv1;
	Servicio serv2;
	OperacionDeEgreso op;
	OperacionDeEgreso op2;
	OperacionDeEgreso op3;
	
	@Before
	public void init() throws Exception
	{
		art1 = new Articulo(5);
		art2 = new Articulo(1);
		
		serv1 = new Servicio(5);
		serv2 = new Servicio(1);
		
		op = new OperacionDeEgreso();
		op2 = new OperacionDeEgreso();
		op3 = new OperacionDeEgreso();
		
		op.agregarItem(art1);
		op.agregarItem(art2);
		op.agregarItem(art2);
		op.agregarItem(art2);
		
		op2.agregarItem(art1);
		op2.agregarItem(serv1);
		
		op3.agregarItem(serv1);
		op3.agregarItem(serv2);	
	}
	
	@Test
	public void testCalcularValorDeOperacion() {
		assertEquals(8, op.calcularPrecio());
	}
	
	@Test
	public void testCalcularValorDeOperacionCerrada() throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, OperaccionEstaCerrada {
		op.cerrarOperacion();
		art1.setPrecio(100);
		assertEquals(8, op.calcularPrecio());
	}
	
	@Test
	public void testGenerarRemito() throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, OperaccionEstaCerrada
	{
		op.cerrarOperacion();
		assertNotNull(op.getDocumento()); 
	}
	
	@Test(expected=OperaccionEstaCerrada.class)
	public void testGenerarRemitoOpCerrada() throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, noSePuedeAgregarItemAOpCerrada, OperaccionEstaCerrada
	{
		op.cerrarOperacion();
		op.generalRemito();
	}
	
	@Test(expected=laOperacionIncluyeServicios.class)
	public void testGenerarRemitoOpConServ() throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, noSePuedeAgregarItemAOpCerrada, OperaccionEstaCerrada
	{
		op2.generalRemito();
	}
	
	@Test(expected=noSePuedeAgregarItemAOpCerrada.class)
	public void testAgregarAOperaccionCerrada() throws noSePuedeCerrarUnaOperacionCerrada, laOperacionIncluyeServicios, noSePuedeAgregarItemAOpCerrada, OperaccionEstaCerrada
	{
		op.cerrarOperacion();
		op.agregarItem(art1);
	}
	
	
	/*@Test(expected=BRException.class)
	public void testCalculaSalarioNeto9() {
	  EmpleadoBR.calculaSalarioNeto(-1.0f);
	}
	
	@Test
	public void testCalcularValorDeOperacionCerradaExepcion() {
		
	}*/
}
