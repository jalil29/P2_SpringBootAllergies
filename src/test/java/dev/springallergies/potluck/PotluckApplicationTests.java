package dev.springallergies.potluck;

import dev.springallergies.controllers.PotlukkController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PotluckApplicationTests {

	@Autowired
	private PotlukkController controller;

	@Test
	public void contextLoads() {
	}

}
