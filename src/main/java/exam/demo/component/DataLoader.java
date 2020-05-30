package exam.demo.component;

import exam.demo.entity.Country;
import exam.demo.entity.User;
import exam.demo.entity.bot.Client;
import exam.demo.entity.enums.RoleName;
import exam.demo.repository.RoleRepository;
import exam.demo.repository.UserRepository;
import exam.demo.repository.bot.AttachmentRepository;
import exam.demo.repository.bot.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void run(String... args) throws Exception {
      if (initialMode.equals("always")) {
          PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User("Shamsiddin xushbaqtov", "shamsiddin",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_ADMIN));
        User user1 = new User("Shamsiddin xushbaqtov", "user",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_USER));
        User user2 = new User("murojaat", "murojaat",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_MUROJAAT));
        User user3 = new User("rahbariyat", "rahbariyat",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_RAHBARIYAT));
        User user4 = new User("kanselyariya", "kanselyariya",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_KANSELYARIYA));
        User user5 = new User("spiker", "spiker",passwordEncoder.encode("12345"), roleRepository.findByName(RoleName.ROLE_SPIKER));

          userRepository.save(user);
          userRepository.save(user1);
          userRepository.save(user2);
          userRepository.save(user3);
          userRepository.save(user4);
          userRepository.save(user5);
          Country country=new Country();
          Client client=new Client();
          client.setChatId((long) 551695315);
          client.setFirstName("Shamsiddin");
          client.setLastName("dddd");
          client.setUserName("");
          client.setUser(user);
          clientRepository.save(client);

      }
    }
}
