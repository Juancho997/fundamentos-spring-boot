package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

// "Implementación" es la clase que representa a un bean !!!

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	// defino las dependencias a inyectar
	private final ComponentDependency componentDependency;
	private final MyBean myBean;
	private final MyBeanWithDependency myBeanWithDependency;
	private final MyBeanWithProperties myBeanWithProperties;
	private final UserPojo userPojo;
	private final UserRepository userRepository;


	// inyecto las dependencias a través del constructor de la clase
	// se puede tener n implementaciones a partir de una dependencia
	// utilizando @Qualifier indicamos qué implementación se inyectará
	// en este caso, debido a que tenemos dos dependencias que implementan la misma interfaz, elegimos "componentTwoImplement"
	public FundamentosApplication(
			@Qualifier("componentTwoImplement")
			ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependency myBeanWithDependency,
			MyBeanWithProperties myBeanWithProperties,
			UserPojo userPojo,
			UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	// CommandLineRunner permite llamar a las implementaciones de cada dependencia
	@Override
	public void run(String... args) throws Exception {
		//previousExamples();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
	};

	private void getInformationJpqlFromUser(){

//		LOGGER.info("Found user with findByUserEmail method > " +
//				userRepository
//						.findByUserEmail("karen@domain.com")
//						.orElseThrow(()-> new RuntimeException("User not found")));
//
//		userRepository.findAndSort("ar", Sort.by("id")
//				.descending()) //.ascending()
//				.forEach(user -> LOGGER.info("User obtained with findAndSort method > " + user));
//				//.forEach(LOGGER::info);
//
//		userRepository.findByName("John")
//				.forEach(user -> LOGGER.info("User obtained with findByName query method > " + user));
//
		//Query methods

//		LOGGER.info("User found with findByEmailAndName query method > " +
//				userRepository
//						.findByEmailAndName("marco@domain.com", "Marco")
//						.orElseThrow(()-> new RuntimeException("Could not find user with email and name")));

//		userRepository.findByNameLike("%ar%")
//				.forEach(user -> LOGGER.info("User obtained with findByNameLike query method > " + user));

//		userRepository.findByNameOrEmail(null,"marco@domain.com")
//				.forEach(user -> LOGGER.info("User obtained with findByNameOrEmail query method > " + user));

//		LocalDate start = LocalDate.of(2021, 1,1);
//		LocalDate end = LocalDate.of(2021, 2,1);
//
//		userRepository.findByBirthDateBetween(start, end)
//				.forEach(user -> LOGGER.info("User with a birthday between start and end dates > " + user));

//		Al invocar a "like" en el método debemos utilizar %String%
//		userRepository.findByNameLikeOrderByIdDesc("%ar")
//						.forEach(user -> LOGGER.info("User found with  > findByNameLikeOrderByIdDesc query method " + user));
//		Al invocar a "containing" solo pasamos el String como param
//		userRepository.findByNameContainingOrderByIdDesc("ar")
//						.forEach(user -> LOGGER.info("User found with  > findByNameContainingOrderByIdDesc query method " + user));

		LocalDate dateP = LocalDate.of(2021, 3, 13);
		String emailP = "john@domain.com";

		LOGGER.info(userRepository.getAllByBirthDateAndEmail(dateP, emailP)
				.orElseThrow(() -> new RuntimeException("User not found with date and email Params")));
	}


	//INICIALIZAR BD > persistencia
	private void saveUsersInDataBase(){
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));

		List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);

		userRepository.saveAll(userList);
	};

	private void previousExamples(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		try{
			// generando un error
			int value = 10 / 0;
			LOGGER.debug("My value: " + value);
		}catch(Exception e){
			LOGGER.error("Error trying to divide by 0 > " + e.getMessage());
		}
	};
}
