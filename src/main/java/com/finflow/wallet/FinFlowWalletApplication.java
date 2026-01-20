package com.finflow.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.finflow.wallet.config.ProjectConfig;

@SpringBootApplication
public class FinFlowWalletApplication {

	public static void main(String[] args) {

		// Spring start here chapter 2.2.1 !!!
		SpringApplication.run(FinFlowWalletApplication.class, args);

	}

}
