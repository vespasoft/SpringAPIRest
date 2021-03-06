/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.springapirest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  //@Autowired
  private DataSource dataSource;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
	  configureDb();
	  return "index";
  }
  
  private void configureDb() {
	  Connection connection = null;
		Statement stmt = null;  
		ResultSet rs = null;
		
		try {
	      connection = dataSource.getConnection();
	  	  stmt = connection.createStatement();
	      stmt.executeUpdate("INSERT INTO city VALUES (1, 1, 'MADRID')");
	      stmt.executeUpdate("INSERT INTO city VALUES (2, 1, 'BARCELONA')");
	      rs = stmt.executeQuery("SELECT name FROM city");
	      
	    } catch (Exception e) {
	      
	    } finally {
	    	if (rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	    	if (stmt!=null)
	    		try {
	    			stmt.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	    	if (connection!=null)
	    		try {
	    			connection.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        
	    }
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
	Connection connection = null;
	Statement stmt = null;  
	ResultSet rs = null;
	
	try {
      connection = dataSource.getConnection();
  	  stmt = connection.createStatement();
      stmt.executeUpdate("INSERT INTO city VALUES (1, 1, 'MADRID')");
      stmt.executeUpdate("INSERT INTO city VALUES (2, 1, 'BARCELONA')");
      rs = stmt.executeQuery("SELECT name FROM city");

      ArrayList<String> output = new ArrayList<>();
      output.add("Wellcome to APIRest. ");
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("name"));
      }

      model.put("records", output);
      
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    } finally {
    	if (rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
    	if (stmt!=null)
    		try {
    			stmt.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
    	if (connection!=null)
    		try {
    			connection.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
        
    }
  }
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
  }

}
