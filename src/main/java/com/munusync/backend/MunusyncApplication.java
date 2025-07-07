package com.munusync.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import java.sql.Connection;

@SpringBootApplication
public class MunusyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(MunusyncApplication.class, args);
    }}

