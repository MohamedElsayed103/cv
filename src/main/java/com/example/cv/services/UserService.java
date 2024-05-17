package com.example.cv.services;
import com.example.cv.enums.Role;
import com.example.cv.model.JobSeeker;
import com.example.cv.model.Recruiter;
import com.example.cv.model.User;
import com.example.cv.model.dto.LoginRequestDto;
import com.example.cv.model.dto.UserEditContactInformationDto;
import com.example.cv.model.dto.UserSignUpDto;
import com.example.cv.repository.JobSeekerRepository;
import com.example.cv.repository.RecruiterRepository;
import com.example.cv.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final RecruiterRepository recruiterRepository;


    public void signUp(UserSignUpDto userDto){

        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Validate if the email is unique
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        User user = User
                .builder()
                .id(userDto.getEmail())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();

        JobSeeker jobSeeker= JobSeeker.builder()
                .id(userDto.getEmail())
                .build();

        Recruiter recruiter = Recruiter.builder()
                .id(userDto.getEmail())
                .build();

        if (userDto.getRole().toString().equals("JOBSEEKER"))
            jobSeekerRepository.save(jobSeeker);

        else if(userDto.getRole().toString().equals("HR"))
            recruiterRepository.save(recruiter);

        userRepository.save(user);
    }
    public User logIn(LoginRequestDto loginDto)  {
        return getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
    }

    public User editContactInformation(UserEditContactInformationDto user){

        User user1 =  userRepository.findByEmail(user.getUserEmail()).get();

        user1.setAddress(user.getAddress());
        user1.setUserName(user.getUserName());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());

        return userRepository.save(user1);
    }





    public User getUserByEmailAndPassword(String email , String password)  {
        return userRepository.findByEmailAndPassword(email , password).orElseThrow();
    }
    public boolean isUserAdmin(String email) {

        return userRepository.findByEmail(email).get().getRole()== Role.ADMIN;
    }
}
