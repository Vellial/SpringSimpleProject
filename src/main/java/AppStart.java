import configs.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import services.UserService;

@ComponentScan
public class AppStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = applicationContext.getBean(UserService.class);
        userService.testMethod();

        userService.createUser(1L, "Mary");
        userService.createUser(11L, "Bob");
        userService.createUser(111L, "Taylor");
        userService.createUser(11111L, "Katy");

        System.out.println("ALL USERS: ");
        userService.getUsers().forEach(System.out::println);

        System.out.println("---------------------------------");
        System.out.println("ONE USER: ");
        System.out.println(userService.getUser(11L).getUsername());

        System.out.println("---------------------------------");

        userService.deleteUser(111L);
        System.out.println("ALL USERS (after delete): ");
        userService.getUsers().forEach(System.out::println);
    }
}
