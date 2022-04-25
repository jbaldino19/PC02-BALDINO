package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

}