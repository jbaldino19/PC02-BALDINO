package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedor()
	{
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			if (!AppSettings.IsNullOrWhiteSpace(obj.getRazonsocial()))
			{
				if (!AppSettings.IsNullOrWhiteSpace(obj.getRuc()))
				{
					if (AppSettings.ValidaRUC(obj.getRuc()))
					{
						if (!AppSettings.IsNullOrWhiteSpace(obj.getDireccion()))
						{
							if (!AppSettings.IsNullOrWhiteSpace(obj.getTelefono()))
							{
								if (AppSettings.ValidaTelefonoFijo(obj.getTelefono()))
								{
									if (!AppSettings.IsNullOrWhiteSpace(obj.getCelular()))
									{
										if (AppSettings.ValidaCelular(obj.getCelular()))
										{
											if (!AppSettings.IsNullOrWhiteSpace(obj.getContacto()))
											{
												if (obj.getUbigeo().getIdUbigeo() != -1)
												{
													obj.setEstado(AppSettings.ESTADO_ACTIVO);
													obj.setFechaRegistro(new Date());
													Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
													if (objSalida == null)
													{
														salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
													}
													else
													{
														salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO);
													}
												}
												else
												{
													salida.put("mensaje", "Por favor seleccione algún ubigeo válido.");
												}
											}
											else
											{
												salida.put("mensaje", "Por favor ingrese algún contacto.");
											}
										}
										else
										{
											salida.put("mensaje", "Por favor ingrese un celular válido.");
										}
									}
									else
									{
										salida.put("mensaje", "Por favor ingrese el celular.");
									}
								}
								else
								{
									salida.put("mensaje", "Por favor ingrese un teléfono válido.");
								}
							}
							else
							{
								salida.put("mensaje", "Por favor ingrese el teléfono.");
							}
						}
						else
						{
							salida.put("mensaje", "Por favor ingrese la dirección.");
						}
					}
					else
					{
						salida.put("mensaje", "El RUC no es válido.");
					}
				}
				else
				{
					salida.put("mensaje", "Por favor ingrese el RUC.");
				}
			}
			else
			{
				salida.put("mensaje", "Por favor ingrese la razón social.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/consultaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> consultaProveedor(
				@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
				@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
				@RequestParam(name = "celular", required = false, defaultValue = "") String celular,
				@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
				@RequestParam(name = "estado", required = true, defaultValue = "1") int estado
			){

		Map<String, Object> salida = new HashMap<>();
		
		try
		{
			List<Proveedor> lista = proveedorService.consultaProveedor(ruc, "%" + razonsocial + "%", celular, idUbigeo, estado);
			
			if(CollectionUtils.isEmpty(lista))
			{
				salida.put("mensaje", "No existe datos para mostrar");
			}
			else
			{
				salida.put("data", lista);
				salida.put("mensaje", "Existen " + lista.size() + " datos para mostrar");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}
}