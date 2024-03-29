package exam.demo.service;

import exam.demo.entity.Role;
import exam.demo.entity.User;
import exam.demo.payload.Result;
import exam.demo.payload.admin.UserRequest;
import exam.demo.repository.RoleRepository;
import exam.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public Result Edit(UserRequest userRequest,Long uuid) {
        Result result = new Result();
       Optional<User> userOptional=userRepository.findById(uuid);
       if (userOptional.isPresent()){
          User  user=userOptional.get();
          user.setUserName(userRequest.getUserName());
          user.setFullName(userRequest.getFullName());
          user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
           List<Role> roles=new ArrayList<>();
           Role role=roleRepository.getOne(userRequest.getRole());
           roles.add(role);
           user.setRoles(roles);
           userRepository.save(user);

       }

        result.setMessage("User saqlandi");
        result.setSuccess(true);
        return result;
    }


    public Result delete(Long id) {
        userRepository.deleteById(id);
        return new Result(true, "User deleted");
    }


    public Result create(UserRequest userRequest) {
        Result result = new Result();
        User user=new User();
        user.setFullName(userRequest.getFullName());
        user.setUserName(userRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        List<Role> roles=new ArrayList<>();
        Role role=roleRepository.getOne(userRequest.getRole());
        roles.add(role);
        user.setRoles(roles);
       try {
           userRepository.save(user);
           result.setMessage("User saqlandi");
           result.setSuccess(true);
       }catch (Exception e)
       {
           result.setSuccess(false);
           result.setMessage(e.getMessage());
       }

        return result;

    }


    public User getUser(Long uuid) {
        Result result = new Result();
        Optional<User> user = userRepository.findById(uuid);
        if (user.isPresent()) {

            return user.get();
        }
        return user.get();
    }

    public Result checkUser(String userName) {
        Result result = new Result();
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            result.setSuccess(false);
            result.setMessage("Ushbu user mavjud");
            return result;
        }
        result.setSuccess(true);
        result.setMessage("ushbu user mavjud");
        return result;
    }

public Page<User> getUserPage( int page,int size){

    Pageable pageable= PageRequest.of(page,size, Sort.Direction.ASC ,"createdAt");
    return  userRepository.findAll(pageable);
}


}
