package com.pws.JpaCurdOperation.controller;

import com.pws.JpaCurdOperation.dto.StudentDto;
import com.pws.JpaCurdOperation.entity.Student;
import com.pws.JpaCurdOperation.service.StudentServiceImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return new ResponseEntity<>("Student Details saved", HttpStatus.OK);
    }

    @GetMapping("/fetchAllStudent")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<Object> updateStudent(@RequestBody StudentDto studentDto) throws Exception {
        studentService.updateStudent(studentDto);
        return new ResponseEntity<>("Student updated",HttpStatus.OK);
    }

    @GetMapping("/getStudentById")
    public Optional<Student> getStudentById(@RequestParam Integer id) throws Exception{
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/deleteById")
    public String deleteStudentById(@RequestParam Integer id){
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/fetch/name")
    public ResponseEntity<Object> getAllStudentName(){
    List<String> students=studentService.getAllStudentName();
        return ResponseEntity.ok(students);
    }

}
