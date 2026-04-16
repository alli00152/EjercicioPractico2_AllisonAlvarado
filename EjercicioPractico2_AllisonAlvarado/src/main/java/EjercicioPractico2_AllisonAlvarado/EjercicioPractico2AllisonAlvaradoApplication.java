package EjercicioPractico2_AllisonAlvarado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "EjercicioPractico2_AllisonAlvarado",
    "config",
    "domain",
    "repository",
    "controller",
    "service"
})
public class EjercicioPractico2AllisonAlvaradoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjercicioPractico2AllisonAlvaradoApplication.class, args);
    }
}