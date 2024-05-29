/**
 * @description
 * @author heihaipeng
 * @create 2024/5/24 11:32
 */
package cn.hhp;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
