package utez.edu.mx.unidad3.utils;

/*
* @Configuration: le dice a Spring que esta clase va a configurar
* algo, pero requiere al menos de un método que retorne
* dicha configuración.
*
* @Bean: define al metodo que va a regresar dicha configuración
* */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {
    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Bean
    public DataSource getConnection() {
        try {
            DriverManagerDataSource configuration = new DriverManagerDataSource();
            configuration.setUrl(DB_URL);
            configuration.setPassword(DB_PASSWORD);
            configuration.setUsername(DB_USERNAME);
            configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");

            return configuration;
        } catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos");
            ex.printStackTrace();
            return null;
        }
    }
}
