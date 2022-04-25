package com.proyecto.util;

import java.util.regex.Pattern;

public class AppSettings {

	public static final String URL_CROSS_ORIGIN = "http://localhost:4200";
	
	public static final String MENSAJE_REG_YA_EXISTE = "Ya existe dicho registro.";
	public static final String MENSAJE_REG_ERROR = "No se registró, consulte con el administrador.";
	public static final String MENSAJE_REG_EXITOSO = "Se registró correctamente.";
		
	public static final String MENSAJE_ACT_YA_EXISTE = "Ya existe dicho registro.";
	public static final String MENSAJE_ACT_ERROR = "No se actualizó, consulte con el administrador.";
	public static final String MENSAJE_ACT_EXITOSO = "Se actualizó correctamente.";
	public static final String MENSAJE_ACT_NO_EXISTE_ID = "No existe el ID que se desea actualizar.";
	
	public static final String MENSAJE_ELI_ERROR = "No se eliminó, ya que el registro esta relacionado.";
	public static final String MENSAJE_ELI_EXITOSO = "Se eliminó correctamente.";
	public static final String MENSAJE_ELI_NO_EXISTE_ID = "No existe el ID que se desea eliminar.";
	
	public static final String MENSAJE_DNI_YA_EXISTE = "Ya existe dicho el DNI : ";
	
	public static final int ESTADO_ACTIVO = 1;
	
	public static boolean IsNullOrWhiteSpace(String value)
	{
		return value == null || (value.length() > 0 && value.trim().length() <= 0);
	}
	
	public static boolean ValidaRUC(long value){
        return ValidaRUC(String.valueOf(value));
    }

    public static boolean ValidaRUC(String value){
        if (value == null) {
            return false;
        }

        final int[] multipliers = {5,4,3,2,7,6,5,4,3,2};
        final String[] prefixes = getRucPrefijos();
        final int length = multipliers.length + 1;

        if(value.length() != length){
            return false;
        }

        boolean isPrefixOk = false;

        for (String prefix : prefixes){
            if(value.substring(0,2).equals(prefix)){
                isPrefixOk = true;
                break;
            }
        }

        if(!isPrefixOk)
        {
            return false;
        }

        int sum = 0;

        for(int i = 0; i < multipliers.length; i++){
            final char section = value.charAt(i);

            if(!Character.isDigit(section)){
                return false;
            }

            sum += Character.getNumericValue(value.charAt(i)) * multipliers[i];
        }

        final int rest = sum % length;
        final String response = String.valueOf(length - rest);

        return response.charAt(response.length() - 1) == value.charAt(value.length() - 1);
    }

    public static String[] getRucPrefijos(){
        return new String[]{"10", "15", "17", "20"};
    }

    public static boolean ValidaTelefonoFijo(String value)
    {
    	Pattern pattern = Pattern.compile("^?[01]\\d{7}");
    	
    	return pattern.matcher(value).find() && value.length() == 9;
    }

    public static boolean ValidaCelular(String value)
    {
    	Pattern pattern = Pattern.compile("^?[9]\\d{8}");
    	
    	return pattern.matcher(value).find() && value.length() == 9;
    }
}