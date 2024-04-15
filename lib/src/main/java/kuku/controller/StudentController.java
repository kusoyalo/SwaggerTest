package kuku.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kuku.entity.Student;

@RestController
public class StudentController{
	
	@RequestMapping(value = "/getStudent",method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<Student> getStudent(){
		Student student = new Student();
		student.setName("陳映廷");
		student.setAge(42);
		
		return ResponseEntity.ok(student);
	}
	@RequestMapping(value = "/setStudent",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Student> setStudent(@RequestBody Student student){
		Student s = new Student();
		s.setName(student.getName());
		s.setAge(student.getAge());
		
		return ResponseEntity.ok(student);
	}
}
