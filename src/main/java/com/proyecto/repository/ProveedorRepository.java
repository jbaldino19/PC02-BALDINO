package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	@Query("select d from Proveedor d where (?1 is '' or d.ruc = ?1) and (?2 is '' or d.razonsocial like ?2) and (?3 is '' or d.celular = ?3) and (?4 is -1 or d.ubigeo.idUbigeo = ?4) and d.estado = ?5")
	public List<Proveedor> consultaProveedor(String ruc, String razonsocial, String celular, int idUbigeo, int estado);
}