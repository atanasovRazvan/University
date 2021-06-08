package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import model.User;
import model.Item;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;
import repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("")
public class RestService {

    private UserRepository userRepository = new UserRepository();
    private ItemRepository itemRepository = new ItemRepository();

    @PutMapping("/item")
    public ResponseEntity<?> getItems(@RequestBody String token) {

        System.out.println("hello");
        System.out.println(token);
        if(token.startsWith("\""))
            token = token.replace("\"", "");

        List<Item> list = new ArrayList<>();
        String username = "";
        List<User> users = userRepository.findAll();
        for(int i = 0; i < users.size() && username.equals(""); i ++){
            if(users.get(i).getToken() != null && users.get(i).getToken().equals(token))
                username = users.get(i).getUsername();
        }

        if(username.equals(""))
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);

        String finalUsername = username;
        itemRepository.findAll().forEach(item -> {
            if(item.getOwnerUsername().equals(finalUsername))
                list.add(item);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable("itemId") String itemId) {
        return new ResponseEntity<>(itemRepository.findOne(itemId), HttpStatus.OK);
    }

    @PostMapping("/item")
    public Item saveItem(@RequestBody Item item) {
        item.setId(itemRepository.findAll().get(itemRepository.findAll().size() - 1).getId() + 1);
        item.setVersion(1);
        return itemRepository.add(item);
    }

    @PutMapping("/item/{itemId}")
    public Item updateItem(@RequestBody Item item, @PathVariable("itemId") String itemId) {
        item.setId(itemId);
        item.setVersion(item.getVersion()+1);
        return itemRepository.update(item);
    }

    @DeleteMapping("/item/{itemId}")
    public Item deleteItem(@PathVariable("itemId") String itemId) {
        System.out.println(itemId);
        return itemRepository.remove(itemRepository.findOne(itemId));
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        if (userRepository.findOne(user.getUsername()) != null && userRepository.findOne(user.getPassword()).getPassword().equals(user.getPassword())) {
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 60))
                    .sign(Algorithm.HMAC256("secret"));

            //user.setToken(userRepository.findOne(user.getUsername()).getToken());
            user.setToken(token);
            userRepository.update(user);

            class Data {
                public String token;

                public Data(String token) {
                    this.token = token;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }
            }

            Data data = new Data(token);

            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}




