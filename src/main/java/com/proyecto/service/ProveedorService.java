package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Proveedor;


public interface ProveedorService {

	public abstract List<Proveedor> listaProveedor();
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj);
	public abstract List<Proveedor> consultaProveedor(String ruc, String razonsocial, String celular, int idUbigeo, int estado);
}