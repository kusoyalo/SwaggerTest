plugins{
    `java`
}

tasks.withType<JavaCompile>{
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>{
    options.encoding = "UTF-8"
}

repositories{
    mavenCentral()
}



dependencies{
	implementation("org.springframework.boot:spring-boot-starter:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.0.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-gson:0.11.5")
	
	implementation("org.projectlombok:lombok:1.18.24")
	
	annotationProcessor("org.projectlombok:lombok:1.18.24")
}
