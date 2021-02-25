package io.jabber.server.controller.status;

import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

  @GetMapping(value = "/status", produces = "application/json")
  public Map<String, String> status() {
    return Collections.singletonMap("status", "running");
  }

}
