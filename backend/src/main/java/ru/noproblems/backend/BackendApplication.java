package ru.noproblems.backend;

// import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import io.jsonwebtoken.io.Encoders;
// import io.jsonwebtoken.security.Keys;
// import io.jsonwebtoken.SignatureAlgorithm;
// import javax.crypto.SecretKey;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);



		// SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		// String secretString = Encoders.BASE64.encode(key.getEncoded());
		// System.out.println("Kex: " + secretString);
	}

}
