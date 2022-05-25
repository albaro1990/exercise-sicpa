package ec.sicpa.latam.com;

import ec.sicpa.latam.com.dao.impl.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = GenericDao.class)
public class BackendSicpaApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ec.sicpa.latam.com.BackendSicpaApplication.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ec.sicpa.latam.com.BackendSicpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 2; i++) {
			String bCryptPasswordEncoder = passwordEncoder.encode("admin");
			LOG.info(bCryptPasswordEncoder);
		}
	}

}
