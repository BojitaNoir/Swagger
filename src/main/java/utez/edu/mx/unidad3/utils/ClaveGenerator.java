package utez.edu.mx.unidad3.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class ClaveGenerator {

    public static String generateCedeClave(Long id){
        // Generar un formato para fechas este es que estara en español Mexico
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy", new Locale("es-MX"));
        //Utilizamos el sdf para formatear un objeto de tipo Date
        String fecha = sdf.format(new Date());
        //Generamos un numero random de 4 caracteres de longitud
        String random = String.format("%04d", ThreadLocalRandom.current().nextInt(1,1000));
        return "C" + id + "-" + fecha + "-" + random;
    }

    public static String generateWarehouseClave(String cedeClave, Long idWarehouse){
        return null;
    }
}
