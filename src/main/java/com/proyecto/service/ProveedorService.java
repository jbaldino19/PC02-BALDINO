package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Proveedor;


public interface ProveedorService {

	public abstract List<Proveedor> listaProveedor();
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj);
}