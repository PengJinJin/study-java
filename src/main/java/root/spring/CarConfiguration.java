package root.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {

	@Bean
	public MyCarFactoryBean carFactoryBean() {
		MyCarFactoryBean cfb = new MyCarFactoryBean();
		cfb.setMake("Honda");
		cfb.setYear(1989);
		return cfb;
	}

	@Bean
	public Person aPerson() throws Exception {
		Person person = new Person();
		person.setCar(carFactoryBean().getObject());
		return person;
	}

}
