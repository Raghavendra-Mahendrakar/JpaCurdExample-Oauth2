package com.pws.JpaCurdOperation.oauthconfig;

import com.pws.JpaCurdOperation.entity.Student;
import com.pws.JpaCurdOperation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Student> optionalStudent = studentRepository.findUserByName(username);
		if (optionalStudent.isPresent()) {
			return (UserDetails) optionalStudent.get();
		}
		else {
			throw new UsernameNotFoundException(username);
		}

	}
}