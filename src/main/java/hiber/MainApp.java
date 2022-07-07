package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru", new Car("car1", 1)));
      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru", new Car("car2", 2)));
      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", new Car("car3",3)));
      userService.addUser(new User("User4", "Lastname4", "user4@mail.ru", new Car("car4", 4)));

      List<User> users = userService.listAllUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<Car> cars = carService.listAllCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println();
      }

      System.out.println("Машина \"car1\" принадлежит " + userService.getUserByCar("car1", 1));

      context.close();
   }
}
