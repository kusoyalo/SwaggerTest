package kuku.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Student implements Serializable{
	private static final long serialVersionUID = -826339858248125192L;

	@Schema(title = "學生姓名",example = "kuku")
    private String name;

	@Schema(title = "學生年齡",example = "18")
    private Integer age;
}
