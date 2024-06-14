package org.big.prj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/check")
	public String hello() {
		return "hello World";
	}
}
