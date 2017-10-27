package com.example.demo;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzt
 */
@Configuration
@ConfigurationProperties(prefix = "a")
public class YmlConfig {

  private List<String> l = Lists.newArrayList("xx", "yy");
  private Map<String, Object> m = new HashMap<>();

  public YmlConfig() {
    m.put("a", 1);
  }

  public List<String> getL() {
    return l;
  }

  public void setL(List<String> l) {
    this.l = l; // size is 3, "i", "a", "b"
  }

  public Map<String, Object> getM() {
    return m;
  }

  public void setM(Map<String, Object> m) {
    this.m = m; // size is 2
  }
}
