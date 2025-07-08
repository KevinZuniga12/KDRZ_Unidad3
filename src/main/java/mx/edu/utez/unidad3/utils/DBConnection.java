package mx.edu.utez.unidad3.utils;
/*
 @Configuration: le dice a spring que esta clase de Java va a
 generar una configuacion durante la ejecucion de la aplicacion
 pero esta anotación debe siempre de ir co un meetodo con la anotación
 bean que le diga que va a configurar.

 @Bean: Le dice a spirng el metodo que retornara dicha configuración
* */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {
    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${df.password}")
    private String dbPassword;

    @Bean
    public DataSource getConnection(){
        try {
            DriverManagerDataSource configuration = new DriverManagerDataSource();
            configuration.setUrl(dbUrl);
            configuration.setUsername(dbUsername);
            configuration.setPassword(dbPassword);
            configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return configuration;

        } catch (Exception ex) {
            System.out.println("Error al obtener datos de la conexion");
            ex.printStackTrace();
            return  null;
        }

    }
}
